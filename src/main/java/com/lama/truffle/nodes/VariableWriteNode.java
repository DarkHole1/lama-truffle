package com.lama.truffle.nodes;

import com.lama.truffle.runtime.VariableLookup;
import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableWriteNode extends ExpressionNode {

    private final String variableName;
    private final VariableLookup lookup;
    @Child private ExpressionNode valueNode;

    public VariableWriteNode(String variableName, VariableLookup lookup, ExpressionNode valueNode) {
        this.variableName = variableName;
        this.lookup = lookup;
        this.valueNode = valueNode;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object value = valueNode.execute(frame);
        if (lookup.isCaptured()) {
            lookup.writeCaptured(frame, value);
        } else {
            lookup.write(frame, value);
        }
        return value;
    }

    public String getVariableName() {
        return variableName;
    }

    public VariableLookup getLookup() {
        return lookup;
    }

    public ExpressionNode getValueNode() {
        return valueNode;
    }
}
