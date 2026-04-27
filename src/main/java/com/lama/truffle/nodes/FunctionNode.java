package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;

public class FunctionNode extends ExpressionNode {
    private final String name;
    private final String[] argsNames;
    private final int[] paramSlots;
    private final FrameDescriptor descriptor;
    @Child private ExpressionNode body;

    public FunctionNode(String[] argsNames, int[] paramSlots, FrameDescriptor descriptor,
                        ExpressionNode body) {
        this(null, argsNames, paramSlots, descriptor, body);
    }

    public FunctionNode(String name, String[] argsNames, int[] paramSlots, FrameDescriptor descriptor,
                        ExpressionNode body) {
        this.name = name;
        this.argsNames = argsNames;
        this.paramSlots = paramSlots;
        this.descriptor = descriptor;
        this.body = body;
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

    @Override
    public Object execute(VirtualFrame frame) {
        MaterializedFrame materializedFrame = frame.materialize();
        Closure closure = new Closure(name, argsNames, paramSlots, body, descriptor, materializedFrame);

        return closure;
    }
}
