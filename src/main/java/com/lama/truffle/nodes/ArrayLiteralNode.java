package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class ArrayLiteralNode extends ExpressionNode {
    @Children ExpressionNode[] expressions;

    ArrayLiteralNode(ExpressionNode[] expressions) {
        this.expressions = expressions;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // TODO Auto-generated method stub
        return null;
    }
}
