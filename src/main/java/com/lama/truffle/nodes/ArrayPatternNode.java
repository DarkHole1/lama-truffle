package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class ArrayPatternNode extends PatternNode {

    @Children private PatternNode[] elementPatterns;

    public ArrayPatternNode(PatternNode[] elementPatterns, int scrutineeSlot) {
        super(scrutineeSlot);
        this.elementPatterns = elementPatterns;
    }

    @Override @ExplodeLoop
    public boolean executeBoolean(VirtualFrame frame) {
        Object value = frame.getObject(getScrutineeSlot());
        if (value == null || !value.getClass().isArray()) {
            return false;
        }

        Object[] array = (Object[]) value;

        if (elementPatterns == null || elementPatterns.length == 0) {
            return array.length == 0;
        }

        if (array.length != elementPatterns.length) {
            return false;
        }

        for (int i = 0; i < elementPatterns.length; i++) {
            frame.setObject(getScrutineeSlot(), array[i]);
            if (!elementPatterns[i].executeBoolean(frame)) {
                return false;
            }
        }

        return true;
    }

    public PatternNode[] getElementPatterns() {
        return elementPatterns;
    }
}
