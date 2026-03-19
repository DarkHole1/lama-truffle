package com.lama.truffle.nodes;

import com.lama.truffle.types.Sexp;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * S-expression pattern that matches Sexp values with a specific tag.
 * Recursively applies child patterns to the Sexp's values.
 */
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

        // Check tag name matches
        if (!tagName.equals(sexp.getName())) {
            return false;
        }

        Object[] sexpValues = sexp.getValues();

        // If no child patterns, match empty sexp
        if (childPatterns == null || childPatterns.length == 0) {
            return sexpValues == null || sexpValues.length == 0;
        }

        // Check number of values matches number of patterns
        if (sexpValues == null || sexpValues.length != childPatterns.length) {
            return false;
        }

        // Recursively match each child pattern against corresponding value
        for (int i = 0; i < childPatterns.length; i++) {
            if (!childPatterns[i].match(sexpValues[i], frame)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // Execute child patterns for any side effects
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
