package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.lama.truffle.types.ListCons;
import com.oracle.truffle.api.frame.VirtualFrame;

public class ConsPatternNode extends PatternNode {

    @Child private PatternNode headPattern;
    @Child private PatternNode tailPattern;

    public ConsPatternNode(PatternNode headPattern, PatternNode tailPattern) {
        this.headPattern = headPattern;
        this.tailPattern = tailPattern;
    }

    @Override
    boolean executeBoolean(VirtualFrame frame, Object value) {
        if (!(value instanceof ListCons)) {
            return false;
        }

        ListCons list = (ListCons) value;
        Object head = list.getFirst();
        List tail = list.getNext();

        if (!headPattern.executeBoolean(frame, head)) {
            return false;
        }

        return tailPattern.executeBoolean(frame, tail);
    }

    public PatternNode getHeadPattern() {
        return headPattern;
    }

    public PatternNode getTailPattern() {
        return tailPattern;
    }
}
