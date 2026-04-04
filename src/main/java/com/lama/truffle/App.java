package com.lama.truffle;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.lama.truffle.nodes.*;
import com.lama.truffle.parser.LamaLexer;
import com.lama.truffle.parser.LamaParser;
import com.lama.truffle.parser.LamaVisitorImpl;
import com.oracle.truffle.api.CallTarget;

public class App {

    public static void main(String[] args) throws IOException {
        // if (args.length <= 0) {
        //     System.out.println("Usage: lama-truffle FILE.lama");
        //     System.exit(1);
        // }
        
        // CharStream stream = CharStreams.fromFileName(args[0]);
        CharStream stream = CharStreams.fromFileName("./Sort.lama");
        LamaParser parser = new LamaParser(new CommonTokenStream(new LamaLexer(stream)));
        ExpressionNode node = parser.compilationUnit().accept(new LamaVisitorImpl());

        LamaRootNode rootNode = new LamaRootNode(node);
        CallTarget callTarget = rootNode.getCallTarget();
        
        Object result = callTarget.call();

        System.out.println(result);
    }
}
