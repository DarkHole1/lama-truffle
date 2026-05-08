package com.lama.truffle.nodes;

import com.lama.truffle.types.Array;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;

@NodeChild("arrayNode")
@NodeChild("indexNode")
public abstract class ArrayAccessNode extends ExpressionNode {

    @Specialization
    protected Object accessArray(Array array, long index) {
        if (index < 0 || index >= array.length()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Length: " + array.length());
        }
        return array.getIndex((int) index);
    }
}
