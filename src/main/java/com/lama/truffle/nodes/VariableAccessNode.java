package com.lama.truffle.nodes;

import com.lama.truffle.runtime.VariableLookup;
import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableAccessNode extends ExpressionNode {

    private final String variableName;
    private final VariableLookup lookup;

    public VariableAccessNode(String variableName, VariableLookup lookup) {
        this.variableName = variableName;
        this.lookup = lookup;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        if (lookup.isCaptured()) {
            return lookup.readCaptured(frame);
        }
        return lookup.read(frame);
    }

    public String getVariableName() {
        return variableName;
    }

    public VariableLookup getLookup() {
        return lookup;
    }
}
