package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableAccessNode extends ExpressionNode {

    private final String variableName;

    public VariableAccessNode(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        VariableEnvironment env = VariableEnvironment.getOrCreate(frame);
        return env.get(variableName);
    }

    public String getVariableName() {
        return variableName;
    }
}
