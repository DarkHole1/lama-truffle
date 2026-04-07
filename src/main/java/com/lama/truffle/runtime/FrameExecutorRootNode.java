package com.lama.truffle.runtime;

import com.lama.truffle.nodes.ExpressionNode;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

/**
 * Generic RootNode that executes an expression body in a new frame.
 * Used by ScopeEnterNode and closures to create child frames.
 * 
 * Slot 0: parent frame reference
 * Slot 1: captured frames array (VirtualFrame[]) - for function scopes
 * Slot 2+: local variables / parameters
 * 
 * Arguments layout: [parentFrame, capturedFramesArray?, arg0, arg1, ...]
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
        if (args == null) args = new Object[0];

        // First argument is the lexical parent frame
        if (args.length > 0) {
            int parentSlot = Scope.getParentSlot(frame);
            frame.setObject(parentSlot, args[0]);
        }

        // Second argument is the captured frames array (only for function scopes)
        if (args.length > 1 && parameterSlots != null) {
            frame.setObject(Scope.CAPTURED_FRAMES_SLOT, args[1]);
        }

        // Bind parameters if this is a function scope (arguments start at index 2)
        if (parameterSlots != null) {
            for (int i = 0; i < parameterSlots.length && i < args.length - 2; i++) {
                frame.setObject(parameterSlots[i], args[i + 2]);
            }
        }

        return body.execute(frame);
    }
}
