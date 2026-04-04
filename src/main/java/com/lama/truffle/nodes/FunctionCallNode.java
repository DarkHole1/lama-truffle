package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.VirtualFrame;

public class FunctionCallNode extends ExpressionNode {

    @Child private ExpressionNode functionNode;
    @Children private final ExpressionNode[] argumentNodes;

    public FunctionCallNode(ExpressionNode functionNode, ExpressionNode[] argumentNodes) {
        this.functionNode = functionNode;
        this.argumentNodes = argumentNodes;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object funcObj = functionNode.execute(frame);

        Object[] arguments = new Object[argumentNodes.length];
        for (int i = 0; i < argumentNodes.length; i++) {
            arguments[i] = argumentNodes[i].execute(frame);
        }

        if (funcObj instanceof Closure) {
            Closure closure = (Closure) funcObj;
            return closure.execute(frame, arguments);
        } else {
            throw new RuntimeException("Expression evaluated into non-function");
        }
    }

    public ExpressionNode getFunctionNode() {
        return functionNode;
    }

    public ExpressionNode[] getArgumentNodes() {
        return argumentNodes;
    }
}