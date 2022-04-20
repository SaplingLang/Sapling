package dev.npex42.sapling.ast;

public class Expression implements SyntaxNode {
    private final SyntaxNode expr;

    public Expression(SyntaxNode expr) {
        this.expr = expr;
    }


    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "Expression: ");
        expr.print(depth + 1);
    }
}
