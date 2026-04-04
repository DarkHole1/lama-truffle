package com.lama.truffle.nodes;

import com.lama.truffle.LamaContext;
import com.lama.truffle.types.Closure;
import com.lama.truffle.runtime.Scope;
import com.lama.truffle.runtime.VariableLookup;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * Node for function definitions.
 * Creates a closure and registers it in the function registry.
 */
public class FunctionDefinitionNode extends DefinitionNode {

    private final String functionName;
    private final String[] parameterNames;
    private final int[] parameterSlots;
    private final FrameDescriptor descriptor;
    @Child
    private ExpressionNode body;

    public FunctionDefinitionNode(String functionName, String[] parameterNames,
                                   int[] parameterSlots, FrameDescriptor descriptor, ExpressionNode body) {
        this.functionName = functionName;
        this.parameterNames = parameterNames;
        this.parameterSlots = parameterSlots;
        this.descriptor = descriptor;
        this.body = body;
    }

    /**
     * Collects all VariableLookups from the body that access outer scopes (depth > 0).
     */
    private void collectOuterLookups(ExpressionNode node, List<VariableLookup> lookups, int[] maxDepth) {
        if (node == null) return;

        if (node instanceof VariableAccessNode) {
            VariableAccessNode van = (VariableAccessNode) node;
            VariableLookup lookup = van.getLookup();
            if (lookup.getDepth() > 0) {
                lookups.add(lookup);
                if (lookup.getDepth() > maxDepth[0]) {
                    maxDepth[0] = lookup.getDepth();
                }
            }
        } else if (node instanceof VariableWriteNode) {
            VariableWriteNode vwn = (VariableWriteNode) node;
            VariableLookup lookup = vwn.getLookup();
            if (lookup.getDepth() > 0) {
                lookups.add(lookup);
                if (lookup.getDepth() > maxDepth[0]) {
                    maxDepth[0] = lookup.getDepth();
                }
            }
            collectOuterLookups(vwn.getValueNode(), lookups, maxDepth);
        } else if (node instanceof ScopeNode) {
            ScopeNode sn = (ScopeNode) node;
            for (DefinitionNode def : sn.getDefinitions()) {
                collectOuterLookupsFromDefinition(def, lookups, maxDepth);
            }
            collectOuterLookups(sn.getExpression(), lookups, maxDepth);
        } else if (node instanceof ScopeEnterNode) {
            ScopeEnterNode sen = (ScopeEnterNode) node;
            collectOuterLookups(sen.getBody(), lookups, maxDepth);
        } else if (node instanceof IfNode) {
            IfNode ifn = (IfNode) node;
            collectOuterLookups(ifn.getCondition(), lookups, maxDepth);
            collectOuterLookups(ifn.getThenBranch(), lookups, maxDepth);
            for (ExpressionNode cond : ifn.getElifConditions()) {
                collectOuterLookups(cond, lookups, maxDepth);
            }
            for (ExpressionNode branch : ifn.getElifBranches()) {
                collectOuterLookups(branch, lookups, maxDepth);
            }
            collectOuterLookups(ifn.getElseBranch(), lookups, maxDepth);
        } else if (node instanceof ForNode) {
            ForNode fn = (ForNode) node;
            collectOuterLookups(fn.getInit(), lookups, maxDepth);
            collectOuterLookups(fn.getLoopScope(), lookups, maxDepth);
        } else if (node instanceof WhileNode) {
            WhileNode wn = (WhileNode) node;
            collectOuterLookups(wn.getLoopScope(), lookups, maxDepth);
        } else if (node instanceof DoWhileNode) {
            DoWhileNode dwn = (DoWhileNode) node;
            collectOuterLookups(dwn.getLoopScope(), lookups, maxDepth);
        } else if (node instanceof CaseNode) {
            CaseNode cn = (CaseNode) node;
            collectOuterLookups(cn.getScrutinee(), lookups, maxDepth);
            for (ExpressionNode branch : cn.getBranches()) {
                if (branch instanceof CaseBranchNode) {
                    CaseBranchNode cbn = (CaseBranchNode) branch;
                    collectOuterLookups(cbn.getBody(), lookups, maxDepth);
                }
            }
        } else if (node instanceof FunctionCallNode) {
            FunctionCallNode fcn = (FunctionCallNode) node;
            collectOuterLookups(fcn.getFunctionNode(), lookups, maxDepth);
            for (ExpressionNode arg : fcn.getArgumentNodes()) {
                collectOuterLookups(arg, lookups, maxDepth);
            }
        } else if (node instanceof ForLoopBodyNode) {
            ForLoopBodyNode flbn = (ForLoopBodyNode) node;
            collectOuterLookups(flbn.getCondition(), lookups, maxDepth);
            collectOuterLookups(flbn.getUpdate(), lookups, maxDepth);
            collectOuterLookups(flbn.getBody(), lookups, maxDepth);
        } else if (node instanceof WhileLoopBodyNode) {
            WhileLoopBodyNode wlbn = (WhileLoopBodyNode) node;
            collectOuterLookups(wlbn.getCondition(), lookups, maxDepth);
            collectOuterLookups(wlbn.getBody(), lookups, maxDepth);
        } else if (node instanceof DoWhileLoopBodyNode) {
            DoWhileLoopBodyNode dwlbn = (DoWhileLoopBodyNode) node;
            collectOuterLookups(dwlbn.getBody(), lookups, maxDepth);
            collectOuterLookups(dwlbn.getCondition(), lookups, maxDepth);
        }
        // BinaryOperationNode and literals don't need special traversal
    }

    private void collectOuterLookupsFromDefinition(DefinitionNode def, List<VariableLookup> lookups, int[] maxDepth) {
        if (def instanceof VariableDefinitionNode) {
            VariableDefinitionNode vdn = (VariableDefinitionNode) def;
            collectOuterLookups(vdn.getValueNode(), lookups, maxDepth);
        }
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Collect variable lookups that access outer scopes
        List<VariableLookup> lookups = new ArrayList<>();
        int[] maxDepth = new int[]{0};
        collectOuterLookups(body, lookups, maxDepth);

        // Build captured frames array
        VirtualFrame[] capturedFrames = Closure.buildCapturedFrames(frame, maxDepth[0]);

        // Mark lookups as captured
        Closure.markCapturedLookups(lookups, capturedFrames);

        // Create closure
        Closure closure = new Closure(functionName, parameterNames, parameterSlots, body, descriptor, capturedFrames);

        // Register in the language context
        LamaContext context = LamaContext.getCurrentContext();
        context.registerFunction(functionName, closure);

        return null;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String[] getParameterNames() {
        return parameterNames;
    }

    public int[] getParameterSlots() {
        return parameterSlots;
    }

    public ExpressionNode getBody() {
        return body;
    }

    public FrameDescriptor getDescriptor() {
        return descriptor;
    }
}
