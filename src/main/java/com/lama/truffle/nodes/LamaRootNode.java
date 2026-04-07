package com.lama.truffle.nodes;

import com.lama.truffle.LamaContext;
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
        // Initialize context if not already set (when not going through Truffle engine)
        if (LamaContext.getCurrentContext() == null) {
            // Context needs env, but we don't have one in direct call mode.
            // Create a minimal context. The env will be null but we don't use it currently.
            LamaContext context = new LamaContext(null);
            LamaContext.setCurrentContext(context);
        }

        // Store null parent in slot 0 (root scope has no parent)
        int parentSlot = com.lama.truffle.runtime.Scope.getParentSlot(frame);
        frame.setObject(parentSlot, null);
        return body.execute(frame);
    }
}
