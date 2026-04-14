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
 * Each function gets one FrameDescriptor built from a single shared FrameDescriptor.Builder.
 * Nested block scopes (let, if, while, etc.) reuse the same builder.
 * Closures create their own builder (and thus their own FrameDescriptor).
 *
 * Parent frame access is done via frame.getArguments()[0] at runtime.
 * Frame layout:
 *   Slot 0+: local variables / parameters
 */
public class Scope {

    private final Scope parent;
    private final FrameDescriptor.Builder builder;
    private final Map<String, Integer> variables;
    private int nextSlot;

    /**
     * Creates a root scope with no parent and a new builder.
     */
    public Scope() {
        this.parent = null;
        this.builder = FrameDescriptor.newBuilder();
        this.variables = new HashMap<>();
        this.nextSlot = 0;
    }

    /**
     * Creates a child scope that shares the parent's FrameDescriptor.Builder.
     * Used for nested blocks (let, if, while, for, case, etc.).
     */
    public Scope(Scope parent) {
        this(parent, parent.builder);
    }

    /**
     * Creates a function scope with its own FrameDescriptor.Builder.
     * Used for function definitions and closures.
     */
    public static Scope createFunctionScope(Scope parent) {
        return new Scope(parent, FrameDescriptor.newBuilder());
    }

    /**
     * Creates a child scope with a new builder (for closures).
     */
    public static Scope createClosureScope(Scope parent) {
        return new Scope(parent, FrameDescriptor.newBuilder());
    }

    private Scope(Scope parent, FrameDescriptor.Builder builder) {
        this.parent = parent;
        this.builder = builder;
        this.variables = new HashMap<>();

        // If this scope has its own builder (function/closure scope),
        // start slot numbering from 0. Otherwise reuse parent's slot counter.
        if (builder != parent.builder) {
            this.nextSlot = 0;
        } else {
            this.nextSlot = parent.nextSlot;
        }
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
     *
     * If the parent scope shares the same builder (nested block), depth stays 0
     * since they share the same frame at runtime.
     * If the parent scope has a different builder (function/closure), depth increases.
     */
    public VariableLookup lookupVariable(String name) {
        return lookupVariable(name, 0);
    }

    private VariableLookup lookupVariable(String name, int currentDepth) {
        if (variables.containsKey(name)) {
            return new VariableLookup(currentDepth, variables.get(name));
        }
        if (parent != null) {
            // If parent shares the same builder, use depth 0 (same frame)
            int nextDepth = (parent.builder == this.builder) ? 0 : currentDepth + 1;
            return parent.lookupVariable(name, nextDepth);
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
     * Returns the next available slot index.
     */
    public int getNextSlot() {
        return nextSlot;
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

    // ========== Static helpers for runtime access ==========

    /**
     * Traverses up the parent frame chain via frame arguments.
     * Parent frame is always at getArguments()[0].
     */
    public static VirtualFrame getParentFrame(VirtualFrame frame, int depth) {
        VirtualFrame current = frame;
        for (int i = 0; i < depth; i++) {
            current = (VirtualFrame) current.getArguments()[0];
        }
        return current;
    }
}
