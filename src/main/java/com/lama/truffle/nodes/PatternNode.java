package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.SourceSection;

public abstract class PatternNode extends Node {
    private SourceSection section;

    abstract boolean executeBoolean(VirtualFrame frame, Object scrutinee);

    public void setSourceSection(SourceSection section) {
        this.section = section;
    }

    @Override
    public SourceSection getSourceSection() {
        return section;
    }
}
