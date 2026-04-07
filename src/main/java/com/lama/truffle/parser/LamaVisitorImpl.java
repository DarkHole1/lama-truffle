package com.lama.truffle.parser;

import com.lama.truffle.nodes.*;
import com.lama.truffle.parser.LamaParser.*;
import com.lama.truffle.runtime.CapturedVariable;
import com.lama.truffle.runtime.Scope;
import com.lama.truffle.runtime.VariableLookup;
import com.oracle.truffle.api.frame.FrameDescriptor;

import java.util.ArrayList;
import java.util.List;

import static com.lama.truffle.nodes.BinaryOperationNode.BinaryOperator.*;

public class LamaVisitorImpl extends LamaBaseVisitor<ExpressionNode> {

    private Scope currentScope;
    private FrameDescriptor rootFrameDescriptor;

    public FrameDescriptor getRootFrameDescriptor() {
        return rootFrameDescriptor;
    }

    @Override
    public ExpressionNode visitCompilationUnit(LamaParser.CompilationUnitContext ctx) {
        currentScope = new Scope();  // root scope
        if (ctx.scopeExpression() != null) {
            ExpressionNode body = visitScopeExpression(ctx.scopeExpression());
            rootFrameDescriptor = currentScope.getBuilder().build();
            return body;
        }
        rootFrameDescriptor = currentScope.getBuilder().build();
        return null;
    }

    @Override
    public ExpressionNode visitScopeExpression(LamaParser.ScopeExpressionContext ctx) {
        // Create child scope for definitions and expression
        Scope childScope = new Scope(currentScope);
        Scope previousScope = currentScope;
        currentScope = childScope;

        try {
            // Pass 1: collect all definition names and allocate slots
            for (DefinitionContext defCtx : ctx.definition()) {
                if (defCtx.variableDefinition() != null) {
                    VariableDefinitionSequenceContext seq = defCtx.variableDefinition().variableDefinitionSequence();
                    for (VariableDefinitionItemContext itemCtx : seq.variableDefinitionItem()) {
                        String varName = itemCtx.LIDENT().getText();
                        childScope.allocateVariable(varName);
                    }
                } else if (defCtx.functionDefinition() != null) {
                    String funcName = defCtx.functionDefinition().LIDENT().getText();
                    childScope.allocateVariable(funcName);
                }
            }

            // Pass 2: visit definitions (values/bodies can reference forward defs)
            DefinitionNode[] definitions = new DefinitionNode[0];
            if (ctx.definition().size() > 0) {
                definitions = new DefinitionNode[ctx.definition().size()];
                for (int i = 0; i < ctx.definition().size(); i++) {
                    definitions[i] = (DefinitionNode) visitDefinition(ctx.definition(i));
                }
            }

            // Get the final expression (if any)
            ExpressionNode expression = null;
            if (ctx.expression() != null) {
                expression = visit(ctx.expression());
            }

            ScopeNode scopeNode = new ScopeNode(definitions, expression);
            FrameDescriptor descriptor = childScope.getBuilder().build();
            return new ScopeEnterNode(descriptor, scopeNode);
        } finally {
            currentScope = previousScope;
        }
    }

    public ExpressionNode visitDefinition(LamaParser.DefinitionContext ctx) {
        if (ctx.variableDefinition() != null) {
            return visitVariableDefinition(ctx.variableDefinition());
        } else if (ctx.functionDefinition() != null) {
            return visitFunctionDefinition(ctx.functionDefinition());
        } else if (ctx.infixDefinition() != null) {
            throw new UnsupportedOperationException("infix definitions are not supported");
        }
        return null;
    }

    public ExpressionNode visitVariableDefinition(LamaParser.VariableDefinitionContext ctx) {
        LamaParser.VariableDefinitionSequenceContext seq = ctx.variableDefinitionSequence();
        if (seq.variableDefinitionItem().size() == 1) {
            return visitVariableDefinitionItem(seq.variableDefinitionItem(0));
        }
        return visitVariableDefinitionItem(seq.variableDefinitionItem(0));
    }

