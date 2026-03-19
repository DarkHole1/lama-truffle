package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for function definitions.
 * Creates a closure and binds it to the frame under the function name.
 */
public class FunctionDefinitionNode extends DefinitionNode {

    private final String functionName;
    private final String[] parameterNames;
    @Child private ExpressionNode body;

    public FunctionDefinitionNode(String functionName, String[] parameterNames, ExpressionNode body) {
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Closure closure = new Closure(functionName, parameterNames, body, frame);
        VariableEnvironment env = VariableEnvironment.getOrCreate(frame);
        env.set(functionName, closure);
        return null;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String[] getParameterNames() {
        return parameterNames;
    }

    public ExpressionNode getBody() {
        return body;
    }
}
