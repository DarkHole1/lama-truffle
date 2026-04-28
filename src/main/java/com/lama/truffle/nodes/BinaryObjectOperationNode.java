package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.lama.truffle.types.List;

@NodeChild("left")
@NodeChild("right")
public abstract class BinaryObjectOperationNode extends ExpressionNode {

    public enum BinaryOperator {
        CONS(":");

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

    public BinaryObjectOperationNode(BinaryOperator operator) {
        this.operator = operator;
    }

    public BinaryOperator getOperator() {
        return operator;
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