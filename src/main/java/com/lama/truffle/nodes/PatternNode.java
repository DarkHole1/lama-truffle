package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class PatternNode extends ExpressionNode {
    public abstract boolean match(Object value, VirtualFrame frame);
}
