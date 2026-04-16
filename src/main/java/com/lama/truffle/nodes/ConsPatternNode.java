package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.oracle.truffle.api.frame.VirtualFrame;

public class ConsPatternNode extends PatternNode {

    @Child private PatternNode headPattern;
    @Child private PatternNode tailPattern;

    public ConsPatternNode(PatternNode headPattern, PatternNode tailPattern) {
        this.headPattern = headPattern;
        this.tailPattern = tailPattern;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        if (!(value instanceof List)) {
            return false;
        }

        List list = (List) value;
        Object head = list.getFirst();
        List tail = list.getNext();

        if (!headPattern.match(head, frame)) {
            return false;
        }

        return tailPattern.match(tail, frame);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        headPattern.execute(frame);
        tailPattern.execute(frame);
        return null;
    }

    public PatternNode getHeadPattern() {
        return headPattern;
    }

    public PatternNode getTailPattern() {
        return tailPattern;
    }
}
