package com.lama.truffle.nodes;

import com.lama.truffle.LamaContext;
import com.lama.truffle.types.Closure;
import com.lama.truffle.runtime.CapturedVariable;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for function definitions.
 * Creates a closure, stores it in the function's slot in the frame,
 * and registers it in the function registry.
 */
public class FunctionDefinitionNode extends DefinitionNode {

    private final String functionName;
    private final String[] parameterNames;
    private final int[] parameterSlots;
    private final FrameDescriptor descriptor;
    private final int functionSlot;  // slot in enclosing frame to store the closure
    private final CapturedVariable[] capturedVariables;
    @Child
    private ExpressionNode body;

    public FunctionDefinitionNode(String functionName, String[] parameterNames,
                                   int[] parameterSlots, FrameDescriptor descriptor,
                                   ExpressionNode body, CapturedVariable[] capturedVariables,
                                   int functionSlot) {
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.parameterSlots = parameterSlots;
        this.descriptor = descriptor;
        this.body = body;
        this.capturedVariables = capturedVariables;
        this.functionSlot = functionSlot;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Build captured frames from static info
        VirtualFrame[] capturedFrames = Closure.buildCapturedFrames(frame, capturedVariables);

        // Create closure
        Closure closure = new Closure(functionName, parameterNames, parameterSlots, body, descriptor, capturedFrames);

        // Store closure in the function's slot in the enclosing frame
        frame.setObject(functionSlot, closure);

        // Also register in the language context for external access
        LamaContext context = LamaContext.getCurrentContext();
        context.registerFunction(functionName, closure);

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

    public CapturedVariable[] getCapturedVariables() {
        return capturedVariables;
    }

    public int getFunctionSlot() {
        return functionSlot;
    }
}
