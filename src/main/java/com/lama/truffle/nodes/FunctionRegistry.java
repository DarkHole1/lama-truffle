package com.lama.truffle.nodes;

import com.lama.truffle.LamaContext;
import com.lama.truffle.types.Closure;

/**
 * Global function registry for named functions.
 * 
 * @deprecated Use {@link LamaContext} directly for function registration and lookup.
 * This class is kept for backward compatibility.
 */
@Deprecated
public class FunctionRegistry {

    /**
     * Registers a function in the current language context.
     * @deprecated Use {@link LamaContext#registerFunction(String, Closure)} instead.
     */
    @Deprecated
    public static void register(String name, Closure closure) {
        LamaContext context = LamaContext.getCurrentContext();
        context.registerFunction(name, closure);
    }

    /**
     * Gets a function from the current language context.
     * @deprecated Use {@link LamaContext#getFunction(String)} instead.
     */
    @Deprecated
    public static Closure get(String name) {
        LamaContext context = LamaContext.getCurrentContext();
        return context.getFunction(name);
    }
}
