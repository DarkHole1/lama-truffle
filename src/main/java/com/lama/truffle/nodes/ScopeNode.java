package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class ScopeNode extends ExpressionNode {

    @Children private ExpressionNode[] definitions;
    @Child private ExpressionNode expression;

    public ScopeNode(ExpressionNode[] definitions, ExpressionNode expression) {
        this.definitions = definitions;
        this.expression = expression;
    }

    @Override @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        for (ExpressionNode definition : definitions) {
            definition.execute(frame);
        }
        if (expression != null) {
            return expression.execute(frame);
        }
        return 0L;
    }

    public ExpressionNode[] getDefinitions() {
        return definitions;
    }

    public ExpressionNode getExpression() {
        return expression;
    }
}
