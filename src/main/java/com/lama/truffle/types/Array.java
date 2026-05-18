package com.lama.truffle.types;

import com.oracle.truffle.api.interop.TruffleObject;

public class Array implements TruffleObject {
    private final Object[] array;

    public Array(Object[] array) {
        this.array = array;
    }

    public Object[] getArray() {
        return array;
    }

    public int length() {
        return array.length;
    }

    public Object getIndex(int i) {
        return array[i];
    }

    public void setIndex(int i, Object o) {
        array[i] = o;
    }
}
