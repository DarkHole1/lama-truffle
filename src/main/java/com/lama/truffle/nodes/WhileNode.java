package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for while-do expressions.
 * Evaluates condition before each iteration.
 */
public class WhileNode extends ExpressionNode {

    @Child private ExpressionNode condition;
    @Child private ExpressionNode body;

    public WhileNode(ExpressionNode condition, ExpressionNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Specialization
    protected Object doWhile(VirtualFrame frame) {
        Object lastResult = 0;

        while (true) {
            Object conditionValue = condition.execute(frame);
            long cond = conditionValue instanceof Number ? ((Number) conditionValue).longValue() : 0;

            if (cond == 0) { // Zero is false
                break;
            }

            lastResult = body.execute(frame);
        }

        return lastResult;
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    public ExpressionNode getBody() {
        return body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return doWhile(frame);
    }
}
