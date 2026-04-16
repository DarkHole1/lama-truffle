package com.lama.truffle.nodes;

import com.lama.truffle.LamaContext;
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
        if (LamaContext.getCurrentContext() == null) {
            LamaContext context = new LamaContext(null);
            LamaContext.setCurrentContext(context);
        }

        return body.execute(frame);
    }
}
