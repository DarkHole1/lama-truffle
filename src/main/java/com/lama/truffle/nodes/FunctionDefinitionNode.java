package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;

public class FunctionDefinitionNode extends DefinitionNode {

    private final String functionName;
    private final String[] parameterNames;
    private final int[] parameterSlots;
    private final FrameDescriptor descriptor;
    // private final int functionSlot;  
    @Child
    private ExpressionNode body;

    public FunctionDefinitionNode(String functionName, String[] parameterNames,
                                   int[] parameterSlots, FrameDescriptor descriptor,
                                   ExpressionNode body) {
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.parameterSlots = parameterSlots;
        this.descriptor = descriptor;
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        MaterializedFrame materializedFrame = frame.materialize();
        return new Closure(functionName, parameterNames, parameterSlots, body, descriptor, materializedFrame);
    }

    public static ExpressionNode create(String functionName, String[] parameterNames,
                                   int[] parameterSlots, FrameDescriptor descriptor,
                                   ExpressionNode body, int functionSlot) {
        return WriteLocalVariableNodeGen.create(new FunctionDefinitionNode(functionName, parameterNames, parameterSlots, descriptor, body), functionSlot);
    }

    public String getFunctionName() {
        return functionName;
    }

    public String[] getParameterNames() {
        return parameterNames;
    }

    public int[] getParameterSlots() {
        return parameterSlots;
    }

    public ExpressionNode getBody() {
        return body;
    }

    public FrameDescriptor getDescriptor() {
        return descriptor;
    }
}
