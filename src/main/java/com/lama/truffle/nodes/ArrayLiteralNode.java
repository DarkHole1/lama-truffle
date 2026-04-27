package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class ArrayLiteralNode extends ExpressionNode {
    @Children private final ExpressionNode[] expressions;

    public ArrayLiteralNode(ExpressionNode[] expressions) {
        this.expressions = expressions;
    }

    @Override @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        Object[] result = new Object[expressions.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = expressions[i].execute(frame);
        }
        return result;
    }
}
