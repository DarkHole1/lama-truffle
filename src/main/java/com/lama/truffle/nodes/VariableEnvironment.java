package com.lama.truffle.nodes;

import com.lama.truffle.LamaContext;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * Environment for managing named variables in a frame.
 * Supports lexical scoping through parent environment chain.
 * Stored as a single object in the frame at a fixed slot.
 */
public class VariableEnvironment {

    /**
     * Slot index for storing the environment in the frame.
     */
    public static final int SLOT_INDEX = 0;

    private final Map<String, Object> variables;
    private final VariableEnvironment parent;
    private final LamaContext context;

    public VariableEnvironment(VariableEnvironment parent, LamaContext context) {
        this.variables = new HashMap<>();
        this.parent = parent;
        this.context = context;
    }

    /**
     * Legacy constructor for backward compatibility.
     * @deprecated Use createRoot() or createChild() instead.
     */
    @Deprecated
    public VariableEnvironment() {
        this.variables = new HashMap<>();
        this.parent = null;
        this.context = null;
    }

    /**
     * Creates a root environment with no parent.
     */
    public static VariableEnvironment createRoot(LamaContext context) {
        return new VariableEnvironment(null, context);
    }

    /**
     * Creates a child environment with the given parent.
     */
    public static VariableEnvironment createChild(VariableEnvironment parent) {
        return new VariableEnvironment(parent, parent.context);
    }

    /**
     * Gets or creates the environment from a frame.
     */
    public static VariableEnvironment getOrCreate(VirtualFrame frame) {
        try {
            Object obj = frame.getObject(SLOT_INDEX);
            if (obj instanceof VariableEnvironment) {
                return (VariableEnvironment) obj;
            }
        } catch (IllegalArgumentException e) {
            // Slot doesn't exist yet, create it
        }
        
        // Create root environment if none exists
        LamaContext context = LamaContext.getCurrentContext();
        VariableEnvironment env = createRoot(context);
        frame.setObject(SLOT_INDEX, env);
        return env;
    }

    /**
     * Gets or creates a child environment from a frame.
     * If no environment exists, creates a root. Otherwise creates a child of the current.
     */
    public static VariableEnvironment getOrCreateChild(VirtualFrame frame) {
        VariableEnvironment current = getOrCreate(frame);
        VariableEnvironment child = createChild(current);
        frame.setObject(SLOT_INDEX, child);
        return child;
    }

    /**
     * Sets a variable in the current (local) scope.
     */
    public void set(String name, Object value) {
        variables.put(name, value);
    }

    /**
     * Defines a variable in the current scope, or updates if it exists in the chain.
     * If the variable exists in a parent scope, this creates a shadowing binding locally.
     */
    public void define(String name, Object value) {
        variables.put(name, value);
    }

    /**
     * Gets a variable value, searching the environment chain.
     * Returns null if not found.
     */
    public Object get(String name) {
        // Check local scope first
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        // Search parent chain
        if (parent != null) {
            return parent.get(name);
        }
        // Check global variables in context
        if (context != null && context.hasGlobalVariable(name)) {
            return context.getGlobalVariable(name);
        }
        return null;
    }

    /**
     * Checks if a variable exists in any scope.
     */
    public boolean has(String name) {
        if (variables.containsKey(name)) {
            return true;
        }
        if (parent != null) {
            return parent.has(name);
        }
        if (context != null) {
            return context.hasGlobalVariable(name);
        }
        return false;
    }

    /**
     * Gets the parent environment.
     */
    public VariableEnvironment getParent() {
        return parent;
    }

    /**
     * Gets the language context.
     */
    public LamaContext getContext() {
        return context;
    }

    /**
     * Gets all variables in the local scope only.
     */
    public Map<String, Object> getVariables() {
        return variables;
    }

    /**
     * Gets all variables in the local scope only.
     */
    public Map<String, Object> getLocalVariables() {
        return variables;
    }
}
