package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;

public class LamaRootNode extends RootNode {

    @Node.Child
    private ExpressionNode body;

    public LamaRootNode(ExpressionNode body) {
        super(null);
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return body.execute(frame);
    }
}