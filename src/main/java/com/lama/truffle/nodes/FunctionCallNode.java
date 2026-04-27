package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.IndirectCallNode;

public class FunctionCallNode extends ExpressionNode {

    @Child private ExpressionNode functionNode;
    @Children private final ExpressionNode[] argumentNodes;
    @Child private IndirectCallNode callNode;

    public FunctionCallNode(ExpressionNode functionNode, ExpressionNode[] argumentNodes) {
        this.functionNode = functionNode;
        this.argumentNodes = argumentNodes;
        this.callNode = Truffle.getRuntime().createIndirectCallNode();
    }

    @Override @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        Object funcObj = functionNode.execute(frame);
        
        if (funcObj instanceof Closure closure) {
            Object[] arguments = new Object[argumentNodes.length + 1];
            
            arguments[0] = closure.getMaterializedParentFrame();
            for (int i = 0; i < argumentNodes.length; i++) {
                arguments[i + 1] = argumentNodes[i].execute(frame);
            }

            return callNode.call(closure.getCallTarget(), arguments);
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