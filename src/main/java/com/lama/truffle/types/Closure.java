package com.lama.truffle.types;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.nodes.VariableEnvironment;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a closure (function value) that captures the execution context.
 * A closure contains the function body, parameter names, and the captured environment.
 */
public final class Closure {

    private final String name;
    private final String[] parameterNames;
    private final ExpressionNode body;
    private final VariableEnvironment capturedEnvironment;

    public Closure(String name, String[] parameterNames, ExpressionNode body, VariableEnvironment capturedEnvironment) {
        this.name = name;
        this.parameterNames = parameterNames;
        this.body = body;
        this.capturedEnvironment = capturedEnvironment;
    }

    /**
     * Creates a closure by capturing the current environment from the frame.
     */
    public Closure(String name, String[] parameterNames, ExpressionNode body, VirtualFrame capturedFrame) {
        this(name, parameterNames, body, VariableEnvironment.getOrCreate(capturedFrame));
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
     * Executes this closure with the given frame and arguments.
     * Creates a new local environment for parameter bindings.
     */
    public Object execute(VirtualFrame frame, Object[] arguments) {
        // Get the current environment from the frame
        VariableEnvironment currentEnv = VariableEnvironment.getOrCreate(frame);
        
        // Save current variable state for parameters that might shadow existing variables
        Map<String, Object> savedValues = new HashMap<>();
        for (String paramName : parameterNames) {
            if (currentEnv.has(paramName)) {
                savedValues.put(paramName, currentEnv.get(paramName));
            }
        }

        // Bind parameters to arguments in the current environment
        for (int i = 0; i < parameterNames.length && i < arguments.length; i++) {
            currentEnv.set(parameterNames[i], arguments[i]);
        }

        try {
            // Execute the function body
            return body.execute(frame);
        } finally {
            // Restore saved variables (cleanup parameter bindings)
            for (String paramName : parameterNames) {
                if (savedValues.containsKey(paramName)) {
                    currentEnv.set(paramName, savedValues.get(paramName));
                } else {
                    // Parameter was newly introduced - leave it or could be removed
                    // For now we leave it as VariableEnvironment doesn't have remove
                }
            }
        }
    }
}
