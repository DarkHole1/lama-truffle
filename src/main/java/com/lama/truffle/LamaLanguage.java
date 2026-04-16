package com.lama.truffle;

import com.oracle.truffle.api.TruffleLanguage;

@TruffleLanguage.Registration(
    name = "Lama",
    id = "lama",
    version = "1.0.0"
)
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
}
