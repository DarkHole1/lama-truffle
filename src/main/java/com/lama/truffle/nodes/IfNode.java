package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Node for if-then-else expressions.
 * Supports multiple elif branches and an optional else branch.
 */
public class IfNode extends ExpressionNode {

    @Child private ExpressionNode condition;
    @Child private ExpressionNode thenBranch;
    private final ExpressionNode[] elifConditions;
    private final ExpressionNode[] elifBranches;
    private final ExpressionNode elseBranch;

    public IfNode(ExpressionNode condition, ExpressionNode thenBranch,
                  ExpressionNode[] elifConditions, ExpressionNode[] elifBranches,
                  ExpressionNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elifConditions = elifConditions != null ? elifConditions : new ExpressionNode[0];
        this.elifBranches = elifBranches != null ? elifBranches : new ExpressionNode[0];
        this.elseBranch = elseBranch;
    }

    @Specialization
    protected Object doIf(VirtualFrame frame) {
        long conditionValue = (long) condition.execute(frame);
        
        if (conditionValue != 0) { // Non-zero is true
            return thenBranch.execute(frame);
        }

        // Check elif conditions
        for (int i = 0; i < elifConditions.length; i++) {
            long elifConditionValue = (long) elifConditions[i].execute(frame);
            if (elifConditionValue != 0) {
                return elifBranches[i].execute(frame);
            }
        }

        // Execute else branch if present
        if (elseBranch != null) {
            return elseBranch.execute(frame);
        }

        return 0; // Default return when no branch matches
    }

    public ExpressionNode getCondition() {
        return condition;
    }

    public ExpressionNode getThenBranch() {
        return thenBranch;
    }

    public ExpressionNode[] getElifConditions() {
        return elifConditions;
    }

    public ExpressionNode[] getElifBranches() {
        return elifBranches;
    }

    public ExpressionNode getElseBranch() {
        return elseBranch;
    }
    
    @Override
    public Object execute(VirtualFrame frame) {
        return doIf(frame);
    }
}
