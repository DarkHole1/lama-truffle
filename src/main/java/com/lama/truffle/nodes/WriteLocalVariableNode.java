package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;

@NodeChild("valueNode")
@NodeField(name = "slot", type = int.class)
public abstract class WriteLocalVariableNode extends ExpressionNode {
    protected abstract int getSlot();

    @Specialization(guards = "isLongOrIllegal(frame)")
    protected long writeLong(VirtualFrame frame, long value) {
        frame.getFrameDescriptor().setSlotKind(getSlot(), FrameSlotKind.Long);

        frame.setLong(getSlot(), value);
        return value;
    }

    @Specialization(replaces = {"writeLong"})
    protected Object write(VirtualFrame frame, Object value) {
        frame.getFrameDescriptor().setSlotKind(getSlot(), FrameSlotKind.Object);

        frame.setObject(getSlot(), value);
        return value;
    }

    public abstract void executeWrite(VirtualFrame frame, Object value);

    protected boolean isLongOrIllegal(VirtualFrame frame) {
        final FrameSlotKind kind = frame.getFrameDescriptor().getSlotKind(getSlot());
        return kind == FrameSlotKind.Long || kind == FrameSlotKind.Illegal;
    }
}
