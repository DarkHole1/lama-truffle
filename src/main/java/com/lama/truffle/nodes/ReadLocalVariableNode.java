package com.lama.truffle.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

@NodeField(name = "slot", type = int.class)
public abstract class ReadLocalVariableNode extends ExpressionNode {
    protected abstract int getSlot();

    @Specialization(guards = "frame.isLong(getSlot())")
    protected long readLong(VirtualFrame frame) {
        return frame.getLong(getSlot());
    }

    @Specialization(replaces = { "readLong" })
    protected Object readObject(VirtualFrame frame) {
        if (!frame.isObject(getSlot())) {
            CompilerDirectives.transferToInterpreter();
            Object result = frame.getValue(getSlot());
            frame.setObject(getSlot(), result);
            return result;
        }

        return frame.getObject(getSlot());
    }
}
