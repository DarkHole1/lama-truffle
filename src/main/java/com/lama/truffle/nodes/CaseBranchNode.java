package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class CaseBranchNode extends ExpressionNode {

    private final PatternNode pattern;
    @Child private ExpressionNode body;

    public CaseBranchNode(PatternNode pattern, ExpressionNode body) {
        this.pattern = pattern;
        this.body = body;
    }

    public boolean matches(Object value, VirtualFrame frame) {
        return pattern.match(value, frame);
    }

    public Object execute(VirtualFrame frame) {
        return body.execute(frame);
    }

    public PatternNode getPattern() {
        return pattern;
    }

    public ExpressionNode getBody() {
        return body;
    }
}
