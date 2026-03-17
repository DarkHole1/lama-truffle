package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for for expressions.
 * Syntax: for init, condition, update do body od
 * The init is executed once, then condition is checked before each iteration,
 * and update is executed after each iteration.
 */
public class ForNode extends ExpressionNode {

    @Child private ExpressionNode init;
    @Child private ExpressionNode condition;
    @Child private ExpressionNode update;
    @Child private ExpressionNode body;

    public ForNode(ExpressionNode init, ExpressionNode condition,
                   ExpressionNode update, ExpressionNode body) {
        this.init = init;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Specialization
    protected Object doFor(VirtualFrame frame) {
        Object lastResult = 0;

        // Execute initialization once
        if (init != null) {
            init.execute(frame);
        }

        // Loop while condition is true (non-zero)
        while (true) {
            Object conditionValue = condition.execute(frame);
            long cond = conditionValue instanceof Number ? ((Number) conditionValue).longValue() : 0;

            if (cond == 0) { // Zero is false
                break;
            }

            lastResult = body.execute(frame);

            // Execute update after each iteration
            if (update != null) {
                update.execute(frame);
            }
        }

        return lastResult;
    }

    public ExpressionNode getInit() {
        return init;
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

    @Override
    public Object execute(VirtualFrame frame) {
        return doFor(frame);
    }
}
