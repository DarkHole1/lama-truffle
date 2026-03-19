package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableDefinitionNode extends DefinitionNode {

    private final String variableName;
    @Child private ExpressionNode valueNode;

    public VariableDefinitionNode(String variableName, ExpressionNode valueNode) {
        this.variableName = variableName;
        this.valueNode = valueNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object value = (valueNode != null) ? valueNode.execute(frame) : 0;
        VariableEnvironment env = VariableEnvironment.getOrCreate(frame);
        env.set(variableName, value);
        return null;
    }

    public String getVariableName() {
        return variableName;
    }

    public ExpressionNode getValueNode() {
        return valueNode;
    }
}
