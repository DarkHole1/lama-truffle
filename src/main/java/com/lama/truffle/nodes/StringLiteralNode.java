package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class StringLiteralNode extends LiteralNode {

    public StringLiteralNode(String value) {
        super(value);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return (String) getValue();
    }
}