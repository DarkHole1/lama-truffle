package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableDefinitionNode extends DefinitionNode {

    private final String variableName;
    private final int slot;
    @Child private ExpressionNode valueNode;

    public VariableDefinitionNode(String variableName, int slot, ExpressionNode valueNode) {
        this.variableName = variableName;
        this.slot = slot;
        this.valueNode = valueNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object value = (valueNode != null) ? valueNode.execute(frame) : 0;
        frame.setObject(slot, value);
        return null;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getSlot() {
        return slot;
    }

    public ExpressionNode getValueNode() {
        return valueNode;
    }
}
