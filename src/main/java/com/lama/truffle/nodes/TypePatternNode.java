package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.lama.truffle.types.Sexp;
import com.oracle.truffle.api.frame.VirtualFrame;

public class TypePatternNode extends PatternNode {
    public enum Type {
        BOX,    // Boxed value (non-primitive)
        VAL,    // Integer (unboxed value)
        STR,    // String
        ARRAY,  // Array
        SEXP,   // S-expression
        FUN     // Closure/function
    }

    private final Type type;

    public TypePatternNode(Type type) {
        this.type = type;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
        switch (type) {
            case BOX:
                // Boxed value: anything that's not an Integer (unboxed)
                return value != null && !(value instanceof Long);
            case VAL:
                // Unboxed integer value
                return value instanceof Long;
            case STR:
                return value instanceof String;
            case ARRAY:
                return value != null && value.getClass().isArray();
            case SEXP:
                return value instanceof Sexp;
            case FUN:
                return value instanceof Closure;
            default:
                return false;
        }
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return null;
    }

    public Type getType() {
        return type;
    }
}
