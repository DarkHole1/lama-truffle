package com.lama.truffle.nodes;

public abstract class LiteralNode extends ExpressionNode {
    
    protected final Object value;
    
    public LiteralNode(Object value) {
        this.value = value;
    }
    
    public Object getValue() {
        return value;
    }
}