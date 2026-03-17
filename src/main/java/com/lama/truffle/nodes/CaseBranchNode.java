package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

/**
 * Represents a single branch in a case expression.
 * Contains a pattern to match against and a body to execute if matched.
 */
public class CaseBranchNode extends Node {

    private final PatternNode pattern;
    @Child private ExpressionNode body;

    public CaseBranchNode(PatternNode pattern, ExpressionNode body) {
        this.pattern = pattern;
        this.body = body;
    }

    /**
     * Check if the value matches this branch's pattern.
     * If it matches and has variable bindings, they are stored in the frame.
     */
    public boolean matches(Object value, VirtualFrame frame) {
        return pattern.match(value, frame);
    }

    /**
     * Execute the body of this branch.
     * Should only be called after matches() returned true.
     */
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
