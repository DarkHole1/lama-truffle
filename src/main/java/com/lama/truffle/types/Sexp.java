package com.lama.truffle.types;

public class Sexp {
    private String name; 
    private Object[] values;

    public Sexp(String name, Object[] values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public Object[] getValues() {
        return values;
    }
}
