package dev.npex42.sapling.parser.ast.values;

import dev.npex42.sapling.SyntaxNode;

public abstract class Expression<T> extends SyntaxNode {
    public abstract T evaluate();
}
