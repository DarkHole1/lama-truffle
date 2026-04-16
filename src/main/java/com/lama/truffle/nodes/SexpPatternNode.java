package com.lama.truffle.nodes;

import com.lama.truffle.types.Sexp;
import com.oracle.truffle.api.frame.VirtualFrame;

public class SexpPatternNode extends PatternNode {
    private final String tagName;
    @Children private PatternNode[] childPatterns;

    public SexpPatternNode(String tagName, PatternNode[] childPatterns) {
        this.tagName = tagName;
        this.childPatterns = childPatterns;
    }

    @Override
    public boolean match(Object value, VirtualFrame frame) {
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
            if (!childPatterns[i].match(sexpValues[i], frame)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        if (childPatterns != null) {
            for (PatternNode pattern : childPatterns) {
                pattern.execute(frame);
            }
        }
        return null;
    }

    public String getTagName() {
        return tagName;
    }

    public PatternNode[] getChildPatterns() {
        return childPatterns;
    }
}
