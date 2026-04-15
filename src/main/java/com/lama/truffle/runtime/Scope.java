package com.lama.truffle.runtime;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private final Scope parent;
    private final FrameDescriptor.Builder builder;
    private final Map<String, Integer> variables;

    public Scope() {
        this.parent = null;
        this.builder = FrameDescriptor.newBuilder();
        this.variables = new HashMap<>();
    }

    public Scope(Scope parent) {
        this(parent, parent.builder);
    }

    public static Scope createFunctionScope(Scope parent) {
        return new Scope(parent, FrameDescriptor.newBuilder());
    }

    public static Scope createClosureScope(Scope parent) {
        return new Scope(parent, FrameDescriptor.newBuilder());
    }

    private Scope(Scope parent, FrameDescriptor.Builder builder) {
        this.parent = parent;
        this.builder = builder;
        this.variables = new HashMap<>();
    }

    public int allocateVariable(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        int slot = builder.addSlot(FrameSlotKind.Object, name, null);
        variables.put(name, slot);
        return slot;
    }

    public VariableLookup lookupVariable(String name) {
        return lookupVariable(name, 0);
    }

    private VariableLookup lookupVariable(String name, int currentDepth) {
        if (variables.containsKey(name)) {
            return new VariableLookup(currentDepth, variables.get(name));
        }
        if (parent != null) {
            int nextDepth = (parent.builder == this.builder) ? currentDepth : currentDepth + 1;
            return parent.lookupVariable(name, nextDepth);
        }
        return null;
    }

    public FrameDescriptor.Builder getBuilder() {
        return builder;
    }

    public Scope getParent() {
        return parent;
    }

    public boolean hasVariable(String name) {
        if (variables.containsKey(name)) {
            return true;
        }
        if (parent != null) {
            return parent.hasVariable(name);
        }
        return false;
    }

    public static VirtualFrame getParentFrame(VirtualFrame frame, int depth) {
        VirtualFrame current = frame;
        for (int i = 0; i < depth; i++) {
            current = (VirtualFrame) current.getArguments()[0];
        }
        return current;
    }
}
