package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class ArrayLiteralNode extends ExpressionNode {
    @Children ExpressionNode[] expressions;

    ArrayLiteralNode(ExpressionNode[] expressions) {
        this.expressions = expressions;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object[] result = new Object[expressions.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = expressions[i].execute(frame);
        }
        return result;
    }
}
