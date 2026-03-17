package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.VirtualFrame;

public class FunctionCallNode extends ExpressionNode {

    private final String functionName;
    @Children private final ExpressionNode[] argumentNodes;

    public FunctionCallNode(String functionName, ExpressionNode[] argumentNodes) {
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

        // Try to get the function from the registry
        Closure closure = FunctionRegistry.get(functionName);
        
        if (closure != null) {
            return closure.execute(arguments);
        } else {
            throw new RuntimeException("Function '" + functionName + "' is not defined or is not callable");
        }
    }

    public String getFunctionName() {
        return functionName;
    }
}