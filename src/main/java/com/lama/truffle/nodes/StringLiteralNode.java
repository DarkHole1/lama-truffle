package com.lama.truffle.nodes;

import com.lama.truffle.types.Array;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

public class StringLiteralNode extends LiteralNode {

    public StringLiteralNode(String value) {
        super(value);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        String value = (String)getValue();
        Object[] result = new Object[value.length()];
        for (int i = 0; i < value.length(); i++) {
            result[i] = (long)value.charAt(i);
        }
        return new Array(result);
    }

    @Override @ExplodeLoop
    public Array executeArray(VirtualFrame frame) throws UnexpectedResultException {
        String value = (String)getValue();
        Object[] result = new Object[value.length()];
        for (int i = 0; i < value.length(); i++) {
            result[i] = (long)value.charAt(i);
        }
        return new Array(result);
    }
}