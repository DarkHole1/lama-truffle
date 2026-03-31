package com.lama.truffle.nodes;

import com.lama.truffle.LamaContext;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

/**
 * Root node for Lama program execution.
 * Serves as the entry point for Truffle execution.
 */
public class LamaRootNode extends RootNode {

    @Child
    private ExpressionNode body;

    public LamaRootNode(ExpressionNode body) {
        super(null);
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Initialize the environment in the frame
        VariableEnvironment.getOrCreate(frame);
        return body.execute(frame);
    }
}
