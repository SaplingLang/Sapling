package dev.npex42.sapling.ast;

import java.util.*;

public class Block implements SyntaxNode {
    private final List<SyntaxNode> statements;

    public Block(List<SyntaxNode> statements) {
        this.statements = statements;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "Block: ");
        if (statements.size() > 0) {
            for (SyntaxNode statement : statements) {
                statement.print(depth + 1);
            }
        } else {
            System.out.println("\t".repeat(depth + 1) + "<EMPTY>");
        }

    }
}
