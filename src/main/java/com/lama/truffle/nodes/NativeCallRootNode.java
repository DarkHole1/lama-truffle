package com.lama.truffle.nodes;

import java.util.function.Function;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public final class NativeCallRootNode extends RootNode {
    private final String name;
    private Function<Object[], Object> func;

    public NativeCallRootNode(String name, Function<Object[], Object> func) {
        super(null);
        this.func = func;
        this.name = name;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return executeNative(frame.getArguments());
    }

    @TruffleBoundary
    Object executeNative(Object[] arguments) {
        return func.apply(arguments);
    }

    @Override
    public String getName() {
        return name;
    }
}
