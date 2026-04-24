package com.lama.truffle.types;

import java.util.function.Function;

import com.oracle.truffle.api.frame.VirtualFrame;

public class NativeFunction extends Executable {
    private final String name;
    private final Function<Object[], Object> func;
    
    public NativeFunction(String name, Function<Object[], Object> callable) {
        this.name = name;
        this.func = callable;
    }

    @Override
    public Object execute(VirtualFrame frame, Object[] arguments) {
        return func.apply(arguments);
    }

    public Function<Object[], Object> getFunc() {
        return func;
    }

    public String getName() {
        return name;
    }
}
