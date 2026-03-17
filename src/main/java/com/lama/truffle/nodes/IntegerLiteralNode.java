package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class IntegerLiteralNode extends LiteralNode {

    public IntegerLiteralNode(long value) {
        super(value);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return (long) getValue();
    }
}