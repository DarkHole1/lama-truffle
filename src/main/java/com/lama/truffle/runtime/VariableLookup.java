package com.lama.truffle.runtime;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Utility class that encapsulates how to find and access a variable at runtime.
 * Created by Scope during AST building, used by VariableAccessNode and VariableWriteNode.
 *
 * Parent frame traversal is done via frame.getArguments()[0].
 * depth=0 means same frame (shared builder), depth>0 means traverse parent chain.
 */
public final class VariableLookup {

    private final int depth;   // number of parent traversals via arguments[0] to reach target frame
    private final int slot;    // local slot index in the target frame

    public VariableLookup(int depth, int slot) {
        this.depth = depth;
        this.slot = slot;
    }

    /**
     * Read variable: traverse parent chain via arguments[0].
     */
    public Object read(VirtualFrame frame) {
        VirtualFrame target = Scope.getParentFrame(frame, depth);
        return target.getObject(slot);
    }

    /**
     * Write variable: traverse parent chain via arguments[0].
     */
    public void write(VirtualFrame frame, Object value) {
        VirtualFrame target = Scope.getParentFrame(frame, depth);
        target.setObject(slot, value);
    }

    public int getDepth() {
        return depth;
    }

    public int getSlot() {
        return slot;
    }
}
