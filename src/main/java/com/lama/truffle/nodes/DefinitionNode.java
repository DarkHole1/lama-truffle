package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class DefinitionNode extends ExpressionNode {
    @Override
    public abstract Object execute(VirtualFrame frame);
}
