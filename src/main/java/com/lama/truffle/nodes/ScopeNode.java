package com.lama.truffle.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node that executes a scope: a sequence of definitions followed by an expression.
 * Definitions are executed for their side effects (binding variables to the frame).
 * The final expression is evaluated and its result is returned.
 */
public class ScopeNode extends ExpressionNode {

    private final DefinitionNode[] definitions;
    @Child private ExpressionNode expression;

    public ScopeNode(DefinitionNode[] definitions, ExpressionNode expression) {
        this.definitions = definitions;
        this.expression = expression;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Execute all definitions in order
        for (DefinitionNode definition : definitions) {
            definition.execute(frame);
        }
        // Execute and return the final expression
        if (expression != null) {
            return expression.execute(frame);
        }
        return 0; // Default value if no expression
    }

    public DefinitionNode[] getDefinitions() {
        return definitions;
    }

    public ExpressionNode getExpression() {
        return expression;
    }
}
