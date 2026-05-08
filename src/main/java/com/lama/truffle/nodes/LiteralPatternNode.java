package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class LiteralPatternNode extends PatternNode {

    private final Object literalValue;

    public LiteralPatternNode(Object literalValue) {
        this.literalValue = literalValue;
    }

    @Override
    public boolean executeBoolean(VirtualFrame frame, Object scrutinee) {
        if (literalValue == null) {
            return scrutinee == null;
        }
        return literalValue.equals(scrutinee);
    }

    public Object getLiteralValue() {
        return literalValue;
    }
}
