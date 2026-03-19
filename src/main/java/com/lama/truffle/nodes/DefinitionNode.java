package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Abstract base node for definitions (variable, function, infix).
 * Definitions are executed for their side effects (binding to frame) rather than returning a value.
 * Returns null after execution.
 */
public abstract class DefinitionNode extends ExpressionNode {

    /**
     * Execute the definition, which typically binds a name to a value in the frame.
     * @param frame the current virtual frame
     * @return null (definitions are for side effects)
     */
    @Override
    public abstract Object execute(VirtualFrame frame);
}
