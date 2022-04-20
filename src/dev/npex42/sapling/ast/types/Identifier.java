package dev.npex42.sapling.ast.types;

import dev.npex42.sapling.ast.SyntaxNode;

public class Identifier implements SyntaxNode {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "IDENTIFIER: " + name);
    }
}
