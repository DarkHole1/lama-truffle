package com.lama.truffle.nodes;

import com.lama.truffle.runtime.VariableLookup;
import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableAccessNode extends ExpressionNode {

    private final String variableName;
    private final VariableLookup lookup;
    @Child private ExpressionNode readNode;

    private VariableAccessNode(String variableName, VariableLookup lookup, ReadLocalVariableNode readNode) {
        this.variableName = variableName;
        this.lookup = lookup;
        this.readNode = readNode;
    }

    public static ExpressionNode create(String variableName, VariableLookup lookup) {
        return new VariableAccessNode(variableName, lookup, ReadLocalVariableNodeGen.create(lookup.getSlot()));
    }

    @Override
    public Object execute(VirtualFrame frame) {
        VirtualFrame readFrame = lookup.getFrame(frame);
        return readNode.execute(readFrame);
    }

    public String getVariableName() {
        return variableName;
    }

    public VariableLookup getLookup() {
        return lookup;
    }
}
