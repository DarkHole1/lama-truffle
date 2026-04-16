package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class WildcardPatternNode extends PatternNode {

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        return true; // Wildcard matches everything
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Binds nothing
        return null;
    }
}
