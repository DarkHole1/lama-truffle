package com.lama.truffle;

import java.io.File;
import java.io.IOException;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            System.out.println("Usage: lama-truffle FILE.lama");
            System.exit(1);
        }

        try (Context context = Context.create()) {
            Source source = Source.newBuilder("lama", new File(args[0])).build();
            Object result = context.eval(source);
            // System.out.println(result);
        }
    }
}
