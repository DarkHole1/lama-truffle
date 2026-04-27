package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class PatternNode extends Node {
    private final int scrutineeSlot;

    public PatternNode(int scrutineeSlot) {
        this.scrutineeSlot = scrutineeSlot;
    }

    public int getScrutineeSlot() {
        return scrutineeSlot;
    }

    abstract boolean executeBoolean(VirtualFrame frame);
}
