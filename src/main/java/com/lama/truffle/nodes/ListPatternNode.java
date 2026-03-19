package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * List pattern that matches list literals with specific element patterns.
 * Matches a fixed-length list: {p1, p2, ..., pn}
 */
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

        // If no element patterns, match empty list
        if (elementPatterns == null || elementPatterns.length == 0) {
            // Check if list is empty (null next means empty list in lama)
            return list.getNext() == null && list.getFirst() == null;
        }

        // Traverse the list and match each element
        List current = list;
        for (int i = 0; i < elementPatterns.length; i++) {
            if (current == null) {
                return false; // List too short
            }
            if (!elementPatterns[i].match(current.getFirst(), frame)) {
                return false;
            }
            current = current.getNext();
        }

        // List should have exactly the same length as patterns
        return current == null || (current.getNext() == null && current.getFirst() == null);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Execute element patterns for any side effects
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
