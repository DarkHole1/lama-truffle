package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for case (pattern-matching) expressions.
 * Evaluates the scrutinee and matches against patterns in order.
 */
public class CaseNode extends ExpressionNode {

    @Child private ExpressionNode scrutinee;
    @Children private ExpressionNode[] branches;

    public CaseNode(ExpressionNode scrutinee, ExpressionNode[] branches) {
        this.scrutinee = scrutinee;
        this.branches = branches;
    }

    @Specialization
    protected Object doCase(VirtualFrame frame) {
        Object scrutineeValue = scrutinee.execute(frame);

        // TODO
        // Try each branch in order
        for (ExpressionNode branch : branches) {
            // For now, just execute the first branch (proper pattern matching needs implementation)
            return branch.execute(frame);
        }

        return 0; // No match found
    }

    public ExpressionNode getScrutinee() {
        return scrutinee;
    }

    public ExpressionNode[] getBranches() {
        return branches;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return doCase(frame);
    }
}
