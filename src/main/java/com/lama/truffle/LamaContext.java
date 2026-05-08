package com.lama.truffle;

import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.nodes.Node;

public final class LamaContext {

    private static final ContextReference<LamaContext> REFERENCE = ContextReference.create(LamaLanguage.class);

    public static LamaContext get(Node node) {
        return REFERENCE.get(node);
    }

    public LamaContext() {
    }
}
