package com.lama.truffle.runtime;

import com.oracle.truffle.api.frame.VirtualFrame;

public final class VariableLookup {
    private final int depth;
    private final int slot;

    public VariableLookup(int depth, int slot) {
        this.depth = depth;
        this.slot = slot;
    }

    public VirtualFrame getFrame(VirtualFrame frame) {
        return (VirtualFrame) Scope.getParentFrame(frame, depth);
    }

    public int getDepth() {
        return depth;
    }

    public int getSlot() {
        return slot;
    }
}
