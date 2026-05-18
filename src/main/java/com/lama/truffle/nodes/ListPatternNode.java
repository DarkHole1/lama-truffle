package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.lama.truffle.types.ListCons;
import com.lama.truffle.types.ListNil;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class ListPatternNode extends PatternNode {
    @Children
    private final PatternNode[] elementPatterns;

    public ListPatternNode(PatternNode[] elementPatterns) {
        this.elementPatterns = elementPatterns;
    }

    @Override
    @ExplodeLoop
    public boolean executeBoolean(VirtualFrame frame, Object value) {
        if (!(value instanceof List)) {
            return false;
        }

        List list = (List) value;

        if (elementPatterns == null || elementPatterns.length == 0) {
            return list instanceof ListNil;
        }

        List current = list;
        for (int i = 0; i < elementPatterns.length; i++) {
            if (current instanceof ListCons c) {
                if (!elementPatterns[i].executeBoolean(frame, c.getFirst())) {
                    return false;
                }

                current = c.getNext();
            } else {
                return false;
            }
        }

        return current instanceof ListNil;
    }

    public PatternNode[] getElementPatterns() {
        return elementPatterns;
    }
}