    public ExpressionNode visitVariableDefinitionItem(LamaParser.VariableDefinitionItemContext ctx) {
        String varName = ctx.LIDENT().getText();
        // Slot already allocated in pass 1
        int slot = currentScope.lookupVariable(varName).getSlot();

        ExpressionNode valueNode = null;
        if (ctx.basicExpression() != null) {
            valueNode = visit(ctx.basicExpression());
        }
        return new VariableDefinitionNode(varName, slot, valueNode);
    }

    @Override
    public ExpressionNode visitExpression(LamaParser.ExpressionContext ctx) {
        if (ctx.expression().size() > 1) {
            ExpressionNode last = null;
            for (LamaParser.ExpressionContext exprCtx : ctx.expression()) {
                last = visit(exprCtx);
            }
            return last;
        } else {
            return visit(ctx.basicExpression());
        }
    }

    @Override
    public ExpressionNode visitBasicExpression(LamaParser.BasicExpressionContext ctx) {
        return visit(ctx.assignmentExpr());
    }

    @Override
    public ExpressionNode visitAssignmentExpr(LamaParser.AssignmentExprContext ctx) {
        if (ctx.getChildCount() > 1 && ctx.getChild(1).getText().equals(":=")) {
            // Assignment: LIDENT := expr
            String varName = ctx.getChild(0).getText();
            VariableLookup lookup = currentScope.lookupVariable(varName);
            if (lookup == null) {
                throw new RuntimeException("Undefined variable: " + varName);
            }
            // captureIndex will be assigned by the enclosing function scope
            ExpressionNode right = visit(ctx.assignmentExpr());
            return new VariableWriteNode(varName, lookup, right);
        } else {
            return visit(ctx.listConstructorExpr());
        }
    }

    @Override
    public ExpressionNode visitListConstructorExpr(LamaParser.ListConstructorExprContext ctx) {
        if (ctx.getChildCount() > 1 && ctx.getChild(1).getText().equals(":")) {
            ExpressionNode left = visit(ctx.disjunctionExpr());
            ExpressionNode right = visit(ctx.listConstructorExpr());
            return BinaryOperationNodeGen.create(CONS, left, right);
        } else {
            return visit(ctx.disjunctionExpr());
        }
    }

    @Override
    public ExpressionNode visitDisjunctionExpr(LamaParser.DisjunctionExprContext ctx) {
        if (ctx.conjunctionExpr().size() > 1) {
            ExpressionNode result = visit(ctx.conjunctionExpr(0));
            for (int i = 1; i < ctx.getChildCount(); i += 2) {
                if (ctx.getChild(i).getText().equals("!!")) {
                    ExpressionNode right = visit(ctx.conjunctionExpr((i + 1) / 2));
                    result = BinaryOperationNodeGen.create(LOGICAL_OR, result, right);
                }
            }
            return result;
        } else {
            return visit(ctx.conjunctionExpr(0));
        }
    }

    @Override
    public ExpressionNode visitConjunctionExpr(LamaParser.ConjunctionExprContext ctx) {
        if (ctx.comparisonExpr().size() > 1) {
            ExpressionNode result = visit(ctx.comparisonExpr(0));
            for (int i = 1; i < ctx.getChildCount(); i += 2) {
                if (ctx.getChild(i).getText().equals("&&")) {
                    ExpressionNode right = visit(ctx.comparisonExpr((i + 1) / 2));
                    result = BinaryOperationNodeGen.create(LOGICAL_AND, result, right);
                }
            }
            return result;
        } else {
            return visit(ctx.comparisonExpr(0));
        }
    }

    @Override
    public ExpressionNode visitComparisonExpr(LamaParser.ComparisonExprContext ctx) {
        if (ctx.getChildCount() > 1) {
            ExpressionNode left = visit(ctx.additiveExpr(0));
            ExpressionNode right = visit(ctx.additiveExpr(1));

            String opText = ctx.getChild(1).getText();
            BinaryOperationNode.BinaryOperator op = fromComparisonOperator(opText);

            return BinaryOperationNodeGen.create(op, left, right);
        } else {
            return visit(ctx.additiveExpr(0));
        }
    }

