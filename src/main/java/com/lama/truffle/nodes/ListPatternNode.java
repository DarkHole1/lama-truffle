package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class ListPatternNode extends PatternNode {
    @Children private final PatternNode[] elementPatterns;

    public ListPatternNode(PatternNode[] elementPatterns, int scrutineeSlot) {
        super(scrutineeSlot);
        this.elementPatterns = elementPatterns;
    }

    @Override @ExplodeLoop
    public boolean executeBoolean(VirtualFrame frame) {
        Object value = frame.getObject(getScrutineeSlot());

        if (!(value instanceof List)) {
            return false;
        }

        List list = (List) value;

        if (elementPatterns == null || elementPatterns.length == 0) {
            return list.getNext() == null && list.getFirst() == null;
        }

        List current = list;
        for (int i = 0; i < elementPatterns.length; i++) {
            if (current == null) {
                return false; 
            }
            frame.setObject(getScrutineeSlot(), current.getFirst());
            if (!elementPatterns[i].executeBoolean(frame)) {
                return false;
            }
            current = current.getNext();
        }

        return current == null || (current.getNext() == null && current.getFirst() == null);
    }

    public PatternNode[] getElementPatterns() {
        return elementPatterns;
    }
}
