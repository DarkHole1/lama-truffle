package com.lama.truffle.nodes;

public abstract class VariableDefinitionNode extends DefinitionNode {

    private final String variableName;
    private final int slot;
    @Child private ExpressionNode valueNode;

    public VariableDefinitionNode(String variableName, int slot, ExpressionNode valueNode) {
        this.variableName = variableName;
        this.slot = slot;
        this.valueNode = valueNode;
    }

    public static ExpressionNode create(String variableName, int slot, ExpressionNode valueNode) {
        return WriteLocalVariableNodeGen.create(valueNode, slot);
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
