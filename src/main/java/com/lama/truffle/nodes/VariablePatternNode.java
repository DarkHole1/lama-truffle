package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Variable pattern that matches any value and binds it to a variable name.
 */
public class VariablePatternNode extends PatternNode {

    private final String variableName;
    private final PatternNode nestedPatternNode;

    public VariablePatternNode(String variableName, PatternNode nestedPatternNode) {
        this.variableName = variableName;
        this.nestedPatternNode = nestedPatternNode;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        VariableEnvironment env = VariableEnvironment.getOrCreate(frame);
        env.set(variableName, value);
        if (nestedPatternNode != null) {
            return nestedPatternNode.match(value, frame);
        }
        return true;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        VariableEnvironment env = VariableEnvironment.getOrCreate(frame);
        return env.get(variableName);
    }
}
