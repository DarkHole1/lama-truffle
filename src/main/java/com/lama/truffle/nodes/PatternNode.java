package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

/**
 * Base class for pattern matching in case expressions.
 * Patterns can match values and optionally bind variables.
 */
public abstract class PatternNode extends Node {

    /**
     * Try to match the given value against this pattern.
     * If the pattern contains variables, they are bound in the frame.
     * 
     * @param value The value to match against
     * @param frame The frame to store variable bindings in
     * @return true if the pattern matches, false otherwise
     */
    public abstract boolean match(Object value, VirtualFrame frame);
}
