package com.lama.truffle.nodes;

import com.lama.truffle.types.List;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * AST node for list literals: {expr1, expr2, ..., exprN}
 * Evaluates each expression and constructs a linked List.
 */
public class ListLiteralNode extends ExpressionNode {

    @Children
    private final ExpressionNode[] elementNodes;

    public ListLiteralNode(ExpressionNode[] elementNodes) {
        this.elementNodes = elementNodes;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Build list from right to left (cons cells)
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
