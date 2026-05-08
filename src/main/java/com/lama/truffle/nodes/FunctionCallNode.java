package com.lama.truffle.nodes;

import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import com.oracle.truffle.api.nodes.Node;

public class FunctionCallNode extends ExpressionNode {

    @Child private ExpressionNode functionNode;
    @Children private final ExpressionNode[] argumentNodes;
    @Child private DispatchNode dispatchNode;

    public FunctionCallNode(ExpressionNode functionNode, ExpressionNode[] argumentNodes) {
        this.functionNode = functionNode;
        this.argumentNodes = argumentNodes;
        this.dispatchNode = new UninitializedDispatchNode(0);
    }

    @Override @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        Object funcObj = functionNode.execute(frame);

        if (!(funcObj instanceof Closure closure)) {
            CompilerDirectives.transferToInterpreter();
            throw new RuntimeException("Expression evaluated into non-function");
        }

        Object[] arguments = new Object[argumentNodes.length + 1];
        arguments[0] = closure.getMaterializedParentFrame();
        for (int i = 0; i < argumentNodes.length; i++) {
            arguments[i + 1] = argumentNodes[i].execute(frame);
        }

        return dispatchNode.executeDispatch(frame, closure, arguments);
    }

    public ExpressionNode getFunctionNode() {
        return functionNode;
    }

    public ExpressionNode[] getArgumentNodes() {
        return argumentNodes;
    }

    public abstract static class DispatchNode extends Node {
        public abstract Object executeDispatch(VirtualFrame frame, Closure closure, Object[] arguments);
    }

    public static final class UninitializedDispatchNode extends DispatchNode {
        private static final int CACHE_LIMIT = 3;
        private final int depth;

        public UninitializedDispatchNode(int depth) {
            this.depth = depth;
        }

        @Override
        public Object executeDispatch(VirtualFrame frame, Closure closure, Object[] arguments) {
            CompilerDirectives.transferToInterpreterAndInvalidate();

            CallTarget callTarget = closure.getCallTarget();

            if (depth < CACHE_LIMIT) {
                CachedDispatchNode cached = new CachedDispatchNode(callTarget, depth + 1);
                return replace(cached).executeDispatch(frame, closure, arguments);
            } else {
                GenericDispatchNode generic = new GenericDispatchNode();
                return replace(generic).executeDispatch(frame, closure, arguments);
            }
        }
    }

    public static final class CachedDispatchNode extends DispatchNode {
        private final CallTarget cachedTarget;
        @Child private DirectCallNode directCallNode;
        @Child private DispatchNode next;

        public CachedDispatchNode(CallTarget cachedTarget, int nextDepth) {
            this.cachedTarget = cachedTarget;
            this.directCallNode = DirectCallNode.create(cachedTarget);
            this.next = new UninitializedDispatchNode(nextDepth);
        }

        @Override
        public Object executeDispatch(VirtualFrame frame, Closure closure, Object[] arguments) {
            if (closure.getCallTarget() == cachedTarget) {
                return directCallNode.call(arguments);
            } else {
                return next.executeDispatch(frame, closure, arguments);
            }
        }
    }

    public static final class GenericDispatchNode extends DispatchNode {
        @Child private IndirectCallNode indirectCallNode = IndirectCallNode.create();

        @Override
        public Object executeDispatch(VirtualFrame frame, Closure closure, Object[] arguments) {
            return indirectCallNode.call(closure.getCallTarget(), arguments);
        }
    }
}