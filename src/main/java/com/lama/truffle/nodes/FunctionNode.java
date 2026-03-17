package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.VirtualFrame;

public class FunctionNode extends ExpressionNode {
    private final String name;
    private final String[] argsNames;
    @Child private ExpressionNode body;

    public FunctionNode(String[] argsNames, ExpressionNode body) {
        this(null, argsNames, body);
    }

    public FunctionNode(String name, String[] argsNames, ExpressionNode body) {
        this.name = name;
        this.argsNames = argsNames;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public String[] getArgsNames() {
        return argsNames;
    }

    public ExpressionNode getBody() {
        return body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Create a closure that captures the current frame
        Closure closure = new Closure(name, argsNames, body, frame);
        
        // If this is a named function, register it in the function registry
        if (name != null) {
            FunctionRegistry.register(name, closure);
        }
        
        return closure;
    }
}
