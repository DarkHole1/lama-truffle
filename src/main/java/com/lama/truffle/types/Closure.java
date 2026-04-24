package com.lama.truffle.types;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.runtime.FrameExecutorRootNode;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.IndirectCallNode;

public final class Closure extends Executable {

    private final String name;
    private final String[] parameterNames;
    private final int[] parameterSlots;
    private final ExpressionNode body;
    private final FrameDescriptor descriptor;
    private final FrameExecutorRootNode root;
    private final CallTarget callTarget;
    private final IndirectCallNode callNode;
    private final VirtualFrame materializedParentFrame;

    public Closure(String name, String[] parameterNames, int[] parameterSlots,
                   ExpressionNode body, FrameDescriptor descriptor, VirtualFrame materializedParentFrame) {
        this.name = name;
        this.parameterNames = parameterNames;
        this.parameterSlots = parameterSlots;
        this.body = body;
        this.descriptor = descriptor;
        this.materializedParentFrame = materializedParentFrame;
        
        this.root = new FrameExecutorRootNode(descriptor, body, parameterSlots);
        this.callTarget = root.getCallTarget();
        this.callNode = Truffle.getRuntime().createIndirectCallNode();
    }

    public Object execute(VirtualFrame frame, Object[] arguments) {
        Object[] callArgs = new Object[arguments.length + 1];
        callArgs[0] = materializedParentFrame;
        System.arraycopy(arguments, 0, callArgs, 1, arguments.length);

        return callNode.call(callTarget, callArgs);
    }

    public String getName() {
        return name;
    }

    public String[] getParameterNames() {
        return parameterNames;
    }

    public int[] getParameterSlots() {
        return parameterSlots;
    }

    public ExpressionNode getBody() {
        return body;
    }

    public FrameDescriptor getDescriptor() {
        return descriptor;
    }

    public VirtualFrame getMaterializedParentFrame() {
        return materializedParentFrame;
    }
}
