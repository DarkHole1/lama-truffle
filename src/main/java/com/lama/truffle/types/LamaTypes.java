package com.lama.truffle.types;

import com.oracle.truffle.api.dsl.TypeSystem;

@TypeSystem({ long.class, Array.class, Closure.class, List.class, Sexp.class })
public abstract class LamaTypes {
}
