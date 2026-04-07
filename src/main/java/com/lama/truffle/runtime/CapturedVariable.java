package com.lama.truffle.runtime;

/**
 * Static information about a captured variable, determined at AST build time.
 * Records the depth (number of parent traversals) and slot index needed to access
 * a variable from an enclosing scope.
 */
public record CapturedVariable(int depth, int slot) {
}
