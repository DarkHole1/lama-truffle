package com.lama.truffle.types;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.nodes.VariableEnvironment;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a closure (function value) that captures the execution context.
 * A closure contains the function body, parameter names, and the captured variables.
 */
public final class Closure {
    private final String name;
    private final String[] parameterNames;
    private final ExpressionNode body;
    private final VariableEnvironment capturedEnvironment;

    public Closure(String name, String[] parameterNames, ExpressionNode body, VirtualFrame capturedFrame) {
        this.name = name;
        this.parameterNames = parameterNames;
        this.body = body;
        // Capture the variable environment from the frame
        this.capturedEnvironment = VariableEnvironment.getOrCreate(capturedFrame);
    }

    public String getName() {
        return name;
    }

    public String[] getParameterNames() {
        return parameterNames;
    }

    public ExpressionNode getBody() {
        return body;
    }

    public VariableEnvironment getCapturedEnvironment() {
        return capturedEnvironment;
    }

    /**
     * Executes this closure with the given arguments.
     * Creates a new frame scope, binds parameters, and executes the body.
     */
    public Object execute(VirtualFrame frame, Object[] arguments) {
        // Create a new variable environment for this function call
        VariableEnvironment newEnv = VariableEnvironment.getOrCreate(frame);
        
        // Save the current environment state to restore later (for nested calls)
        Map<String, Object> savedVars = new HashMap<>();
        for (String paramName : parameterNames) {
            if (newEnv.has(paramName)) {
                savedVars.put(paramName, newEnv.get(paramName));
            }
        }
        
        // Bind parameters to arguments
        for (int i = 0; i < parameterNames.length && i < arguments.length; i++) {
            newEnv.set(parameterNames[i], arguments[i]);
        }
        
        try {
            // Execute the body
            return body.execute(frame);
        } finally {
            // Restore saved variables (cleanup)
            for (String paramName : parameterNames) {
                if (savedVars.containsKey(paramName)) {
                    newEnv.set(paramName, savedVars.get(paramName));
                } else {
                    // Remove the parameter binding
                    // Note: VariableEnvironment doesn't have a remove method, so we leave it
                }
            }
        }
    }
}
