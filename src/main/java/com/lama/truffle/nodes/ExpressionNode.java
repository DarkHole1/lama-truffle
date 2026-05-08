package com.lama.truffle.nodes;

import com.lama.truffle.types.Array;
import com.lama.truffle.types.Closure;
import com.lama.truffle.types.LamaTypes;
import com.lama.truffle.types.LamaTypesGen;
import com.lama.truffle.types.List;
import com.lama.truffle.types.Sexp;
import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.source.SourceSection;

@TypeSystemReference(LamaTypes.class)
public abstract class ExpressionNode extends Node {
    private SourceSection section;

    public abstract Object execute(VirtualFrame frame);

    public long executeLong(VirtualFrame frame) throws UnexpectedResultException {
        return LamaTypesGen.expectLong(execute(frame));
    }

    public Array executeArray(VirtualFrame frame) throws UnexpectedResultException {
        return LamaTypesGen.expectArray(execute(frame));
    }

    public Closure executeClosure(VirtualFrame frame) throws UnexpectedResultException {
        return LamaTypesGen.expectClosure(execute(frame));
    }

    public List executeList(VirtualFrame frame) throws UnexpectedResultException {
        return LamaTypesGen.expectList(execute(frame));
    }

    public Sexp executeSexp(VirtualFrame frame) throws UnexpectedResultException {
        return LamaTypesGen.expectSexp(execute(frame));
    }

    public void setSourceSection(SourceSection section) {
        this.section = section;
    }

    @Override
    public SourceSection getSourceSection() {
        return section;
    }
}