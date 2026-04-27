package com.lama.truffle.nodes;

import java.util.function.Function;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public final class NativeCallRootNode extends RootNode {
    private Function<Object[], Object> func;

    public NativeCallRootNode(Function<Object[], Object> func) {
        super(null);
        this.func = func;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return executeNative(frame.getArguments());
    }

    @TruffleBoundary
    Object executeNative(Object[] arguments) {
        return func.apply(arguments);
    }
}
