package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class ScopeNode extends ExpressionNode {

    @Children private DefinitionNode[] definitions;
    @Child private ExpressionNode expression;

    public ScopeNode(DefinitionNode[] definitions, ExpressionNode expression) {
        this.definitions = definitions;
        this.expression = expression;
    }

    @Override @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        for (DefinitionNode definition : definitions) {
            definition.execute(frame);
        }
        if (expression != null) {
            return expression.execute(frame);
        }
        return 0;
    }

    public DefinitionNode[] getDefinitions() {
        return definitions;
    }

    public ExpressionNode getExpression() {
        return expression;
    }
}
