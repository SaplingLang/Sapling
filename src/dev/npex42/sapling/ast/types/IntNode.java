package dev.npex42.sapling.ast.types;

import dev.npex42.sapling.ast.SyntaxNode;

import java.util.List;

public class IntNode implements SyntaxNode {
    private final int value;

    public IntNode(int value) {
        this.value = value;
    }

    @Override
    public String codegen() {
        return "pushi " + value;
    }

    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "INTEGER: " + value);
    }
}
