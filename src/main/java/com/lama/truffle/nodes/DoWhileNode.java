package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

public class DoWhileNode extends ExpressionNode {

    @Child private ExpressionNode loopScope;

    public DoWhileNode(ExpressionNode loopScope) {
        this.loopScope = loopScope;
    }

    @Specialization
    protected Object doDoWhile(VirtualFrame frame) {
        return loopScope.execute(frame);
    }

    public ExpressionNode getLoopScope() {
        return loopScope;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return doDoWhile(frame);
    }
}
