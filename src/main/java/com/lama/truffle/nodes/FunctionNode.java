package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.lama.truffle.runtime.CapturedVariable;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

public class FunctionNode extends ExpressionNode {
    private final String name;
    private final String[] argsNames;
    private final int[] paramSlots;
    private final FrameDescriptor descriptor;
    private final CapturedVariable[] capturedVariables;
    @Child private ExpressionNode body;

    public FunctionNode(String[] argsNames, int[] paramSlots, FrameDescriptor descriptor,
                        ExpressionNode body, CapturedVariable[] capturedVariables) {
        this(null, argsNames, paramSlots, descriptor, body, capturedVariables);
    }

    public FunctionNode(String name, String[] argsNames, int[] paramSlots, FrameDescriptor descriptor,
                        ExpressionNode body, CapturedVariable[] capturedVariables) {
        this.name = name;
        this.argsNames = argsNames;
        this.paramSlots = paramSlots;
        this.descriptor = descriptor;
        this.body = body;
        this.capturedVariables = capturedVariables;
    }

    public String getName() {
        return name;
    }

    public String[] getArgsNames() {
        return argsNames;
    }

    public int[] getParamSlots() {
        return paramSlots;
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

    @Override
    public Object execute(VirtualFrame frame) {
        // Build captured frames from static info
        VirtualFrame[] capturedFrames = Closure.buildCapturedFrames(frame, capturedVariables);

        // Create closure
        Closure closure = new Closure(name, argsNames, paramSlots, body, descriptor, capturedFrames);

        // If this is a named function, register it
        if (name != null) {
            FunctionRegistry.register(name, closure);
        }

        return closure;
    }
}
