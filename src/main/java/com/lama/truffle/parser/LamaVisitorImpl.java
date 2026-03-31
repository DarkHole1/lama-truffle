package com.lama.truffle.parser;

import com.lama.truffle.nodes.*;
import com.lama.truffle.parser.LamaParser.*;

import static com.lama.truffle.nodes.BinaryOperationNode.BinaryOperator.*;

public class LamaVisitorImpl extends LamaBaseVisitor<ExpressionNode> {

    @Override
    public ExpressionNode visitCompilationUnit(LamaParser.CompilationUnitContext ctx) {
        if (ctx.scopeExpression() != null) {
            return visit(ctx.scopeExpression());
        }
        return null;
    }

    @Override
    public ExpressionNode visitScopeExpression(LamaParser.ScopeExpressionContext ctx) {
        // Collect all definitions
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

        return new ScopeNode(definitions, expression);
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
        // Handle: ('var' | 'public') variableDefinitionSequence ';'
        // For now, handle single variable definition
        LamaParser.VariableDefinitionSequenceContext seq = ctx.variableDefinitionSequence();
        if (seq.variableDefinitionItem().size() == 1) {
            return visitVariableDefinitionItem(seq.variableDefinitionItem(0));
        }
        // Multiple variables - create a sequence (for now, just handle the first one)
        return visitVariableDefinitionItem(seq.variableDefinitionItem(0));
    }

    public ExpressionNode visitVariableDefinitionItem(LamaParser.VariableDefinitionItemContext ctx) {
        String varName = ctx.LIDENT().getText();
        ExpressionNode valueNode = null;
        if (ctx.basicExpression() != null) {
            valueNode = visit(ctx.basicExpression());
        }
        return new VariableDefinitionNode(varName, valueNode);
    }

    @Override
    public ExpressionNode visitExpression(LamaParser.ExpressionContext ctx) {
        if (ctx.expression().size() > 1) {
            // Sequence of expressions - execute all, return last
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
            ExpressionNode right = visit(ctx.assignmentExpr());
            // For now, return the right side
            return right;
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
            // Function call: postfixExpression '(' (expression (',' expression)*)? ')'
            ExpressionNode functionNode = visit(ctx.postfixExpression());

            // Get the function name from the postfixExpression
            String functionName = ctx.postfixExpression().getText();

            // Parse arguments
            ExpressionNode[] argumentNodes = new ExpressionNode[0];
            if (ctx.expression().size() > 0) {
                argumentNodes = new ExpressionNode[ctx.expression().size()];
                for (int i = 0; i < ctx.expression().size(); i++) {
                    argumentNodes[i] = visit(ctx.expression(i));
                }
            }

            return new FunctionCallNode(functionName, argumentNodes);
        } else if (ctx.getChildCount() > 1 && ctx.getChild(1).getText().equals("[")) {
            // Array indexing
            ExpressionNode array = visit(ctx.postfixExpression());
            return array;
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
            return new VariableAccessNode(varName);
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
            ExpressionNode body = visit(ctx.functionBody());
            return new FunctionNode(paramNames, body);
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
        ExpressionNode thenBranch = visit(ctx.scopeExpression());

        ExpressionNode[] elifConditions = new ExpressionNode[0];
        ExpressionNode[] elifBranches = new ExpressionNode[0];
        ExpressionNode elseBranch = null;

        if (ctx.elsePart() != null) {
            // Process elif and else branches
            java.util.List<ExpressionNode> elifConds = new java.util.ArrayList<>();
            java.util.List<ExpressionNode> elifBods = new java.util.ArrayList<>();

            LamaParser.ElsePartContext elseCtx = ctx.elsePart();
            while (elseCtx != null) {
                if (elseCtx.ELIF() != null) {
                    elifConds.add(visit(elseCtx.expression()));
                    elifBods.add(visit(elseCtx.scopeExpression()));
                } else if (elseCtx.ELSE() != null) {
                    elseBranch = visit(elseCtx.scopeExpression());
                }
                // Handle chained elsePart
                if (elseCtx.elsePart() != null) {
                    elseCtx = elseCtx.elsePart();
                } else {
                    break;
                }
            }

            elifConditions = elifConds.toArray(new ExpressionNode[0]);
            elifBranches = elifBods.toArray(new ExpressionNode[0]);
        }

        return new IfNode(condition, thenBranch, elifConditions, elifBranches, elseBranch);
    }

    @Override
    public ExpressionNode visitWhileDoExpression(LamaParser.WhileDoExpressionContext ctx) {
        ExpressionNode condition = visit(ctx.expression());
        ExpressionNode body = visit(ctx.scopeExpression());
        return new WhileNode(condition, body);
    }

    @Override
    public ExpressionNode visitDoWhileExpression(LamaParser.DoWhileExpressionContext ctx) {
        ExpressionNode body = visit(ctx.scopeExpression());
        ExpressionNode condition = visit(ctx.expression());
        return new DoWhileNode(body, condition);
    }

    @Override
    public ExpressionNode visitForExpression(LamaParser.ForExpressionContext ctx) {
        // for init, condition, update do body od
        ExpressionNode init = visit(ctx.scopeExpression(0));
        ExpressionNode condition = visit(ctx.expression(0));
        ExpressionNode update = visit(ctx.expression(1));
        ExpressionNode body = visit(ctx.scopeExpression(1));
        return new ForNode(init, condition, update, body);
    }

    static class PatternVisitor extends LamaBaseVisitor<PatternNode> {
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
                if (ctx.pattern() != null) {
                    // LIDENT @ pattern - variable binding with nested pattern
                    // TODO
                    PatternNode nestedPattern = visit(ctx.pattern());
                    return new VariablePatternNode(varName);
                }
                return new VariablePatternNode(varName);
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
        ExpressionNode[] branches = new ExpressionNode[ctx.caseBranches().caseBranch().size()];

        for (int i = 0; i < ctx.caseBranches().caseBranch().size(); i++) {
            LamaParser.CaseBranchContext branchCtx = ctx.caseBranches().caseBranch(i);
            PatternNode pattern = branchCtx.pattern().accept(new PatternVisitor());
            ExpressionNode expression = visit(branchCtx.scopeExpression());
            branches[i] = new CaseBranchNode(pattern, expression);
        }

        return new CaseNode(scrutinee, branches);
    }

    // Definition visitors

    public ExpressionNode visitFunctionDefinition(LamaParser.FunctionDefinitionContext ctx) {
        String functionName = ctx.LIDENT().getText();

        // Get parameter names
        String[] paramNames = new String[0];
        if (ctx.functionArguments().LIDENT().size() > 0) {
            paramNames = new String[ctx.functionArguments().LIDENT().size()];
            for (int i = 0; i < ctx.functionArguments().LIDENT().size(); i++) {
                paramNames[i] = ctx.functionArguments().LIDENT(i).getText();
            }
        }

        // Visit the body
        ExpressionNode body = visit(ctx.functionBody());

        return new FunctionDefinitionNode(functionName, paramNames, body);
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
        // This is handled by the parent visitor
        return new IntegerLiteralNode(0);
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
