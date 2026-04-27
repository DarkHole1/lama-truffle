package com.lama.truffle;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.lama.truffle.nodes.ExpressionNode;
import com.lama.truffle.nodes.LamaRootNode;
import com.lama.truffle.parser.LamaLexer;
import com.lama.truffle.parser.LamaParser;
import com.lama.truffle.parser.LamaVisitorImpl;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.TruffleLanguage;

@TruffleLanguage.Registration(name = "Lama", id = "lama", version = "1.0.0")
public final class LamaLanguage extends TruffleLanguage<LamaContext> {
    public static LamaLanguage get() {
        return TruffleLanguage.getCurrentLanguage(LamaLanguage.class);
    }

    public static LamaContext getCurrentContext() {
        return TruffleLanguage.getCurrentContext(LamaLanguage.class);
    }

    @Override
    protected LamaContext createContext(TruffleLanguage.Env env) {
        LamaContext context = new LamaContext(env);
        LamaContext.setCurrentContext(context);
        return context;
    }

    @Override
    protected void initializeContext(LamaContext context) {
    }

    protected boolean isObjectOfLanguage(Object object) {
        return false;
    }

    @Override
    protected CallTarget parse(ParsingRequest request) throws Exception {
        CharStream stream = CharStreams.fromReader(request.getSource().getReader());
        LamaParser parser = new LamaParser(new CommonTokenStream(new LamaLexer(stream)));
        LamaVisitorImpl visitor = new LamaVisitorImpl();
        ExpressionNode node = parser.compilationUnit().accept(visitor);

        LamaRootNode rootNode = new LamaRootNode(node, visitor.getRootFrameDescriptor());
        return rootNode.getCallTarget();
    }
}
