package com.lama.truffle.types;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.runtime.CapturedVariable;
import com.lama.truffle.runtime.FrameExecutorRootNode;
import com.lama.truffle.runtime.Scope;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a closure (function value) that captures execution frames.
 * A closure contains the function body, parameter info, and captured frames.
 */
public final class Closure {

    private final String name;
    private final String[] parameterNames;
    private final int[] parameterSlots;
    private final ExpressionNode body;
    private final FrameDescriptor descriptor;

    /**
     * Captured frames from outer scopes, shared between closures.
     * Index 0 is the immediate lexical parent frame.
     */
    private final VirtualFrame[] capturedFrames;

    /**
     * CallTarget for executing the closure body, created lazily.
     */
    @CompilerDirectives.CompilationFinal
    private CallTarget callTarget;

    public Closure(String name, String[] parameterNames, int[] parameterSlots,
                   ExpressionNode body, FrameDescriptor descriptor, VirtualFrame[] capturedFrames) {
        this.name = name;
        this.parameterNames = parameterNames;
        this.parameterSlots = parameterSlots;
        this.body = body;
        this.descriptor = descriptor;
        this.capturedFrames = capturedFrames;
    }

    /**
     * Builds the captured frames array for a closure being created in the given frame.
     * Walks up the parent chain via slot 0 to find which frames need capturing.
     *
     * @param currentFrame the current execution frame
     * @param capturedVariables static info about which variables are captured
     * @return array of captured frames
     */
    public static VirtualFrame[] buildCapturedFrames(VirtualFrame currentFrame,
                                                      CapturedVariable[] capturedVariables) {
        if (capturedVariables.length == 0) {
            return new VirtualFrame[0];
        }

        // Find the max depth needed
        int maxDepth = 0;
        for (CapturedVariable cv : capturedVariables) {
            if (cv.depth() > maxDepth) {
                maxDepth = cv.depth();
            }
        }

        // Walk up the parent chain
        List<VirtualFrame> frames = new ArrayList<>();
        VirtualFrame current = currentFrame;

        for (int d = 0; d < maxDepth; d++) {
            current = (VirtualFrame) current.getObject(Scope.getParentSlot(current));
            frames.add(current);
        }

        // Map each captured variable to its frame
        VirtualFrame[] result = new VirtualFrame[capturedVariables.length];
        for (int i = 0; i < capturedVariables.length; i++) {
            int frameIndex = capturedVariables[i].depth() - 1;
            if (frameIndex >= 0 && frameIndex < frames.size()) {
                result[i] = frames.get(frameIndex);
            }
        }

        return result;
    }

    /**
     * Gets or creates the CallTarget for this closure's body.
     */
    private CallTarget getCallTarget() {
        if (callTarget == null) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            FrameExecutorRootNode root = new FrameExecutorRootNode(descriptor, body, parameterSlots);
            callTarget = root.getCallTarget();
        }
        return callTarget;
    }

    /**
     * Executes this closure with the given frame and arguments.
     * Creates a new frame for the function body with the captured frame as parent.
     * Stores captured frames in slot 1 (CAPTURED_FRAMES_SLOT) of the new frame.
     */
    public Object execute(VirtualFrame frame, Object[] arguments) {
        // Determine the parent frame for the new frame
        VirtualFrame parentFrame;
        if (capturedFrames.length > 0) {
            parentFrame = capturedFrames[0];
        } else {
            parentFrame = frame;
        }

        // Prepare arguments: [parentFrame, capturedFramesArray, arg0, arg1, ...]
        Object[] callArgs = new Object[arguments.length + 2];
        callArgs[0] = parentFrame;
        callArgs[1] = capturedFrames;
        System.arraycopy(arguments, 0, callArgs, 2, arguments.length);

        return getCallTarget().call(callArgs);
    }

    public String getName() {
        return name;
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

    public VirtualFrame[] getCapturedFrames() {
        return capturedFrames;
    }
}
