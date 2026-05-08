package com.lama.truffle.runtime;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.nodes.ReadArgumentNode;
import com.lama.truffle.nodes.WriteLocalVariableNodeGen;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RootNode;

public final class FrameExecutorRootNode extends RootNode {
    private final String name;
    private final int[] parameterSlots;

    @Children private final ExpressionNode[] preable;
    @Child private ExpressionNode body;

    public FrameExecutorRootNode(String name, FrameDescriptor descriptor,
            ExpressionNode body, int[] parameterSlots) {
        super(null, descriptor);
        this.name = name;
        this.body = body;
        this.parameterSlots = parameterSlots;
        this.preable = new ExpressionNode[parameterSlots.length];
        for (int i = 0; i < parameterSlots.length; i++) {
            preable[i] = WriteLocalVariableNodeGen.create(new ReadArgumentNode(i + 1), parameterSlots[i]);
        }
    }

    public FrameExecutorRootNode(String name, FrameDescriptor descriptor, ExpressionNode body) {
        this(name, descriptor, body, null);
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        for (int i = 0; i < parameterSlots.length; i++) {
            preable[i].execute(frame);
        }

        return body.execute(frame);
    }

    @Override
    public String getName() {
        return name;
    }
}
