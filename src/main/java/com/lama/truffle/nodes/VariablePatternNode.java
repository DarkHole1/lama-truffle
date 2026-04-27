package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VariablePatternNode extends PatternNode {

    private final String variableName;
    private final int slot;
    private final PatternNode nestedPatternNode;

    public VariablePatternNode(String variableName, int slot, PatternNode nestedPatternNode, int scrutineeSlot) {
        super(scrutineeSlot);
        this.variableName = variableName;
        this.slot = slot;
        this.nestedPatternNode = nestedPatternNode;
    }

    @Override
    public boolean executeBoolean(VirtualFrame frame) {
        Object value = frame.getObject(getScrutineeSlot());
        frame.setObject(slot, value);
        if (nestedPatternNode != null) {
            frame.setObject(getScrutineeSlot(), value);
            return nestedPatternNode.executeBoolean(frame);
        }
        return true;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getSlot() {
        return slot;
    }
}
