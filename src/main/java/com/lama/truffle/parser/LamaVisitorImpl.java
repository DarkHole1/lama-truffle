package com.lama.truffle.parser;

import com.lama.truffle.nodes.*;

import static com.lama.truffle.nodes.BinaryOperationNode.BinaryOperator.*;

public class LamaVisitorImpl extends LamaBaseVisitor<ExpressionNode> {

    @Override
    public ExpressionNode visitCompilationUnit(LamaParser.CompilationUnitContext ctx) {
        // TODO
        if (ctx.scopeExpression() != null) {
            return visit(ctx.scopeExpression());
        }
        return null;
    }

    @Override
    public ExpressionNode visitScopeExpression(LamaParser.ScopeExpressionContext ctx) {
        if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        return new IntegerLiteralNode(0);
    }

    @Override
    public ExpressionNode visitExpression(LamaParser.ExpressionContext ctx) {
        if (ctx.expression().size() > 1) {
            // TODO
            return visit(ctx.expression(ctx.expression().size() - 1));
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
        if (ctx.getChildCount() > 1 && ctx.getChild(1).getText().equals(":=")) { // Assignment operator
            // Handle assignment: variable := expression
            // TODO
            ExpressionNode right = visit(ctx.assignmentExpr());
            // ExpressionNode left = visit(ctx.listConstructorExpr());
            // For now, return the right side (in a real implementation, this would create an assignment node)
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
                
                BinaryOperationNode.BinaryOperator op = 
                    "+".equals(opText) ? ADD : SUBTRACT;
                
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
    public ExpressionNode visitPrimary(LamaParser.PrimaryContext ctx) {
        if (ctx.DECIMAL() != null) {
            int value = Integer.parseInt(ctx.DECIMAL().getText());
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
        } else if (ctx.getChild(0) != null && ctx.getChild(0).getText().equals("(")) {
            // Handle parenthesized expressions
            return visit(ctx.scopeExpression());
        }
        
        // Handle other primary expressions (functions, lists, arrays, etc.)
        return new IntegerLiteralNode(0); // Default fallback
    }

    private BinaryOperationNode.BinaryOperator fromComparisonOperator(String opText) {
        switch (opText) {
            case "==": return EQUAL;
            case "!=": return NOT_EQUAL;
            case "<": return LESS;
            case "<=": return LESS_EQUAL;
            case ">": return GREATER;
            case ">=": return GREATER_EQUAL;
            default: throw new IllegalArgumentException("Unknown comparison operator: " + opText);
        }
    }
}
