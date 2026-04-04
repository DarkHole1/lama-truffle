package com.lama.truffle.types;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.runtime.FrameExecutorRootNode;
import com.lama.truffle.runtime.Scope;
import com.lama.truffle.runtime.VariableLookup;
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
     * @param maxDepth the maximum depth of variable access (how many parent traversals)
     * @return array of captured frames, or empty array if nothing needs capturing
     */
    public static VirtualFrame[] buildCapturedFrames(VirtualFrame currentFrame, int maxDepth) {
        if (maxDepth <= 0) {
            return new VirtualFrame[0];
        }

        // Collect frames that need capturing (from immediate parent up to maxDepth)
        List<VirtualFrame> frames = new ArrayList<>();
        VirtualFrame current = currentFrame;

        for (int d = 0; d < maxDepth; d++) {
            Object parentObj = current.getObject(Scope.getParentSlot());
            if (parentObj == null) break;
            current = (VirtualFrame) parentObj;
            frames.add(current);
        }

        return frames.toArray(new VirtualFrame[0]);
    }

    /**
     * Marks all provided VariableLookups as captured with indices into the captured frames array.
     *
     * @param lookups list of VariableLookups to mark
     * @param capturedFrames the captured frames array
     */
    public static void markCapturedLookups(List<VariableLookup> lookups, VirtualFrame[] capturedFrames) {
        for (VariableLookup lookup : lookups) {
            if (lookup.getDepth() > 0) {
                int frameIndex = lookup.getDepth() - 1;
                if (frameIndex >= 0 && frameIndex < capturedFrames.length) {
                    lookup.markCaptured(frameIndex, capturedFrames);
                }
            }
        }
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
     */
    public Object execute(VirtualFrame frame, Object[] arguments) {
        // Determine the parent frame for the new frame
        VirtualFrame parentFrame;
        if (capturedFrames.length > 0) {
            parentFrame = capturedFrames[0];
        } else {
            parentFrame = frame;
        }

        // Prepare arguments: [parentFrame, arg0, arg1, ...]
        Object[] callArgs = new Object[arguments.length + 1];
        callArgs[0] = parentFrame;
        System.arraycopy(arguments, 0, callArgs, 1, arguments.length);

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
