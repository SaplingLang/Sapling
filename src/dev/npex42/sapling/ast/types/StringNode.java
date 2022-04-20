package dev.npex42.sapling.ast.types;

import dev.npex42.sapling.ast.SyntaxNode;

public class StringNode implements SyntaxNode {
    private final String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public String codegen() {
        return null;
    }

    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "STRING: " + value);
    }
}
