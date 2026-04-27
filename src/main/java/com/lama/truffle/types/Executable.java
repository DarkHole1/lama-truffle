package com.lama.truffle.types;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.TruffleObject;

public abstract class Executable implements TruffleObject {
    public abstract Object execute(VirtualFrame frame, Object[] arguments);
}
