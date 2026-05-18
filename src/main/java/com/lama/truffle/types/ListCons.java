package com.lama.truffle.types;

public final class ListCons extends List {
    private Object first;
    private List next;

    public ListCons(Object first, List next) {
        this.first = first;
        this.next = next;
    }

    public Object getFirst() {
        return first;
    }

    public List getNext() {
        return next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(first.toString());
        List current = next;
        while (current instanceof ListCons c) {
            sb.append(", ");
            sb.append(c.getFirst().toString());
            current = c.getNext();
        }
        sb.append("}");
        return sb.toString();
    }
}
