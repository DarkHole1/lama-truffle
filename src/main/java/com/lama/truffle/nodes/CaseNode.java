package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for case (pattern-matching) expressions.
 * Evaluates the scrutinee and matches against patterns in order.
 * Pattern variables are allocated in the case expression's scope,
 * so no save/restore is needed - each branch writes to its own slots.
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

        // Try each branch in order
        for (ExpressionNode branch : branches) {
            if (branch instanceof CaseBranchNode) {
                CaseBranchNode caseBranch = (CaseBranchNode) branch;

                // Try to match the pattern (binds variables to slots in frame)
                if (caseBranch.matches(scrutineeValue, frame)) {
                    // Pattern matched - execute the body with bound variables
                    return caseBranch.execute(frame);
                }
            }
        }

        throw new RuntimeException("No matching pattern found in case expression");
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
