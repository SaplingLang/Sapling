package dev.npex42.sapling.ast;

import dev.npex42.sapling.ast.types.Identifier;

public class Call implements SyntaxNode {
    private final Identifier name;
    private final Params params;

    public Call(Identifier name, Params params) {
        this.name = name;
        this.params = params;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "Call: ");
        name.print(depth + 1);
        params.print(depth + 1);
    }
}
