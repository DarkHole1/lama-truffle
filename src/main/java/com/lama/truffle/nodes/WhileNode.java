package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for while-do expressions.
 * Evaluates condition before each iteration.
 * Both condition and body execute in a child scope.
 */
public class WhileNode extends ExpressionNode {

    @Child private ExpressionNode loopScope;  // ScopeEnterNode wrapping WhileLoopBodyNode

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
