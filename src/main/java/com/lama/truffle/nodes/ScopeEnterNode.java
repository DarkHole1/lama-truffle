package com.lama.truffle.nodes;

import com.lama.truffle.runtime.FrameExecutorRootNode;
import com.lama.truffle.runtime.Scope;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * AST node that creates a new frame for its body expression.
 * Used for scope-creating constructs (let-in, loops, if branches, etc.).
 * 
 * The CallTarget is created lazily on first execution.
 */
public class ScopeEnterNode extends ExpressionNode {

    private final FrameDescriptor descriptor;
    private final ExpressionNode body;

    @CompilerDirectives.CompilationFinal
    private CallTarget callTarget;

    public ScopeEnterNode(FrameDescriptor descriptor, ExpressionNode body) {
        this.descriptor = descriptor;
        this.body = body;
    }

    private CallTarget getCallTarget() {
        if (callTarget == null) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            FrameExecutorRootNode root = new FrameExecutorRootNode(descriptor, body);
            callTarget = root.getCallTarget();
        }
        return callTarget;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Pass current frame as parent (first argument)
        return getCallTarget().call(new Object[]{frame});
    }

    public ExpressionNode getBody() {
        return body;
    }

    public FrameDescriptor getDescriptor() {
        return descriptor;
    }
}