    @Override
    public ExpressionNode visitAdditiveExpr(LamaParser.AdditiveExprContext ctx) {
        if (ctx.multiplicativeExpr().size() > 1) {
            ExpressionNode result = visit(ctx.multiplicativeExpr(0));
            for (int i = 1; i < ctx.getChildCount(); i += 2) {
                String opText = ctx.getChild(i).getText();
                ExpressionNode right = visit(ctx.multiplicativeExpr((i + 1) / 2));

                BinaryOperationNode.BinaryOperator op = "+".equals(opText) ? ADD : SUBTRACT;

                result = BinaryOperationNodeGen.create(op, result, right);
            }
            return result;
        } else {
            return visit(ctx.multiplicativeExpr(0));
        }
    }

    @Override
    public ExpressionNode visitMultiplicativeExpr(LamaParser.MultiplicativeExprContext ctx) {
        if (ctx.unaryExpr().size() > 1) {
            ExpressionNode result = visit(ctx.unaryExpr(0));
            for (int i = 1; i < ctx.getChildCount(); i += 2) {
                String opText = ctx.getChild(i).getText();
                ExpressionNode right = visit(ctx.unaryExpr((i + 1) / 2));

                BinaryOperationNode.BinaryOperator op;
                if ("*".equals(opText)) {
                    op = MULTIPLY;
                } else if ("/".equals(opText)) {
                    op = DIVIDE;
                } else { // %
                    op = MODULO;
                }

                result = BinaryOperationNodeGen.create(op, result, right);
            }
            return result;
        } else {
            return visit(ctx.unaryExpr(0));
        }
    }

    @Override
    public ExpressionNode visitUnaryExpr(LamaParser.UnaryExprContext ctx) {
        if (ctx.getChild(0) != null && ctx.getChild(0).getText().equals("-")) {
            ExpressionNode operand = visit(ctx.postfixExpression());
            return BinaryOperationNodeGen.create(SUBTRACT, new IntegerLiteralNode(0), operand);
        } else {
            return visit(ctx.postfixExpression());
        }
    }

    @Override
    public ExpressionNode visitPostfixExpression(LamaParser.PostfixExpressionContext ctx) {
        if (ctx.primary() != null) {
            return visit(ctx.primary());
        } else if (ctx.getChildCount() > 1 && ctx.getChild(1).getText().equals("(")) {
            ExpressionNode functionNode = visit(ctx.postfixExpression());

            ExpressionNode[] argumentNodes = new ExpressionNode[0];
            if (ctx.expression().size() > 0) {
                argumentNodes = new ExpressionNode[ctx.expression().size()];
                for (int i = 0; i < ctx.expression().size(); i++) {
                    argumentNodes[i] = visit(ctx.expression(i));
                }
            }

            return new FunctionCallNode(functionNode, argumentNodes);
        } else if (ctx.getChildCount() > 1 && ctx.getChild(1).getText().equals("[")) {
            ExpressionNode arrayNode = visit(ctx.postfixExpression());
            ExpressionNode indexNode = visit(ctx.expression(0));
            return ArrayAccessNodeGen.create(arrayNode, indexNode);
        }
        return visit(ctx.primary());
    }

