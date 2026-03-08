package com.lama.truffle;

public class App {
    
    public static void main(String[] args) {
        System.out.println("Hello from Lama Truffle!");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Running on GraalVM: " + isRunningOnGraalVM());
        
        // Demonstrate some basic functionality
        demonstrateBasicFeatures();
        
        System.out.println("\nApplication completed successfully!");
    }
    
    private static boolean isRunningOnGraalVM() {
        String vmName = System.getProperty("java.vm.name", "");
        return vmName.contains("GraalVM");
    }
    
    private static void demonstrateBasicFeatures() {
        System.out.println("\n--- Basic Features Demo ---");
        
        // String operations
        String message = "GraalVM Native Image Demo";
        System.out.println("Message: " + message);
        System.out.println("Uppercase: " + message.toUpperCase());
        System.out.println("Length: " + message.length());
        
        // Mathematical operations
        int a = 10;
        int b = 20;
        System.out.println("Math: " + a + " + " + b + " = " + (a + b));
        
        // Array operations
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        System.out.println("Array sum: " + sum);
        
        // Simple object creation
        Calculator calc = new Calculator();
        System.out.println("Calculator result: " + calc.add(15, 25));
    }
    
    static class Calculator {
        public int add(int a, int b) {
            return a + b;
        }
        
        public int multiply(int a, int b) {
            return a * b;
        }
    }
}