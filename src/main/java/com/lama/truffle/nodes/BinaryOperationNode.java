package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.lama.truffle.types.List;

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
    protected long doLongLong(long left, long right) {
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
                return left == right ? 1 : 0;
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
            default:
                throw new UnsupportedOperationException("Operator " + operator + " not supported for integers");
        }
    }

    @Specialization
    protected Object doObjectObject(Object left, Object right) {
        switch (operator) {
            case CONS:
                return new List(left, (List) right);

            default:
                throw new UnsupportedOperationException("Operator " + operator + " not supported for objects");
        }
    }

    @Override
    public Object execute(VirtualFrame frame) {
        throw new RuntimeException("Generic execution should not be called - specialization should handle this");
    }
}