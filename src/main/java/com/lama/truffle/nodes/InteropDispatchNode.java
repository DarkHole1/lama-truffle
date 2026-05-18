package com.lama.truffle.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.ArityException;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.interop.UnsupportedTypeException;

public final class InteropDispatchNode extends FunctionCallNode.DispatchNode {

    @Child private InteropLibrary interop;

    public InteropDispatchNode() {
        this.interop = InteropLibrary.getFactory().createDispatched(3);
    }

    @Override
    public Object executeDispatch(VirtualFrame frame, Object function, Object[] arguments) {
        if (!interop.isExecutable(function)) {
            CompilerDirectives.transferToInterpreter();
            throw new RuntimeException("Expression evaluated into non-function: " + function);
        }

        try {
            return interop.execute(function, arguments);
        } catch (UnsupportedTypeException | ArityException | UnsupportedMessageException e) {
            CompilerDirectives.transferToInterpreter();
            throw new RuntimeException(e);
        }
    }
}