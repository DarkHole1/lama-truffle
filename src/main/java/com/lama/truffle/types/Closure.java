package com.lama.truffle.types;

import java.util.function.Function;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.nodes.NativeCallRootNode;
import com.lama.truffle.runtime.FrameExecutorRootNode;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public final class Closure {

    private final String name;
    private final CallTarget callTarget;
    private final MaterializedFrame materializedParentFrame;

    public Closure(String name, String[] parameterNames, int[] parameterSlots,
                   ExpressionNode body, FrameDescriptor descriptor, MaterializedFrame materializedParentFrame) {
        this.name = name;
        this.materializedParentFrame = materializedParentFrame;
        
        RootNode root = new FrameExecutorRootNode(descriptor, body, parameterSlots);
        this.callTarget = root.getCallTarget();
    }

    public Closure(String name, Function<Object[], Object> callable) {
        this.name = name;
        this.materializedParentFrame = null;

        RootNode root = new NativeCallRootNode(callable);
        this.callTarget = root.getCallTarget();
    }

    public String getName() {
        return name;
    }

    public VirtualFrame getMaterializedParentFrame() {
        return materializedParentFrame;
    }

    public CallTarget getCallTarget() {
        return callTarget;
    }
}
