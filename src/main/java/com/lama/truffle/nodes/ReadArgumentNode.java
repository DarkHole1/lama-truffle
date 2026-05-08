package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class ReadArgumentNode extends ExpressionNode {
    private final int index;

    public ReadArgumentNode(int index) {
        this.index = index;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object[] args = frame.getArguments();
        if (index < args.length) {
            return args[index];
        } else {
            throw new RuntimeException("Argument out of bounds");
        }
    }
}
