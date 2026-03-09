package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableAccessNode extends ExpressionNode {
    
    private final String variableName;

    public VariableAccessNode(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // In a real implementation, this would access the variable from the frame
        // For now, returning a placeholder
        return 0; // Placeholder - actual implementation would access frame variables
    }

    public String getVariableName() {
        return variableName;
    }
}
