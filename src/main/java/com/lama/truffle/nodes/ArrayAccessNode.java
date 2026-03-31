package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

@NodeChild("arrayNode")
@NodeChild("indexNode")
public abstract class ArrayAccessNode extends ExpressionNode {

    @Specialization
    protected Object accessArray(Object[] array, long index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }
        return array[(int) index];
    }

    @Specialization
    protected Object accessString(String string, long index) {
        if (index < 0 || index >= string.length()) {
            throw new StringIndexOutOfBoundsException("Index: " + index + ", Length: " + string.length());
        }
        return string.charAt((int) index);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        throw new RuntimeException("Generic execution should not be called - specialization should handle this");
    }
}
