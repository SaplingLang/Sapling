package dev.npex42.sapling.parser.ast.values;

import dev.npex42.sapling.SyntaxNode;

public class ValueNode extends Expression {
    protected final Object value;

    public ValueNode(Object value) {
        this.value = value;
    }

    public Object value() {
        return value;
    }

    public int intValue() {
        return (Integer) value;
    }

    public String stringValue() {
        return (String) value;
    }


    @Override
    public String toString() {
        return "(" + value + ")";
    }
}
