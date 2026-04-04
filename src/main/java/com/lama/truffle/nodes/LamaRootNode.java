package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

/**
 * Root node for Lama program execution.
 * Serves as the entry point for Truffle execution.
 */
public class LamaRootNode extends RootNode {

    @Child
    private ExpressionNode body;

    public LamaRootNode(ExpressionNode body, FrameDescriptor descriptor) {
        super(null, descriptor);
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Store null parent in slot 0 (root scope has no parent)
        frame.setObject(com.lama.truffle.runtime.Scope.getParentSlot(), null);
        return body.execute(frame);
    }
}
