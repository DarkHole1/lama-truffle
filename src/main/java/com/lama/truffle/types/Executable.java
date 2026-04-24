package com.lama.truffle.types;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class Executable {
    public abstract Object execute(VirtualFrame frame, Object[] arguments);
}
