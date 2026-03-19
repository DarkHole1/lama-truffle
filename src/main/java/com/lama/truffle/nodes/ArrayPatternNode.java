package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Array pattern that matches arrays with specific element patterns.
 */
public class ArrayPatternNode extends PatternNode {

    @Children private PatternNode[] elementPatterns;

    public ArrayPatternNode(PatternNode[] elementPatterns) {
        this.elementPatterns = elementPatterns;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        if (value == null || !value.getClass().isArray()) {
            return false;
        }

        Object[] array = (Object[]) value;

        // If no element patterns, match empty array
        if (elementPatterns == null || elementPatterns.length == 0) {
            return array.length == 0;
        }

        // Check number of elements matches number of patterns
        if (array.length != elementPatterns.length) {
            return false;
        }

        // Recursively match each element pattern against corresponding array element
        for (int i = 0; i < elementPatterns.length; i++) {
            if (!elementPatterns[i].match(array[i], frame)) {
                return false;
            }
        }

        return true;
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
