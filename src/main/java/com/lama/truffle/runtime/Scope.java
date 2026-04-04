package com.lama.truffle.runtime;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlotKind;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for tracking variables and allocating frame slots during AST building.
 * Not an AST node - used purely at parse/build time.
 * 
 * Each scope has its own FrameDescriptor.Builder. Slot 0 is reserved for parent frame reference.
 * Child scopes link to parent scopes for variable lookup across scope boundaries.
 */
public class Scope {

    private static final String PARENT_SLOT_NAME = "$parent";
    public static final int PARENT_SLOT = 0;

    private final Scope parent;
    private final FrameDescriptor.Builder builder;
    private final Map<String, Integer> variables;  // name -> local slot
    private int nextSlot = 1;

    /**
     * Creates a root scope with no parent.
     */
    public Scope() {
        this.parent = null;
        this.builder = FrameDescriptor.newBuilder();
        this.builder.addSlot(FrameSlotKind.Object, PARENT_SLOT_NAME, null);
        this.variables = new HashMap<>();
    }

    /**
     * Creates a child scope with the given parent.
     */
    public Scope(Scope parent) {
        this.parent = parent;
        this.builder = FrameDescriptor.newBuilder();
        this.builder.addSlot(FrameSlotKind.Object, PARENT_SLOT_NAME, null);
        this.variables = new HashMap<>();
    }

    /**
     * Allocates a variable in the current scope and returns its slot index.
     */
    public int allocateVariable(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        int slot = nextSlot++;
        builder.addSlot(FrameSlotKind.Object, name, null);
        variables.put(name, slot);
        return slot;
    }

    /**
     * Looks up a variable in the scope chain.
     * Returns a VariableLookup that encapsulates the runtime access pattern.
     * Returns null if the variable is not found.
     */
    public VariableLookup lookupVariable(String name) {
        return lookupVariable(name, 0);
    }

    private VariableLookup lookupVariable(String name, int currentDepth) {
        if (variables.containsKey(name)) {
            return new VariableLookup(currentDepth, variables.get(name));
        }
        if (parent != null) {
            return parent.lookupVariable(name, currentDepth + 1);
        }
        return null;
    }

    /**
     * Returns the FrameDescriptor.Builder for this scope.
     */
    public FrameDescriptor.Builder getBuilder() {
        return builder;
    }

    /**
     * Returns the parent scope, or null if this is the root.
     */
    public Scope getParent() {
        return parent;
    }

    /**
     * Returns the slot index reserved for storing the parent frame reference.
     */
    public static int getParentSlot() {
        return PARENT_SLOT;
    }

    /**
     * Checks if a variable exists in this scope chain.
     */
    public boolean hasVariable(String name) {
        if (variables.containsKey(name)) {
            return true;
        }
        if (parent != null) {
            return parent.hasVariable(name);
        }
        return false;
    }
}
