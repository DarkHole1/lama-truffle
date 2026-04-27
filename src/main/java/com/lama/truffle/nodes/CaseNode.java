package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class CaseNode extends ExpressionNode {

    @Child private ExpressionNode scrutinee;
    @Children private final PatternNode[] patterns;
    @Children private final ExpressionNode[] bodies;
    private final int scrutineeSlot;

    public CaseNode(ExpressionNode scrutinee, PatternNode[] patterns, ExpressionNode[] bodies, int scrutineeSlot) {
        this.scrutinee = scrutinee;
        this.patterns = patterns;
        this.bodies = bodies;
        this.scrutineeSlot = scrutineeSlot;
    }

    @Specialization @ExplodeLoop
    protected Object doCase(VirtualFrame frame) {
        Object scrutineeValue = scrutinee.execute(frame);
        
        for (int i = 0; i < patterns.length; i++) {
            frame.setObject(scrutineeSlot, scrutineeValue);
            if (patterns[i].executeBoolean(frame)) {
                return bodies[i].execute(frame);
            }
        }

        throw new RuntimeException("No matching pattern found in case expression");
    }

    public ExpressionNode getScrutinee() {
        return scrutinee;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return doCase(frame);
    }
}
