package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class WildcardPatternNode extends PatternNode {

    public WildcardPatternNode(int scrutineeSlot) {
        super(scrutineeSlot);
    }

    @Override
    public boolean executeBoolean(VirtualFrame frame) {
        return true; // Wildcard matches everything
    }
}