    @Override
    public ExpressionNode visitPrimary(LamaParser.PrimaryContext ctx) {
        if (ctx.DECIMAL() != null) {
            long value = Long.parseLong(ctx.DECIMAL().getText());
            return new IntegerLiteralNode(value);
        } else if (ctx.STRING() != null) {
            String value = ctx.STRING().getText();
            value = value.substring(1, value.length() - 1);
            return new StringLiteralNode(value);
        } else if (ctx.CHAR() != null) {
            String value = ctx.CHAR().getText();
            value = value.substring(1, value.length() - 1);
            return new IntegerLiteralNode(value.charAt(0));
        } else if (ctx.LIDENT() != null) {
            String varName = ctx.LIDENT().getText();
            VariableLookup lookup = currentScope.lookupVariable(varName);
            if (lookup == null) {
                throw new RuntimeException("Undefined variable: " + varName);
            }
            return new VariableAccessNode(varName, lookup);
        } else if (ctx.TRUE() != null) {
            return new BooleanLiteralNode(true);
        } else if (ctx.FALSE() != null) {
            return new BooleanLiteralNode(false);
        } else if (ctx.getChild(0) != null && ctx.getChild(0).getText().equals("(") && ctx.scopeExpression() != null) {
            return visit(ctx.scopeExpression());
        }

        // Anonymous function: 'fun' '(' functionArguments ')' functionBody
        if (ctx.FUN() != null && ctx.functionArguments() != null && ctx.functionBody() != null) {
            String[] paramNames = new String[0];
            if (ctx.functionArguments().LIDENT().size() > 0) {
                paramNames = new String[ctx.functionArguments().LIDENT().size()];
                for (int i = 0; i < ctx.functionArguments().LIDENT().size(); i++) {
                    paramNames[i] = ctx.functionArguments().LIDENT(i).getText();
                }
            }

            // Create function scope (with captured frames slot)
            Scope functionScope = Scope.createFunctionScope(currentScope);
            Scope previousScope = currentScope;
            currentScope = functionScope;

            try {
                // Allocate parameter slots
                int[] paramSlots = new int[paramNames.length];
                for (int i = 0; i < paramNames.length; i++) {
                    paramSlots[i] = functionScope.allocateVariable(paramNames[i]);
                }

                ExpressionNode body = visit(ctx.functionBody());
                FrameDescriptor descriptor = functionScope.getBuilder().build();

                // Collect captured variables statically
                CapturedVariable[] capturedVars = collectCapturedVariables(body, functionScope);

                return new FunctionNode(paramNames, paramSlots, descriptor, body, capturedVars);
            } finally {
                currentScope = previousScope;
            }
        }

        // Control flow expressions
        if (ctx.ifExpression() != null) {
            return visit(ctx.ifExpression());
        } else if (ctx.whileDoExpression() != null) {
            return visit(ctx.whileDoExpression());
        } else if (ctx.doWhileExpression() != null) {
            return visit(ctx.doWhileExpression());
        } else if (ctx.forExpression() != null) {
            return visit(ctx.forExpression());
        } else if (ctx.caseExpression() != null) {
            return visit(ctx.caseExpression());
        }

        return new IntegerLiteralNode(0);
    }

    // Control flow expression visitors

    @Override
    public ExpressionNode visitIfExpression(LamaParser.IfExpressionContext ctx) {
        ExpressionNode condition = visit(ctx.expression());

        // Then branch in child scope
        Scope thenScope = new Scope(currentScope);
        Scope previousScope = currentScope;
        currentScope = thenScope;
        ExpressionNode thenBranch = visit(ctx.scopeExpression());
        FrameDescriptor thenDescriptor = thenScope.getBuilder().build();
        currentScope = previousScope;

        // Process elif and else branches
        java.util.List<ExpressionNode> elifConditions = new java.util.ArrayList<>();
        java.util.List<ExpressionNode> elifBranches = new java.util.ArrayList<>();
        ExpressionNode elseBranch = null;

        if (ctx.elsePart() != null) {
            LamaParser.ElsePartContext elseCtx = ctx.elsePart();
            while (elseCtx != null) {
                if (elseCtx.ELIF() != null) {
                    elifConditions.add(visit(elseCtx.expression()));

                    Scope elifScope = new Scope(currentScope);
                    previousScope = currentScope;
                    currentScope = elifScope;
                    ExpressionNode elifBody = visit(elseCtx.scopeExpression());
                    FrameDescriptor elifDescriptor = elifScope.getBuilder().build();
                    currentScope = previousScope;
                    elifBranches.add(new ScopeEnterNode(elifDescriptor, elifBody));
                } else if (elseCtx.ELSE() != null) {
                    Scope elseScope = new Scope(currentScope);
                    previousScope = currentScope;
                    currentScope = elseScope;
                    elseBranch = visit(elseCtx.scopeExpression());
                    FrameDescriptor elseDescriptor = elseScope.getBuilder().build();
                    currentScope = previousScope;
                    elseBranch = new ScopeEnterNode(elseDescriptor, elseBranch);
                }
                if (elseCtx.elsePart() != null) {
                    elseCtx = elseCtx.elsePart();
                } else {
                    break;
                }
            }
        }

        ExpressionNode thenBranchScoped = new ScopeEnterNode(thenDescriptor, thenBranch);
        ExpressionNode[] elifBranchesArray = elifBranches.toArray(new ExpressionNode[0]);

        return new IfNode(condition, thenBranchScoped,
                elifConditions.toArray(new ExpressionNode[0]),
                elifBranchesArray, elseBranch);
    }

