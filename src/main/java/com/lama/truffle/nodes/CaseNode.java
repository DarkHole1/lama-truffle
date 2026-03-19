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

        // Try each branch in order
        for (ExpressionNode branch : branches) {
            if (branch instanceof CaseBranchNode) {
                CaseBranchNode caseBranch = (CaseBranchNode) branch;
                
                // Create a fresh environment for this branch's variable bindings
                // The VariableEnvironment uses a fixed slot, so we need to save and restore
                VariableEnvironment savedEnv = null;
                try {
                    savedEnv = VariableEnvironment.getOrCreate(frame);
                } catch (IllegalArgumentException e) {
                    // Environment slot doesn't exist yet
                }
                
                // Clear environment for fresh variable bindings in this branch
                VariableEnvironment newEnv = new VariableEnvironment();
                if (savedEnv != null) {
                    // Copy existing variables to preserve outer scope
                    for (java.util.Map.Entry<String, Object> entry : savedEnv.getVariables().entrySet()) {
                        newEnv.set(entry.getKey(), entry.getValue());
                    }
                }
                frame.setObject(VariableEnvironment.SLOT_INDEX, newEnv);
                
                // Try to match the pattern
                if (caseBranch.matches(scrutineeValue, frame)) {
                    // Pattern matched - execute the body with bound variables
                    return caseBranch.execute(frame);
                }
                
                // Pattern didn't match - restore environment and try next branch
                if (savedEnv != null) {
                    frame.setObject(VariableEnvironment.SLOT_INDEX, savedEnv);
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
