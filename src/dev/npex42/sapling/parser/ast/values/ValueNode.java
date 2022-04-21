package dev.npex42.sapling.parser.ast.values;

import dev.npex42.sapling.SyntaxNode;

public class ValueNode<T> extends SyntaxNode {
    protected final T value;

    public ValueNode(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

}