    @Override
    public ExpressionNode visitWhileDoExpression(LamaParser.WhileDoExpressionContext ctx) {
        ExpressionNode condition = visit(ctx.expression());

        Scope bodyScope = new Scope(currentScope);
        Scope previousScope = currentScope;
        currentScope = bodyScope;
        ExpressionNode body = visit(ctx.scopeExpression());
        FrameDescriptor bodyDescriptor = bodyScope.getBuilder().build();
        currentScope = previousScope;

        WhileLoopBodyNode loopBody = new WhileLoopBodyNode(condition, body);
        return new WhileNode(new ScopeEnterNode(bodyDescriptor, loopBody));
    }

    @Override
    public ExpressionNode visitDoWhileExpression(LamaParser.DoWhileExpressionContext ctx) {
        Scope bodyScope = new Scope(currentScope);
        Scope previousScope = currentScope;
        currentScope = bodyScope;
        ExpressionNode body = visit(ctx.scopeExpression());
        ExpressionNode condition = visit(ctx.expression());
        FrameDescriptor bodyDescriptor = bodyScope.getBuilder().build();
        currentScope = previousScope;

        DoWhileLoopBodyNode loopBody = new DoWhileLoopBodyNode(body, condition);
        return new DoWhileNode(new ScopeEnterNode(bodyDescriptor, loopBody));
    }

    @Override
    public ExpressionNode visitForExpression(LamaParser.ForExpressionContext ctx) {
        // init runs in parent scope
        ExpressionNode init = visit(ctx.scopeExpression(0));

        // condition, update, body run in child scope
        Scope loopScope = new Scope(currentScope);
        Scope previousScope = currentScope;
        currentScope = loopScope;

        try {
            ExpressionNode condition = visit(ctx.expression(0));
            ExpressionNode update = visit(ctx.expression(1));
            ExpressionNode body = visit(ctx.scopeExpression(1));
            FrameDescriptor loopDescriptor = loopScope.getBuilder().build();
            ForLoopBodyNode loopBody = new ForLoopBodyNode(condition, update, body);
            return new ForNode(init, new ScopeEnterNode(loopDescriptor, loopBody));
        } finally {
            currentScope = previousScope;
        }
    }

    static class PatternVisitor extends LamaBaseVisitor<PatternNode> {
        private final Scope scope;

        PatternVisitor(Scope scope) {
            this.scope = scope;
        }

        @Override
        public PatternNode visitConsPattern(ConsPatternContext ctx) {
            PatternNode headPattern = visit(ctx.simplePattern());
            PatternNode tailPattern = visit(ctx.pattern());
            return new ConsPatternNode(headPattern, tailPattern);
        }

