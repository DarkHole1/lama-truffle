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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(first.toString());
        List current = next;
        while (current != null) {
            sb.append(", ");
            sb.append(current.getFirst().toString());
            current = current.getNext();
        }
        sb.append("}");
        return sb.toString();
    }
}
