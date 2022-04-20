package dev.npex42.sapling.ast;

import java.util.List;

public class Params implements SyntaxNode {
    private final List<Expression> params;

    public Params(List<Expression> params) {
        this.params = params;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "Params: ");
        for (Expression expression : params) {
            expression.print(depth + 1);
        }
    }
}
