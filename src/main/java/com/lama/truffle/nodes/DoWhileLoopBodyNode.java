package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class DoWhileLoopBodyNode extends ExpressionNode {

    @Child private ExpressionNode body;
    @Child private ExpressionNode condition;

    public DoWhileLoopBodyNode(ExpressionNode body, ExpressionNode condition) {
        this.body = body;
        this.condition = condition;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object lastResult = 0;

        do {
            lastResult = body.execute(frame);
            Object conditionValue = condition.execute(frame);
            long cond = conditionValue instanceof Number ? ((Number) conditionValue).longValue() : 0;

            if (cond == 0) {
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
}
