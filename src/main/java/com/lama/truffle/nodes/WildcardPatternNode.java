package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Wildcard pattern (_) that matches any value.
 */
public class WildcardPatternNode extends PatternNode {

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        return true; // Wildcard matches everything
    }
}
