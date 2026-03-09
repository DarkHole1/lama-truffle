package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class FunctionNode extends ExpressionNode {
    
    private final String functionName;
    @Children private final ExpressionNode[] argumentNodes;

    public FunctionNode(String functionName, ExpressionNode[] argumentNodes) {
        this.functionName = functionName;
        this.argumentNodes = argumentNodes;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Execute all argument nodes first
        Object[] arguments = new Object[argumentNodes.length];
        for (int i = 0; i < argumentNodes.length; i++) {
            arguments[i] = argumentNodes[i].execute(frame);
        }
        
        // In a real implementation, this would call the actual function
        // For now, returning a placeholder
        return 0; // Placeholder - actual implementation would call the function
    }

    public String getFunctionName() {
        return functionName;
    }
}