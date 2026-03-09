package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableWriteNode extends ExpressionNode {
    
    private final String variableName;
    @Child private ExpressionNode valueNode;

    public VariableWriteNode(String variableName, ExpressionNode valueNode) {
        this.variableName = variableName;
        this.valueNode = valueNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // In a real implementation, this would write the variable to the frame
        // For now, returning the value as a placeholder
        Object value = valueNode.execute(frame);
        return value; // Placeholder - actual implementation would store in frame
    }

    public String getVariableName() {
        return variableName;
    }
}