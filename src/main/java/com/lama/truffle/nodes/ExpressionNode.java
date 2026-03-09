package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class ExpressionNode extends Node {
    
    public abstract Object execute(VirtualFrame frame);
}