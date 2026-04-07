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
        int ci = lookup.getCaptureIndex();
        if (ci >= 0) {
            VirtualFrame[] capturedFrames = (VirtualFrame[]) frame.getObject(com.lama.truffle.runtime.Scope.CAPTURED_FRAMES_SLOT);
            capturedFrames[ci].setObject(lookup.getSlot(), value);
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
