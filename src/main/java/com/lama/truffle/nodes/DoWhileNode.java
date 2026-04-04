package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for do-while expressions.
 * Evaluates condition after each iteration (body executes at least once).
 * Both body and condition execute in a child scope.
 */
public class DoWhileNode extends ExpressionNode {

    @Child private ExpressionNode loopScope;  // ScopeEnterNode wrapping DoWhileLoopBodyNode

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
