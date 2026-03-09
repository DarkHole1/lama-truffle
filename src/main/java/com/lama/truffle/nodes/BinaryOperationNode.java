package com.lama.truffle.nodes;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

@NodeChild("left")
@NodeChild("right")
public abstract class BinaryOperationNode extends ExpressionNode {

    public enum BinaryOperator {
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        MODULO("%"),
        EQUAL("=="),
        NOT_EQUAL("!="),
        LESS("<"),
        LESS_EQUAL("<="),
        GREATER(">"),
        GREATER_EQUAL(">="),
        LOGICAL_AND("&&"),
        LOGICAL_OR("!!"),
        CONS(":"); // For list construction

        private final String symbol;

        BinaryOperator(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public static BinaryOperator fromString(String op) {
            for (BinaryOperator operator : values()) {
                if (operator.getSymbol().equals(op)) {
                    return operator;
                }
            }
            throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    protected final BinaryOperator operator;

    public BinaryOperationNode(BinaryOperator operator) {
        this.operator = operator;
    }

    public BinaryOperator getOperator() {
        return operator;
    }

    @Specialization
    protected int doIntInt(int left, int right) {
        switch (operator) {
            case ADD:
                return left + right;
            case SUBTRACT:
                return left - right;
            case MULTIPLY:
                return left * right;
            case DIVIDE:
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return left / right;
            case MODULO:
                return left % right;
            case EQUAL:
                return left == right ? 1 : 0; // Return 1 for true, 0 for false
            case NOT_EQUAL:
                return left != right ? 1 : 0;
            case LESS:
                return left < right ? 1 : 0;
            case LESS_EQUAL:
                return left <= right ? 1 : 0;
            case GREATER:
                return left > right ? 1 : 0;
            case GREATER_EQUAL:
                return left >= right ? 1 : 0;
            case CONS:
                // For list construction, we'll return a simple representation
                // In a real implementation, this would create a proper list structure
                return left; // Placeholder
            default:
                throw new UnsupportedOperationException("Operator " + operator + " not supported for integers");
        }
    }

    @Specialization
    protected int doDoubleDouble(double left, double right) {
        switch (operator) {
            case ADD:
                return (int) (left + right);
            case SUBTRACT:
                return (int) (left - right);
            case MULTIPLY:
                return (int) (left * right);
            case DIVIDE:
                if (right == 0.0) {
                    throw new ArithmeticException("Division by zero");
                }
                return (int) (left / right);
            case MODULO:
                return (int) (left % right);
            case EQUAL:
                return Math.abs(left - right) < 1e-9 ? 1 : 0;
            case NOT_EQUAL:
                return Math.abs(left - right) >= 1e-9 ? 1 : 0;
            case LESS:
                return left < right ? 1 : 0;
            case LESS_EQUAL:
                return left <= right ? 1 : 0;
            case GREATER:
                return left > right ? 1 : 0;
            case GREATER_EQUAL:
                return left >= right ? 1 : 0;
            default:
                throw new UnsupportedOperationException("Operator " + operator + " not supported for doubles");
        }
    }

    @Specialization
    protected int doBooleanBoolean(boolean left, boolean right) {
        switch (operator) {
            case LOGICAL_AND:
                return left && right ? 1 : 0;
            case LOGICAL_OR:
                return left || right ? 1 : 0;
            case EQUAL:
                return left == right ? 1 : 0;
            case NOT_EQUAL:
                return left != right ? 1 : 0;
            default:
                throw new UnsupportedOperationException("Operator " + operator + " not supported for booleans");
        }
    }

    @TruffleBoundary
    @Specialization
    protected String doStringString(String left, String right) {
        if (operator == BinaryOperator.ADD) {
            return left + right; // String concatenation
        }
        throw new UnsupportedOperationException("Operator " + operator + " not supported for strings");
    }

    @Override
    public Object execute(VirtualFrame frame) {
        throw new RuntimeException("Generic execution should not be called - specialization should handle this");
    }
}