package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.oracle.truffle.api.frame.VirtualFrame;

public class ListPatternNode extends PatternNode {
    @Children private PatternNode[] elementPatterns;

    public ListPatternNode(PatternNode[] elementPatterns) {
        this.elementPatterns = elementPatterns;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
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
            if (!elementPatterns[i].match(current.getFirst(), frame)) {
                return false;
            }
            current = current.getNext();
        }

        return current == null || (current.getNext() == null && current.getFirst() == null);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        if (elementPatterns != null) {
            for (PatternNode pattern : elementPatterns) {
                pattern.execute(frame);
            }
        }
        return null;
    }

    public PatternNode[] getElementPatterns() {
        return elementPatterns;
    }
}
