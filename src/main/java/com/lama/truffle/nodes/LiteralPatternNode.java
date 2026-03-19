package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Literal pattern that matches only specific literal values.
 */
public class LiteralPatternNode extends PatternNode {

    private final Object literalValue;

    public LiteralPatternNode(Object literalValue) {
        this.literalValue = literalValue;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        if (literalValue == null) {
            return value == null;
        }
        return literalValue.equals(value);
    }

    public Object getLiteralValue() {
        return literalValue;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Binds nothing
        return null;
    }
}
