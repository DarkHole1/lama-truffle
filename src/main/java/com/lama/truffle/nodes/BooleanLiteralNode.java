package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

public class BooleanLiteralNode extends LiteralNode {

    public BooleanLiteralNode(boolean value) {
        super(value ? 1l : 0l);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return (long) getValue();
    }

    @Override
    public long executeLong(VirtualFrame frame) throws UnexpectedResultException {
        return (long) getValue();
    }
}