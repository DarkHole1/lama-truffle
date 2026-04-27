package com.lama.truffle.nodes;

import com.lama.truffle.types.Sexp;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class SexpPatternNode extends PatternNode {
    private final String tagName;
    @Children private final PatternNode[] childPatterns;

    public SexpPatternNode(String tagName, PatternNode[] childPatterns, int scrutineeSlot) {
        super(scrutineeSlot);
        this.tagName = tagName;
        this.childPatterns = childPatterns;
    }

    @Override @ExplodeLoop
    public boolean executeBoolean(VirtualFrame frame) {
        Object value = frame.getObject(getScrutineeSlot());

        if (!(value instanceof Sexp)) {
            return false;
        }

        Sexp sexp = (Sexp) value;

        if (!tagName.equals(sexp.getName())) {
            return false;
        }

        Object[] sexpValues = sexp.getValues();

        if (childPatterns == null || childPatterns.length == 0) {
            return sexpValues == null || sexpValues.length == 0;
        }

        if (sexpValues == null || sexpValues.length != childPatterns.length) {
            return false;
        }

        for (int i = 0; i < childPatterns.length; i++) {
            frame.setObject(getScrutineeSlot(), sexpValues[i]);
            if (!childPatterns[i].executeBoolean(frame)) {
                return false;
            }
        }

        return true;
    }

    public String getTagName() {
        return tagName;
    }

    public PatternNode[] getChildPatterns() {
        return childPatterns;
    }
}
