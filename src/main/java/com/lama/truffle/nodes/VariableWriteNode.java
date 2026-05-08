package com.lama.truffle.nodes;

import com.lama.truffle.runtime.VariableLookup;
import com.oracle.truffle.api.frame.VirtualFrame;

public class VariableWriteNode extends ExpressionNode {

    private final String variableName;
    private final VariableLookup lookup;
    @Child private ExpressionNode writeNode;

    private VariableWriteNode(String variableName, VariableLookup lookup, WriteLocalVariableNode writeNode) {
        this.variableName = variableName;
        this.lookup = lookup;
        this.writeNode = writeNode;
    }

    public static ExpressionNode create(String variableName, VariableLookup lookup, ExpressionNode valueNode) {
        return new VariableWriteNode(variableName, lookup, WriteLocalVariableNodeGen.create(valueNode, lookup.getSlot()));
    }

    @Override
    public Object execute(VirtualFrame frame) {
        VirtualFrame writeFrame = lookup.getFrame(frame);
        return writeNode.execute(writeFrame);
    }

    public String getVariableName() {
        return variableName;
    }

    public VariableLookup getLookup() {
        return lookup;
    }
    
    public ExpressionNode getWriteNode() {
        return writeNode;
    }
}
