package com.lama.truffle.runtime;

import com.lama.truffle.nodes.ExpressionNode;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

/**
 * Generic RootNode that executes an expression body in a new frame.
 * Used by ScopeEnterNode and closures to create child frames.
 * 
 * Slot 0 is reserved for the lexical parent frame reference.
 * The parent frame is passed as the first argument.
 * If parameterSlots is provided, remaining arguments are bound to those slots.
 */
public class FrameExecutorRootNode extends RootNode {

    private final int[] parameterSlots;  // null for non-function scopes

    @Child
    private ExpressionNode body;

    public FrameExecutorRootNode(FrameDescriptor descriptor,
                                  ExpressionNode body, int[] parameterSlots) {
        super(null, descriptor);
        this.body = body;
        this.parameterSlots = parameterSlots;
    }

    /**
     * For non-function scopes (no parameter binding).
     */
    public FrameExecutorRootNode(FrameDescriptor descriptor, ExpressionNode body) {
        this(descriptor, body, null);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object[] args = frame.getArguments();

        // First argument is the lexical parent frame
        if (args != null && args.length > 0) {
            frame.setObject(Scope.getParentSlot(), args[0]);
        }

        // Bind parameters if this is a function scope (arguments start at index 1)
        if (parameterSlots != null && args != null) {
            for (int i = 0; i < parameterSlots.length && i < args.length - 1; i++) {
                frame.setObject(parameterSlots[i], args[i + 1]);
            }
        }

        return body.execute(frame);
    }
}