        @Override
        public PatternNode visitSimplePattern(SimplePatternContext ctx) {
            if (ctx.listPattern() != null) {
                return visit(ctx.listPattern());
            }
            if (ctx.arrayPattern() != null) {
                return visit(ctx.arrayPattern());
            }
            if (ctx.wildcardPattern() != null) {
                return new WildcardPatternNode();
            }
            if (ctx.sExprPattern() != null) {
                return visit(ctx.sExprPattern());
            }
            if (ctx.LIDENT() != null) {
                String varName = ctx.LIDENT().getText();
                int slot = scope.allocateVariable(varName);
                if (ctx.pattern() != null) {
                    PatternNode nestedPattern = visit(ctx.pattern());
                    return new VariablePatternNode(varName, slot, nestedPattern);
                }
                return new VariablePatternNode(varName, slot, null);
            }
            if (ctx.DECIMAL() != null) {
                long value = Long.parseLong(ctx.getText());
                return new LiteralPatternNode(value);
            }
            if (ctx.STRING() != null) {
                String value = ctx.STRING().getText();
                value = value.substring(1, value.length() - 1);
                return new LiteralPatternNode(value);
            }
            if (ctx.CHAR() != null) {
                String value = ctx.CHAR().getText();
                value = value.substring(1, value.length() - 1);
                return new LiteralPatternNode((long) value.charAt(0));
            }
            if (ctx.TRUE() != null) {
                return new LiteralPatternNode(1L);
            }
            if (ctx.FALSE() != null) {
                return new LiteralPatternNode(0L);
            }
            if (ctx.BOX() != null) {
                return new TypePatternNode(TypePatternNode.Type.BOX);
            }
            if (ctx.VAL() != null) {
                return new TypePatternNode(TypePatternNode.Type.VAL);
            }
            if (ctx.STR() != null) {
                return new TypePatternNode(TypePatternNode.Type.STR);
            }
            if (ctx.ARRAY_KW() != null) {
                return new TypePatternNode(TypePatternNode.Type.ARRAY);
            }
            if (ctx.SEXP() != null) {
                return new TypePatternNode(TypePatternNode.Type.SEXP);
            }
            if (ctx.FUN() != null) {
                return new TypePatternNode(TypePatternNode.Type.FUN);
            }
            if (ctx.pattern() != null) {
                return visit(ctx.pattern());
            }
            return new WildcardPatternNode();
        }

        @Override
        public PatternNode visitSExprPattern(SExprPatternContext ctx) {
            String tagName = ctx.UIDENT().getText();
            PatternNode[] childPatterns = new PatternNode[0];
            if (ctx.pattern() != null && ctx.pattern().size() > 0) {
                childPatterns = new PatternNode[ctx.pattern().size()];
                for (int i = 0; i < ctx.pattern().size(); i++) {
                    childPatterns[i] = visit(ctx.pattern(i));
                }
            }
            return new SexpPatternNode(tagName, childPatterns);
        }

        @Override
        public PatternNode visitArrayPattern(ArrayPatternContext ctx) {
            PatternNode[] elementPatterns = new PatternNode[0];
            if (ctx.pattern() != null && ctx.pattern().size() > 0) {
                elementPatterns = new PatternNode[ctx.pattern().size()];
                for (int i = 0; i < ctx.pattern().size(); i++) {
                    elementPatterns[i] = visit(ctx.pattern(i));
                }
            }
            return new ArrayPatternNode(elementPatterns);
        }

        @Override
        public PatternNode visitListPattern(ListPatternContext ctx) {
            PatternNode[] elementPatterns = new PatternNode[0];
            if (ctx.pattern() != null && ctx.pattern().size() > 0) {
                elementPatterns = new PatternNode[ctx.pattern().size()];
                for (int i = 0; i < ctx.pattern().size(); i++) {
                    elementPatterns[i] = visit(ctx.pattern(i));
                }
            }
            return new ListPatternNode(elementPatterns);
        }
    }

    @Override
    public ExpressionNode visitCaseExpression(LamaParser.CaseExpressionContext ctx) {
        ExpressionNode scrutinee = visit(ctx.expression());

        // Create child scope for case branches
        Scope caseScope = new Scope(currentScope);
        Scope previousScope = currentScope;
        currentScope = caseScope;

        try {
            PatternVisitor patternVisitor = new PatternVisitor(caseScope);
            ExpressionNode[] branches = new ExpressionNode[ctx.caseBranches().caseBranch().size()];

            for (int i = 0; i < ctx.caseBranches().caseBranch().size(); i++) {
                LamaParser.CaseBranchContext branchCtx = ctx.caseBranches().caseBranch(i);

                // Branch body in its own child scope
                Scope branchScope = new Scope(caseScope);
                currentScope = branchScope;

                PatternNode pattern = branchCtx.pattern().accept(patternVisitor);
                ExpressionNode expression = visit(branchCtx.scopeExpression());
                FrameDescriptor branchDescriptor = branchScope.getBuilder().build();

                branches[i] = new CaseBranchNode(pattern, new ScopeEnterNode(branchDescriptor, expression));
            }

            FrameDescriptor caseDescriptor = caseScope.getBuilder().build();
            return new ScopeEnterNode(caseDescriptor, new CaseNode(scrutinee, branches));
        } finally {
            currentScope = previousScope;
        }
    }

