package com.lama.truffle;

import com.oracle.truffle.api.TruffleLanguage;

public final class LamaContext {

    private static final ThreadLocal<LamaContext> currentContext = new ThreadLocal<>();

    private final TruffleLanguage.Env env;

    public LamaContext(TruffleLanguage.Env env) {
        this.env = env;
    }

    public static LamaContext getCurrentContext() {
        return currentContext.get();
    }

    public static void setCurrentContext(LamaContext context) {
        currentContext.set(context);
    }

    public TruffleLanguage.Env getEnv() {
        return env;
    }
}
