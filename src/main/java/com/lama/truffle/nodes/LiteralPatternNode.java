package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class LiteralPatternNode extends PatternNode {

    private final Object literalValue;

    public LiteralPatternNode(Object literalValue, int scrutineeSlot) {
        super(scrutineeSlot);
        this.literalValue = literalValue;
    }

    @Override
    public boolean executeBoolean(VirtualFrame frame) {
        Object value = frame.getObject(getScrutineeSlot());
        if (literalValue == null) {
            return value == null;
        }
        return literalValue.equals(value);
    }

    public Object getLiteralValue() {
        return literalValue;
    }
}
