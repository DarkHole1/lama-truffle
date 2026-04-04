package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Internal loop body for for-expressions.
 * Executes condition, body, and update in the same frame (the loop's scope).
 */
public class ForLoopBodyNode extends ExpressionNode {

    @Child private ExpressionNode condition;
    @Child private ExpressionNode update;
    @Child private ExpressionNode body;

    public ForLoopBodyNode(ExpressionNode condition, ExpressionNode update, ExpressionNode body) {
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object lastResult = 0;

        while (true) {
            Object conditionValue = condition.execute(frame);
            long cond = conditionValue instanceof Number ? ((Number) conditionValue).longValue() : 0;

            if (cond == 0) {
                break;
            }

            lastResult = body.execute(frame);

            if (update != null) {
                update.execute(frame);
            }
        }

        return lastResult;
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    public ExpressionNode getUpdate() {
        return update;
    }

    public ExpressionNode getBody() {
        return body;
    }
}
