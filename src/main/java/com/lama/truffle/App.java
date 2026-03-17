package com.lama.truffle;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.lama.truffle.nodes.*;
import com.lama.truffle.nodes.BinaryOperationNode.BinaryOperator;
import com.lama.truffle.parser.LamaLexer;
import com.lama.truffle.parser.LamaParser;
import com.lama.truffle.parser.LamaVisitorImpl;
import com.oracle.truffle.api.CallTarget;

public class App {
    
    public static void main(String[] args) throws IOException {
        System.out.println("Hello from Lama Truffle!");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Running on GraalVM: " + isRunningOnGraalVM());
        
        // Demonstrate basic Truffle node functionality
        demonstrateTruffleNodes();

        System.out.println("\n--- Truffle Parser Demo ---");
        LamaParser parser = new LamaParser(new CommonTokenStream(new LamaLexer(CharStreams.fromFileName("./Sort.lama"))));
        ExpressionNode node = parser.compilationUnit().accept(new LamaVisitorImpl());

        System.out.println(((VariableAccessNode)node).getVariableName());
        
        System.out.println("\nApplication completed successfully!");
    }
    
    private static boolean isRunningOnGraalVM() {
        String vmName = System.getProperty("java.vendor.version", "");
        return vmName.contains("GraalVM");
    }
    
    private static void demonstrateTruffleNodes() {
        System.out.println("\n--- Truffle Nodes Demo ---");
        
        // Create a simple addition expression: 5 + 3
        ExpressionNode five = new IntegerLiteralNode(5);
        ExpressionNode three = new IntegerLiteralNode(3);
        BinaryOperationNode addNode = BinaryOperationNodeGen.create(BinaryOperator.ADD, five, three);
        
        // Create a root node to execute the expression
        LamaRootNode rootNode = new LamaRootNode(addNode);
        
        // Create a call target to execute the root node
        CallTarget callTarget = rootNode.getCallTarget();
        
        // Execute the expression
        Object result = callTarget.call(new Object[]{});
        System.out.println("Result of 5 + 3: " + result);
        
        // Create a more complex expression: (10 + 5) * 2
        ExpressionNode ten = new IntegerLiteralNode(10);
        ExpressionNode fiveAgain = new IntegerLiteralNode(5);
        ExpressionNode two = new IntegerLiteralNode(2);
        
        BinaryOperationNode addNode2 = BinaryOperationNodeGen.create(BinaryOperator.ADD, ten, fiveAgain);
        
        BinaryOperationNode multNode = BinaryOperationNodeGen.create(BinaryOperator.MULTIPLY, addNode2, two);
        
        // Build the expression tree: (10 + 5) * 2
        LamaRootNode complexRootNode = new LamaRootNode(multNode);
        
        // Create a call target to execute the root node
        Object result2 = complexRootNode.getCallTarget().call(new Object[]{});
        System.out.println("Result of (10 + 5) * 2: " + result2);
        
        System.out.println("Note: Full expression evaluation requires complete implementation of node children.");
    }
}
