package com.lama.truffle.nodes;

import com.lama.truffle.LamaContext;
import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for function definitions.
 * Creates a closure and registers it in the language context.
 */
public class FunctionDefinitionNode extends DefinitionNode {

    private final String functionName;
    private final String[] parameterNames;
    @Child
    private ExpressionNode body;

    public FunctionDefinitionNode(String functionName, String[] parameterNames, ExpressionNode body) {
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Get the current environment (captures the lexical scope)
        VariableEnvironment env = VariableEnvironment.getOrCreate(frame);
        
        // Create closure with the current environment
        Closure closure = new Closure(functionName, parameterNames, body, env);
        
        // Register in the language context
        LamaContext context = LamaContext.getCurrentContext();
        context.registerFunction(functionName, closure);
        
        // Also store in local environment for recursive calls within same scope
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
