package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public class LamaRootNode extends RootNode {

    @Child
    private ExpressionNode body;

    public LamaRootNode(ExpressionNode body, FrameDescriptor descriptor) {
        super(null, descriptor);
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return body.execute(frame);
    }

    @Override
    public String getName() {
        return "@root";
    }
}
