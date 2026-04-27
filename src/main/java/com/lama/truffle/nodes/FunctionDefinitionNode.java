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
    private final int functionSlot;  
    @Child
    private ExpressionNode body;

    public FunctionDefinitionNode(String functionName, String[] parameterNames,
                                   int[] parameterSlots, FrameDescriptor descriptor,
                                   ExpressionNode body, int functionSlot) {
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.parameterSlots = parameterSlots;
        this.descriptor = descriptor;
        this.body = body;
        this.functionSlot = functionSlot;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        MaterializedFrame materializedFrame = frame.materialize();
        Closure closure = new Closure(functionName, parameterNames, parameterSlots, body, descriptor, materializedFrame);

        frame.setObject(functionSlot, closure);

        return null;
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

    public int getFunctionSlot() {
        return functionSlot;
    }
}
