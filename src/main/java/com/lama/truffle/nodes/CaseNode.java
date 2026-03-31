package com.lama.truffle.nodes;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.HashMap;
import java.util.Map;

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
                // Save current environment state
                VariableEnvironment savedEnv = null;
                Map<String, Object> savedVariables = new HashMap<>();
                try {
                    savedEnv = VariableEnvironment.getOrCreate(frame);
                    // Save the current variables
                    savedVariables.putAll(savedEnv.getLocalVariables());
                } catch (IllegalArgumentException e) {
                    // Environment slot doesn't exist yet
                }

                // Try to match the pattern
                if (caseBranch.matches(scrutineeValue, frame)) {
                    // Pattern matched - execute the body with bound variables
                    Object result = caseBranch.execute(frame);
                    
                    // Restore saved variables
                    if (savedEnv != null) {
                        for (Map.Entry<String, Object> entry : savedVariables.entrySet()) {
                            savedEnv.set(entry.getKey(), entry.getValue());
                        }
                    }
                    
                    return result;
                }

                // Pattern didn't match - restore environment and try next branch
                if (savedEnv != null) {
                    // Clear variables added in this branch and restore saved ones
                    savedEnv.getLocalVariables().clear();
                    for (Map.Entry<String, Object> entry : savedVariables.entrySet()) {
                        savedEnv.set(entry.getKey(), entry.getValue());
                    }
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
