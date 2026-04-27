package com.lama.truffle.runtime;

import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.VirtualFrame;

public final class VariableLookup {
    private final int depth;
    private final int slot;

    public VariableLookup(int depth, int slot) {
        this.depth = depth;
        this.slot = slot;
    }

    public Object read(VirtualFrame frame) {
        Frame target = Scope.getParentFrame(frame, depth);
        return target.getObject(slot);
    }

    public void write(VirtualFrame frame, Object value) {
        Frame target = Scope.getParentFrame(frame, depth);
        target.setObject(slot, value);
    }

    public int getDepth() {
        return depth;
    }

    public int getSlot() {
        return slot;
    }
}
