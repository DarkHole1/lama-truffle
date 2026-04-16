package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class ForNode extends ExpressionNode {

    @Child private ExpressionNode init;
    @Child private ExpressionNode loopScope;

    public ForNode(ExpressionNode init, ExpressionNode loopScope) {
        this.init = init;
        this.loopScope = loopScope;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object lastResult = 0;

        if (init != null) {
            init.execute(frame);
        }

        lastResult = loopScope.execute(frame);

        return lastResult;
    }

    public ExpressionNode getInit() {
        return init;
    }

    public ExpressionNode getLoopScope() {
        return loopScope;
    }
}
