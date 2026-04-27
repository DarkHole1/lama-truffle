package com.lama.truffle.runtime;

import com.lama.truffle.nodes.ExpressionNode;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public final class FrameExecutorRootNode extends RootNode {
    private final int[] parameterSlots;

    @Child
    private ExpressionNode body;

    public FrameExecutorRootNode(FrameDescriptor descriptor,
                                  ExpressionNode body, int[] parameterSlots) {
        super(null, descriptor);
        this.body = body;
        this.parameterSlots = parameterSlots;
    }

    public FrameExecutorRootNode(FrameDescriptor descriptor, ExpressionNode body) {
        this(descriptor, body, null);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object[] args = frame.getArguments();
        if (args == null) args = new Object[0];

        // Bind parameters if this is a function scope (arguments start at index 1)
        if (parameterSlots != null) {
            for (int i = 0; i < parameterSlots.length && i < args.length - 1; i++) {
                frame.setObject(parameterSlots[i], args[i + 1]);
            }
        }

        return body.execute(frame);
    }
}
