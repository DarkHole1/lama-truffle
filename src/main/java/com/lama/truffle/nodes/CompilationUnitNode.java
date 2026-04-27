package com.lama.truffle.nodes;

import java.util.Scanner;

import com.lama.truffle.runtime.Scope;
import com.lama.truffle.types.Closure;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class CompilationUnitNode extends ExpressionNode {
    private final static Closure[] funcs = {
        new Closure("read", CompilationUnitNode::read),
        new Closure("write", CompilationUnitNode::write),
        new Closure("length", CompilationUnitNode::length),
    };

    private final static Scanner s = new Scanner(System.in);

    @Child private ExpressionNode body;
    private final int[] slots;

    public CompilationUnitNode(ExpressionNode body, int[] slots) {
        this.body = body;
        this.slots = slots;
    }

    @Override @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        for (int i = 0; i < slots.length; i++) {
            frame.setObject(slots[i], funcs[i]);
        }
        return this.body.execute(frame);
    }

    public static int[] prepareScope(Scope scope) {
        int[] result = new int[funcs.length];
        for (int i = 0; i < funcs.length; i++) {
            result[i] = scope.allocateVariable(funcs[i].getName());
        }
        return result;
    }

    public static Object read(Object[] args) {
        System.out.print(" > ");
        return s.nextLong();
    }

    public static Object write(Object[] args) {
        System.out.println(args[1]);
        return 0L;
    }

    public static Object length(Object[] args) {
        Object arg = args[1];
        if (arg instanceof String s) {
            return (long)(s.length());
        }
        if (arg instanceof Object[] s) {
            return (long)(s.length);
        }
        return 0L;
    }
}
