package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

public class WhileNode extends ExpressionNode {
    @Child private ExpressionNode loopScope;

    public WhileNode(ExpressionNode loopScope) {
        this.loopScope = loopScope;
    }

    @Specialization
    protected Object doWhile(VirtualFrame frame) {
        return loopScope.execute(frame);
    }

    public ExpressionNode getLoopScope() {
        return loopScope;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return doWhile(frame);
    }
}
