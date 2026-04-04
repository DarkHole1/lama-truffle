package com.lama.truffle;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.TruffleLanguage;

import java.util.HashMap;
import java.util.Map;

/**
 * Language context for Lama.
 * Holds global state including functions.
 * One context instance is created per execution environment.
 */
public final class LamaContext {

    private static final ThreadLocal<LamaContext> currentContext = new ThreadLocal<>();

    private final TruffleLanguage.Env env;
    private final Map<String, Closure> functions;

    public LamaContext(TruffleLanguage.Env env) {
        this.env = env;
        this.functions = new HashMap<>();
    }

    /**
     * Gets the current context from thread-local storage.
     */
    public static LamaContext getCurrentContext() {
        return currentContext.get();
    }

    /**
     * Sets the current context in thread-local storage.
     */
    public static void setCurrentContext(LamaContext context) {
        currentContext.set(context);
    }

    /**
     * Gets the Truffle environment.
     */
    public TruffleLanguage.Env getEnv() {
        return env;
    }

    /**
     * Registers a function in the global function registry.
     */
    public void registerFunction(String name, Closure closure) {
        functions.put(name, closure);
    }

    /**
     * Looks up a function by name.
     */
    public Closure getFunction(String name) {
        return functions.get(name);
    }

    /**
     * Checks if a function exists.
     */
    public boolean hasFunction(String name) {
        return functions.containsKey(name);
    }

    /**
     * Initializes the context with built-in functions.
     */
    public void initializeBuiltins() {
        // TODO: Add built-in functions here
        // Examples: print, length, etc.
    }
}
