package com.lama.truffle.types;

import java.util.function.Function;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.nodes.NativeCallRootNode;
import com.lama.truffle.runtime.FrameExecutorRootNode;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;
import com.oracle.truffle.api.nodes.RootNode;

@ExportLibrary(InteropLibrary.class)
public final class Closure implements TruffleObject {

    private final String name;
    private final CallTarget callTarget;
    private final MaterializedFrame materializedParentFrame;

    public Closure(String name, String[] parameterNames, int[] parameterSlots,
            ExpressionNode body, FrameDescriptor descriptor, MaterializedFrame materializedParentFrame) {
        this.name = name;
        this.materializedParentFrame = materializedParentFrame;

        RootNode root = new FrameExecutorRootNode(name, descriptor, body, parameterSlots);
        this.callTarget = root.getCallTarget();
    }

    public Closure(String name, Function<Object[], Object> callable) {
        this.name = name;
        this.materializedParentFrame = null;

        RootNode root = new NativeCallRootNode(name, callable);
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

    @ExportMessage
    boolean isExecutable() {
        return true;
    }

    @ExportMessage
    Object execute(Object[] arguments) {
        Object[] args = new Object[arguments.length + 1];
        args[0] = this.getMaterializedParentFrame();
        System.arraycopy(arguments, 0, args, 1, arguments.length);
        return callTarget.call(args);
    }
}
