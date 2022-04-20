package dev.npex42.sapling.ast;

import dev.npex42.sapling.ast.types.Identifier;

import java.util.List;

public class ArgList implements SyntaxNode {

    private final List<Identifier> args;

    public ArgList(List<Identifier> args) {
        this.args = args;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "Arg List: ");
        for (Identifier ident : args) {
            ident.print(depth + 1);
        }
    }
}