    // Definition visitors

    public ExpressionNode visitFunctionDefinition(LamaParser.FunctionDefinitionContext ctx) {
        String functionName = ctx.LIDENT().getText();
        // Slot already allocated in pass 1
        int funcSlot = currentScope.lookupVariable(functionName).getSlot();

        String[] paramNames = new String[0];
        if (ctx.functionArguments().LIDENT().size() > 0) {
            paramNames = new String[ctx.functionArguments().LIDENT().size()];
            for (int i = 0; i < ctx.functionArguments().LIDENT().size(); i++) {
                paramNames[i] = ctx.functionArguments().LIDENT(i).getText();
            }
        }

        // Create function scope
        Scope functionScope = Scope.createFunctionScope(currentScope);
        Scope previousScope = currentScope;
        currentScope = functionScope;

        try {
            // Allocate parameter slots
            int[] paramSlots = new int[paramNames.length];
            for (int i = 0; i < paramNames.length; i++) {
                paramSlots[i] = functionScope.allocateVariable(paramNames[i]);
            }

            ExpressionNode body = visit(ctx.functionBody());
            FrameDescriptor descriptor = functionScope.getBuilder().build();

            // Collect captured variables statically
            CapturedVariable[] capturedVars = collectCapturedVariables(body, functionScope);

            return new FunctionDefinitionNode(functionName, paramNames, paramSlots,
                    descriptor, body, capturedVars, funcSlot);
        } finally {
            currentScope = previousScope;
        }
    }

    @Override
    public ExpressionNode visitFunctionBody(LamaParser.FunctionBodyContext ctx) {
        if (ctx.scopeExpression() != null) {
            return visit(ctx.scopeExpression());
        }
        return new IntegerLiteralNode(0);
    }

    @Override
    public ExpressionNode visitFunctionArguments(LamaParser.FunctionArgumentsContext ctx) {
        return new IntegerLiteralNode(0);
    }

    // ========== Static Capture Collection ==========

    /**
     * Collects all CapturedVariables from a function body.
     * Walks the AST and finds all VariableAccessNode/VariableWriteNode that access
     * outer scopes (depth > 0). Assigns unique capture indices to their lookups.
     */
    private CapturedVariable[] collectCapturedVariables(ExpressionNode body, Scope functionScope) {
        List<VariableLookup> captures = new ArrayList<>();
        collectCaptures(body, captures, functionScope);

        if (captures.isEmpty()) {
            return new CapturedVariable[0];
        }

        // Deduplicate lookups by (depth, slot), assign capture indices
        java.util.LinkedHashSet<String> seen = new java.util.LinkedHashSet<>();
        List<CapturedVariable> result = new ArrayList<>();
        for (VariableLookup lookup : captures) {
            String key = lookup.getDepth() + ":" + lookup.getSlot();
            if (seen.add(key)) {
                int index = result.size();
                lookup.setCaptureIndex(index);
                result.add(new CapturedVariable(lookup.getDepth(), lookup.getSlot()));
            }
        }

        return result.toArray(new CapturedVariable[0]);
    }

