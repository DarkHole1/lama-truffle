#!/bin/bash

    # -Djdk.graal.PrintGraph=Network \
    # -Dgraal.Dump=Truffle:1 \
    # -Dpolyglot.engine.TraceCompilationDetails=true \
    # -Dpolyglot.engine.AllowExperimentalOptions=true \
    # -Dpolyglot.engine.BackgroundCompilation=false \
    # -Dpolyglot.engine.CompilationFailureAction=Diagnose \
    # -Dpolyglot.engine.TraceCompilationAST=true \
    # -Dpolyglot.compiler.TraceInlining=true \
    # -Dpolyglot.compiler.TracePerformanceWarnings=all \
    # -Dpolyglot.engine.TraceTransferToInterpreter=true \
    # -Dpolyglot.engine.TraceAssumptions=true \
    # -Dpolyglot.engine.TraceSplitting=true \
    # -XX:+UnlockDiagnosticVMOptions \
    # -XX:+DebugNonSafepoints \
    # -XX:+TraceDeoptimization \
java \
    -Xss128M -Xms1g -Xmx8g \
    -Dpolyglot.engine.TraceCompilation=true \
    -cp "target/lama-truffle-1.0.0.jar:target/dependency/*" com.lama.truffle.App "$1" >graal.log 2>&1