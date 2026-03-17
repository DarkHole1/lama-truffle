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
        // Bind the value to the variable in the frame
        // Note: This requires frame variable support to be implemented
        frame.setObject(0, value); // Placeholder - actual implementation needs proper variable binding
        return true;
    }

    public String getVariableName() {
        return variableName;
    }
}
