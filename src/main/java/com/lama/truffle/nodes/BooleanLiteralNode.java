package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class BooleanLiteralNode extends LiteralNode {

    public BooleanLiteralNode(boolean value) {
        super(value);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return (boolean) getValue() ? 1 : 0; // In Lama, true is represented as 1, false as 0
    }
}