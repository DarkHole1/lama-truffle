package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class WhileLoopBodyNode extends ExpressionNode {

    @Child private ExpressionNode condition;
    @Child private ExpressionNode body;

    public WhileLoopBodyNode(ExpressionNode condition, ExpressionNode body) {
        this.condition = condition;
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
        }

        return lastResult;
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    public ExpressionNode getBody() {
        return body;
    }
}
