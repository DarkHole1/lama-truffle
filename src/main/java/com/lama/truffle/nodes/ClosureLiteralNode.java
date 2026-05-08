package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.VirtualFrame;

public class ClosureLiteralNode extends LiteralNode {
    public ClosureLiteralNode(Closure closure) {
        super(closure);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return (Closure)getValue();
    }

    @Override
    public Closure executeClosure(VirtualFrame frame) {
        return (Closure)getValue();
    }
}
