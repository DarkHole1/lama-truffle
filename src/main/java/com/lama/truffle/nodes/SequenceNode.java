package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class SequenceNode extends ExpressionNode {
    @Children private ExpressionNode[] expressions;

    public SequenceNode(ExpressionNode[] expressions) {
        this.expressions = expressions;
    }

    public ExpressionNode[] getExpressions() {
        return expressions;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object result = null;
        for (ExpressionNode expression : expressions) {
            result = expression.execute(frame);
        }
        return result;
    }
}
