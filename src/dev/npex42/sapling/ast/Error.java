package dev.npex42.sapling.ast;

public class Error implements SyntaxNode {
    private final String msg;

    public Error(String msg) {
        this.msg = msg;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "ERROR: " + msg);
    }
}
