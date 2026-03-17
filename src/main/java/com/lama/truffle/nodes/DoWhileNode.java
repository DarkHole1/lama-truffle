package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for do-while expressions.
 * Evaluates condition after each iteration (body executes at least once).
 */
public class DoWhileNode extends ExpressionNode {

    @Child private ExpressionNode body;
    @Child private ExpressionNode condition;

    public DoWhileNode(ExpressionNode body, ExpressionNode condition) {
        this.body = body;
        this.condition = condition;
    }

    @Specialization
    protected Object doDoWhile(VirtualFrame frame) {
        Object lastResult = 0;

        do {
            lastResult = body.execute(frame);
            Object conditionValue = condition.execute(frame);
            long cond = conditionValue instanceof Number ? ((Number) conditionValue).longValue() : 0;

            if (cond == 0) { // Zero is false
                break;
            }
        } while (true);

        return lastResult;
    }

    public ExpressionNode getBody() {
        return body;
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return doDoWhile(frame);
    }
}
