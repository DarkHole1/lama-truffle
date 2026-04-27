package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.oracle.truffle.api.frame.VirtualFrame;

public class ConsPatternNode extends PatternNode {

    @Child private PatternNode headPattern;
    @Child private PatternNode tailPattern;

    public ConsPatternNode(PatternNode headPattern, PatternNode tailPattern, int scrutineeSlot) {
        super(scrutineeSlot);
        this.headPattern = headPattern;
        this.tailPattern = tailPattern;
    }

    @Override
    boolean executeBoolean(VirtualFrame frame) {
        Object value = frame.getObject(getScrutineeSlot());

        if (!(value instanceof List)) {
            return false;
        }

        List list = (List) value;
        Object head = list.getFirst();
        List tail = list.getNext();

        frame.setObject(getScrutineeSlot(), head);
        if (!headPattern.executeBoolean(frame)) {
            return false;
        }

        frame.setObject(getScrutineeSlot(), tail);
        return tailPattern.executeBoolean(frame);
    }

    public PatternNode getHeadPattern() {
        return headPattern;
    }

    public PatternNode getTailPattern() {
        return tailPattern;
    }
}
