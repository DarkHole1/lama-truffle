package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for managing named variables in a frame.
 * Stored as a single object in the frame at a fixed slot index.
 */
public class VariableEnvironment {
    
    public static final int SLOT_INDEX = 0;
    public static final String SLOT_NAME = "_env";
    
    private final Map<String, Object> variables;
    
    public VariableEnvironment() {
        this.variables = new HashMap<>();
    }
    
    public static VariableEnvironment getOrCreate(VirtualFrame frame) {
        try {
            Object obj = frame.getObject(SLOT_INDEX);
            if (obj instanceof VariableEnvironment) {
                return (VariableEnvironment) obj;
            }
        } catch (IllegalArgumentException e) {
            // Slot doesn't exist yet
        }
        VariableEnvironment env = new VariableEnvironment();
        frame.setObject(SLOT_INDEX, env);
        return env;
    }
    
    public void set(String name, Object value) {
        variables.put(name, value);
    }
    
    public Object get(String name) {
        return variables.get(name);
    }
    
    public boolean has(String name) {
        return variables.containsKey(name);
    }

    public java.util.Map<String, Object> getVariables() {
        return variables;
    }
}
