package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.SourceSection;

public abstract class PatternNode extends Node {
    private final int scrutineeSlot;
    private SourceSection section;

    public PatternNode(int scrutineeSlot) {
        this.scrutineeSlot = scrutineeSlot;
    }

    public int getScrutineeSlot() {
        return scrutineeSlot;
    }

    abstract boolean executeBoolean(VirtualFrame frame);

    public void setSourceSection(SourceSection section) {
        this.section = section;
    }

    @Override
    public SourceSection getSourceSection() {
        return section;
    }
}
