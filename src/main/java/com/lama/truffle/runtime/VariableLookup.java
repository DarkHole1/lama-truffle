package com.lama.truffle.runtime;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Utility class that encapsulates how to find and access a variable at runtime.
 * Created by Scope during AST building, used by VariableAccessNode and VariableWriteNode.
 * 
 * For normal access: traverses parent frames via slot 0 references.
 * For captured access: indexes into a shared captured-frames array.
 */
public final class VariableLookup {

    private final int depth;   // number of parent traversals to reach target frame
    private final int slot;    // local slot index in the target frame

    // Capture support
    private int captureListIndex = -1;           // -1 means not captured
    private VirtualFrame[] capturedFrames;       // shared array of captured frames

    public VariableLookup(int depth, int slot) {
        this.depth = depth;
        this.slot = slot;
    }

    /**
     * Normal read: traverse parent chain via slot 0 references.
     */
    public Object read(VirtualFrame frame) {
        VirtualFrame current = frame;
        for (int i = 0; i < depth; i++) {
            current = (VirtualFrame) current.getObject(Scope.getParentSlot());
        }
        return current.getObject(slot);
    }

    /**
     * Captured read: use the captured frames array.
     */
    public Object readCaptured(VirtualFrame frame) {
        return capturedFrames[captureListIndex].getObject(slot);
    }

    /**
     * Normal write: traverse parent chain via slot 0 references.
     */
    public void write(VirtualFrame frame, Object value) {
        VirtualFrame current = frame;
        for (int i = 0; i < depth; i++) {
            current = (VirtualFrame) current.getObject(Scope.getParentSlot());
        }
        current.setObject(slot, value);
    }

    /**
     * Captured write: use the captured frames array.
     */
    public void writeCaptured(VirtualFrame frame, Object value) {
        capturedFrames[captureListIndex].setObject(slot, value);
    }

    /**
     * Called during closure creation to set up capture information.
     * @param listIndex index into the captured frames array
     * @param frames the shared captured frames array
     */
    public void markCaptured(int listIndex, VirtualFrame[] frames) {
        this.captureListIndex = listIndex;
        this.capturedFrames = frames;
    }

    /**
     * Returns true if this variable has been marked as captured.
     */
    public boolean isCaptured() {
        return captureListIndex >= 0;
    }

    public int getDepth() {
        return depth;
    }

    public int getSlot() {
        return slot;
    }
}
