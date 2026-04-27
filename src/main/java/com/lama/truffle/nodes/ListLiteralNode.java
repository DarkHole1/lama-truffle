package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class ListLiteralNode extends ExpressionNode {
    @Children
    private final ExpressionNode[] elementNodes;

    public ListLiteralNode(ExpressionNode[] elementNodes) {
        this.elementNodes = elementNodes;
    }

    @Override @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        List result = null;
        for (int i = elementNodes.length - 1; i >= 0; i--) {
            Object value = elementNodes[i].execute(frame);
            result = new List(value, result);
        }
        return result;
    }

    public ExpressionNode[] getElementNodes() {
        return elementNodes;
    }
}
