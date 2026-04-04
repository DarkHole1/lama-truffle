package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for for expressions.
 * Syntax: for init, condition, update do body od
 * The init is executed once in the parent scope, then a child scope is created
 * for the loop (condition, update, body all run in the child scope).
 */
public class ForNode extends ExpressionNode {

    @Child private ExpressionNode init;
    @Child private ExpressionNode loopScope;  // ScopeEnterNode wrapping ForLoopBodyNode

    public ForNode(ExpressionNode init, ExpressionNode loopScope) {
        this.init = init;
        this.loopScope = loopScope;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object lastResult = 0;

        // Execute initialization once in the parent scope
        if (init != null) {
            init.execute(frame);
        }

        // Execute the loop in the child scope
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
