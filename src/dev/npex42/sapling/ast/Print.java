package dev.npex42.sapling.ast;

public class Print implements SyntaxNode {
    private final SyntaxNode output;

    public Print(SyntaxNode output) {
        this.output = output;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "Print: ");
        output.print(depth + 1);
    }
}
