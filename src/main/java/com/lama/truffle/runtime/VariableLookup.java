package com.lama.truffle.runtime;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Utility class that encapsulates how to find and access a variable at runtime.
 * Created by Scope during AST building, used by VariableAccessNode and VariableWriteNode.
 * 
 * For normal access: traverses parent frames via the parent slot from frame descriptor info.
 * Captured access: uses the captureIndex to index into the captured frames array
 * stored in the captured frames slot of the current frame.
 */
public final class VariableLookup {

    private final int depth;   // number of parent traversals to reach target frame
    private final int slot;    // local slot index in the target frame

    // Mutable capture index, set during function capture collection at AST build time.
    // -1 means this variable is not captured (accessed via normal parent traversal).
    private int captureIndex = -1;

    public VariableLookup(int depth, int slot) {
        this.depth = depth;
        this.slot = slot;
    }

    /**
     * Normal read: traverse parent chain via the parent slot from frame descriptor info.
     */
    public Object read(VirtualFrame frame) {
        VirtualFrame target = Scope.getParentFrame(frame, depth);
        return target.getObject(slot);
    }

    /**
     * Normal write: traverse parent chain via the parent slot from frame descriptor info.
     */
    public void write(VirtualFrame frame, Object value) {
        VirtualFrame target = Scope.getParentFrame(frame, depth);
        target.setObject(slot, value);
    }

    /**
     * Sets the capture index for this lookup.
     * Called during function capture collection at AST build time.
     */
    public void setCaptureIndex(int index) {
        this.captureIndex = index;
    }

    public int getCaptureIndex() {
        return captureIndex;
    }

    public int getDepth() {
        return depth;
    }

    public int getSlot() {
        return slot;
    }
}
