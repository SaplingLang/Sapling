package dev.npex42.sapling.ast;

public class Lookup implements SyntaxNode {
    private String name;

    public Lookup(String name) {
        this.name = name;
    }

    public Lookup() {
        super();
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + "Lookup: " + name);
    }
}
