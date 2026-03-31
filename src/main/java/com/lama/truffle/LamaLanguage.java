package com.lama.truffle;

import com.oracle.truffle.api.TruffleLanguage;

/**
 * The main language class for Lama.
 * Provides the Truffle language infrastructure and context management.
 */
@TruffleLanguage.Registration(
    name = "Lama",
    id = "lama",
    version = "1.0.0"
)
public final class LamaLanguage extends TruffleLanguage<LamaContext> {

    /**
     * Gets the current language instance from the current context.
     */
    public static LamaLanguage get() {
        return TruffleLanguage.getCurrentLanguage(LamaLanguage.class);
    }

    /**
     * Gets the current language context.
     */
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
        context.initializeBuiltins();
    }

    protected boolean isObjectOfLanguage(Object object) {
        // Check if the object is a Lama-specific type
        // For now, we don't have any special types that need to be identified
        return false;
    }
}
