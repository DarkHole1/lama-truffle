package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VariablePatternNode extends PatternNode {

    private final String variableName;
    private final int slot;
    private final PatternNode nestedPatternNode;

    public VariablePatternNode(String variableName, int slot, PatternNode nestedPatternNode) {
        this.variableName = variableName;
        this.slot = slot;
        this.nestedPatternNode = nestedPatternNode;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        frame.setObject(slot, value);
        if (nestedPatternNode != null) {
            return nestedPatternNode.match(value, frame);
        }
        return true;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getSlot() {
        return slot;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return frame.getObject(slot);
    }
}
