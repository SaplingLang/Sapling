package dev.npex42.sapling.parser.ast.statements;

import dev.npex42.sapling.SyntaxNode;

import java.util.List;

public class Block extends SyntaxNode {
    public final List<SyntaxNode> statements;

    public Block(List<SyntaxNode> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "Block( " + statements.toString() + " )";
    }
}
