package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;

public final class FunctionCallNode extends ExpressionNode {

    @Child private ExpressionNode functionNode;
    @Children private final ExpressionNode[] argumentNodes;
    @Child private DispatchNode dispatchNode;

    public FunctionCallNode(ExpressionNode functionNode, ExpressionNode[] argumentNodes) {
        this.functionNode = functionNode;
        this.argumentNodes = argumentNodes;
        this.dispatchNode = new InteropDispatchNode();
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        Object function = functionNode.execute(frame);

        Object[] arguments = new Object[argumentNodes.length + 1];
        for (int i = 0; i < argumentNodes.length; i++) {
            arguments[i] = argumentNodes[i].execute(frame);
        }

        return dispatchNode.executeDispatch(frame, function, arguments);
    }

    public ExpressionNode getFunctionNode() {
        return functionNode;
    }

    public ExpressionNode[] getArgumentNodes() {
        return argumentNodes;
    }

    public abstract static class DispatchNode extends Node {
        public abstract Object executeDispatch(VirtualFrame frame, Object function, Object[] arguments);
    }
}