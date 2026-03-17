package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;

import java.util.HashMap;
import java.util.Map;

/**
 * Global function registry for named functions.
 * In a full implementation, this would be part of the language context.
 */
public class FunctionRegistry {
    private static final Map<String, Closure> functions = new HashMap<>();
    
    public static void register(String name, Closure closure) {
        functions.put(name, closure);
    }
    
    public static Closure get(String name) {
        return functions.get(name);
    }
}
