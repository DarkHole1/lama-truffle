package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Variable pattern that matches any value and binds it to a variable name.
 */
public class VariablePatternNode extends PatternNode {

    private final String variableName;

    public VariablePatternNode(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        // Bind the value to the variable using VariableEnvironment
        VariableEnvironment env = VariableEnvironment.getOrCreate(frame);
        env.set(variableName, value);
        return true;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Return the bound variable value
        VariableEnvironment env = VariableEnvironment.getOrCreate(frame);
        return env.get(variableName);
    }
}
