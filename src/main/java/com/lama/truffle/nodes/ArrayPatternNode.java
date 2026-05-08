package com.lama.truffle.nodes;

import com.lama.truffle.types.Array;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class ArrayPatternNode extends PatternNode {

    @Children private PatternNode[] elementPatterns;

    public ArrayPatternNode(PatternNode[] elementPatterns) {
        this.elementPatterns = elementPatterns;
    }

    @Override @ExplodeLoop
    public boolean executeBoolean(VirtualFrame frame, Object value) {
        if (!(value instanceof Array)) {
            return false;
        }

        Array array = (Array) value;

        if (elementPatterns == null || elementPatterns.length == 0) {
            return array.length() == 0;
        }

        if (array.length() != elementPatterns.length) {
            return false;
        }

        for (int i = 0; i < elementPatterns.length; i++) {
            if (!elementPatterns[i].executeBoolean(frame, array.getIndex(i))) {
                return false;
            }
        }

        return true;
    }

    public PatternNode[] getElementPatterns() {
        return elementPatterns;
    }
}
