package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

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

        for (ExpressionNode branch : branches) {
            if (branch instanceof CaseBranchNode) {
                CaseBranchNode caseBranch = (CaseBranchNode) branch;

                if (caseBranch.matches(scrutineeValue, frame)) {
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
