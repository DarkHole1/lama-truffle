package com.lama.truffle.types;

public final class List {
    private Object first;
    private List next;

    public List(Object first, List next) {
        this.first = first;
        this.next = next;
    }

    public Object getFirst() {
        return first;
    }

    public List getNext() {
        return next;
    }
}
