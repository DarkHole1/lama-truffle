package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class WildcardPatternNode extends PatternNode {
    @Override
    public boolean executeBoolean(VirtualFrame frame, Object value) {
        return true; // Wildcard matches everything
    }
}
