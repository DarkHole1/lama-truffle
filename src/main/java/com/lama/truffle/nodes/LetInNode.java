package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class LetInNode extends ExpressionNode {

    @Child private ExpressionNode boundExpression;
    @Child private ExpressionNode body;
    private final PatternNode pattern;
    private final int scrutineeSlot;

    public LetInNode(ExpressionNode boundExpression, ExpressionNode body, PatternNode pattern, int scrutineeSlot) {
        this.boundExpression = boundExpression;
        this.body = body;
        this.pattern = pattern;
        this.scrutineeSlot = scrutineeSlot;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object value = boundExpression.execute(frame);
        frame.setObject(scrutineeSlot, value);

        if (!pattern.executeBoolean(frame)) {
            throw new RuntimeException("Pattern match failed in let expression");
        }
        
        return body.execute(frame);
    }

    public ExpressionNode getBoundExpression() {
        return boundExpression;
    }

    public ExpressionNode getBody() {
        return body;
    }

    public PatternNode getPattern() {
        return pattern;
    }
}
