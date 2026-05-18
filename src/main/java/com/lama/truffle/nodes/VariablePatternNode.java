package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VariablePatternNode extends PatternNode {

    private final String variableName;
    private final int slot;
    @Child private PatternNode nestedPatternNode;
    @Child private WriteLocalVariableNode writeVariableNode;

    public VariablePatternNode(String variableName, int slot, PatternNode nestedPatternNode) {
        this.variableName = variableName;
        this.slot = slot;
        if (nestedPatternNode != null) {
            this.nestedPatternNode = nestedPatternNode;
        } else {
            this.nestedPatternNode = new WildcardPatternNode();
        }
        this.writeVariableNode = WriteLocalVariableNodeGen.create(null, slot);
    }

    @Override
    public boolean executeBoolean(VirtualFrame frame, Object value) {
        writeVariableNode.executeWrite(frame, value);
        return nestedPatternNode.executeBoolean(frame, value);
    }

    public String getVariableName() {
        return variableName;
    }

    public int getSlot() {
        return slot;
    }
}
