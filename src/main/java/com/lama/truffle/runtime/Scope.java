package com.lama.truffle.runtime;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for tracking variables and allocating frame slots during AST building.
 * Not an AST node - used purely at parse/build time.
 * 
 * Frame layout:
 *   Slot 0: parent frame reference ($parent)
 *   Slot 1: captured frames array ($captured) - only in function scopes
 *   Slot 2+: local variables
 * 
 * The parent slot index is stored in the FrameDescriptor's info field,
 * accessible at runtime via frame.getFrameDescriptor().getInfo().
 */
public class Scope {

    private static final String PARENT_SLOT_NAME = "$parent";
    private static final String CAPTURED_SLOT_NAME = "$captured";

    public static final int PARENT_SLOT = 0;
    public static final int CAPTURED_FRAMES_SLOT = 1;

    private final Scope parent;
    private final FrameDescriptor.Builder builder;
    private final Map<String, Integer> variables;
    private final boolean isFunctionScope;
    private int nextSlot;

    /**
     * Creates a root scope with no parent.
     */
    public Scope() {
        this.parent = null;
        this.builder = FrameDescriptor.newBuilder();
        this.builder.addSlot(FrameSlotKind.Object, PARENT_SLOT_NAME, null);
        this.builder.info(PARENT_SLOT);
        this.variables = new HashMap<>();
        this.isFunctionScope = false;
        this.nextSlot = 1;
    }

    /**
     * Creates a child scope with the given parent.
     */
    public Scope(Scope parent) {
        this(parent, false);
    }

    /**
     * Creates a child scope, optionally reserving slot 1 for captured frames array.
     */
    private Scope(Scope parent, boolean isFunctionScope) {
        this.parent = parent;
        this.builder = FrameDescriptor.newBuilder();
        this.builder.addSlot(FrameSlotKind.Object, PARENT_SLOT_NAME, null);
        this.builder.info(PARENT_SLOT);
        this.isFunctionScope = isFunctionScope;

        if (isFunctionScope) {
            this.builder.addSlot(FrameSlotKind.Object, CAPTURED_SLOT_NAME, null);
            this.nextSlot = 2;
        } else {
            this.nextSlot = 1;
        }

        this.variables = new HashMap<>();
    }

    /**
     * Creates a function scope with slot 1 reserved for captured frames array.
     */
    public static Scope createFunctionScope(Scope parent) {
        return new Scope(parent, true);
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
     * Returns whether this is a function scope (has captured frames slot).
     */
    public boolean isFunctionScope() {
        return isFunctionScope;
    }

    // ========== Static helpers for runtime access ==========

    /**
     * Gets the parent slot index from a frame's descriptor info.
     */
    public static int getParentSlot(VirtualFrame frame) {
        return (int) frame.getFrameDescriptor().getInfo();
    }

    /**
     * Traverses up the parent chain by the given depth and returns the target frame.
     */
    public static VirtualFrame getParentFrame(VirtualFrame frame, int depth) {
        VirtualFrame current = frame;
        int parentSlot = getParentSlot(current);
        for (int i = 0; i < depth; i++) {
            current = (VirtualFrame) current.getObject(parentSlot);
            parentSlot = getParentSlot(current);
        }
        return current;
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