    private void collectCaptures(ExpressionNode node, List<VariableLookup> captures, Scope functionScope) {
        if (node == null) return;

        if (node instanceof VariableAccessNode) {
            VariableAccessNode van = (VariableAccessNode) node;
            VariableLookup lookup = van.getLookup();
            if (lookup.getDepth() > 0) {
                captures.add(lookup);
            }
        } else if (node instanceof VariableWriteNode) {
            VariableWriteNode vwn = (VariableWriteNode) node;
            VariableLookup lookup = vwn.getLookup();
            if (lookup.getDepth() > 0) {
                captures.add(lookup);
            }
            collectCaptures(vwn.getValueNode(), captures, functionScope);
        } else if (node instanceof ScopeNode) {
            ScopeNode sn = (ScopeNode) node;
            for (DefinitionNode def : sn.getDefinitions()) {
                collectCapturesFromDef(def, captures, functionScope);
            }
            collectCaptures(sn.getExpression(), captures, functionScope);
        } else if (node instanceof ScopeEnterNode) {
            ScopeEnterNode sen = (ScopeEnterNode) node;
            collectCaptures(sen.getBody(), captures, functionScope);
        } else if (node instanceof IfNode) {
            IfNode ifn = (IfNode) node;
            collectCaptures(ifn.getCondition(), captures, functionScope);
            collectCaptures(ifn.getThenBranch(), captures, functionScope);
            for (ExpressionNode cond : ifn.getElifConditions()) {
                collectCaptures(cond, captures, functionScope);
            }
            for (ExpressionNode branch : ifn.getElifBranches()) {
                collectCaptures(branch, captures, functionScope);
            }
            collectCaptures(ifn.getElseBranch(), captures, functionScope);
        } else if (node instanceof ForNode) {
            ForNode fn = (ForNode) node;
            collectCaptures(fn.getInit(), captures, functionScope);
            collectCaptures(fn.getLoopScope(), captures, functionScope);
        } else if (node instanceof WhileNode) {
            WhileNode wn = (WhileNode) node;
            collectCaptures(wn.getLoopScope(), captures, functionScope);
        } else if (node instanceof DoWhileNode) {
            DoWhileNode dwn = (DoWhileNode) node;
            collectCaptures(dwn.getLoopScope(), captures, functionScope);
        } else if (node instanceof CaseNode) {
            CaseNode cn = (CaseNode) node;
            collectCaptures(cn.getScrutinee(), captures, functionScope);
            for (ExpressionNode branch : cn.getBranches()) {
                if (branch instanceof CaseBranchNode) {
                    CaseBranchNode cbn = (CaseBranchNode) branch;
                    collectCaptures(cbn.getBody(), captures, functionScope);
                }
            }
        } else if (node instanceof FunctionCallNode) {
            FunctionCallNode fcn = (FunctionCallNode) node;
            collectCaptures(fcn.getFunctionNode(), captures, functionScope);
            for (ExpressionNode arg : fcn.getArgumentNodes()) {
                collectCaptures(arg, captures, functionScope);
            }
        } else if (node instanceof ForLoopBodyNode) {
            ForLoopBodyNode flbn = (ForLoopBodyNode) node;
            collectCaptures(flbn.getCondition(), captures, functionScope);
            collectCaptures(flbn.getUpdate(), captures, functionScope);
            collectCaptures(flbn.getBody(), captures, functionScope);
        } else if (node instanceof WhileLoopBodyNode) {
            WhileLoopBodyNode wlbn = (WhileLoopBodyNode) node;
            collectCaptures(wlbn.getCondition(), captures, functionScope);
            collectCaptures(wlbn.getBody(), captures, functionScope);
        } else if (node instanceof DoWhileLoopBodyNode) {
            DoWhileLoopBodyNode dwlbn = (DoWhileLoopBodyNode) node;
            collectCaptures(dwlbn.getBody(), captures, functionScope);
            collectCaptures(dwlbn.getCondition(), captures, functionScope);
        } else if (node instanceof FunctionNode) {
            // Nested function - its captures are separate, don't traverse into it
        } else if (node instanceof FunctionDefinitionNode) {
            // Nested function def - don't traverse into it
        }
        // Other nodes (literals, BinaryOperation, etc.) don't have variable access
    }

    private void collectCapturesFromDef(DefinitionNode def, List<VariableLookup> captures, Scope functionScope) {
        if (def instanceof VariableDefinitionNode) {
            VariableDefinitionNode vdn = (VariableDefinitionNode) def;
            collectCaptures(vdn.getValueNode(), captures, functionScope);
        }
        // FunctionDefinitionNode: don't traverse into nested functions
    }

    private BinaryOperationNode.BinaryOperator fromComparisonOperator(String opText) {
        switch (opText) {
            case "==":
                return EQUAL;
            case "!=":
                return NOT_EQUAL;
            case "<":
                return LESS;
            case "<=":
                return LESS_EQUAL;
            case ">":
                return GREATER;
            case ">=":
                return GREATER_EQUAL;
            default:
                throw new IllegalArgumentException("Unknown comparison operator: " + opText);
        }
    }
}
