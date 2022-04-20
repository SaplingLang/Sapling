package dev.npex42.sapling.ast;

import dev.npex42.sapling.ast.types.Identifier;

public class Assignment implements SyntaxNode {
    private final Identifier identifier;
    private final Expression initializer;

    public Assignment(Identifier identifier, Expression initializer) {
        this.identifier = identifier;
        this.initializer = initializer;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "Assignment: ");
        identifier.print(depth + 1);
        if (initializer != null)
            initializer.print(depth + 1);
    }
}
