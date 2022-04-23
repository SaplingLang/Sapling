package dev.npex42.sapling;

public abstract class SyntaxNode {
    protected int line, column;

    @Override
    public String toString() {
        return "SyntaxNode {" +
                " line=" + line +
                ", column=" + column +
                " }";
    }

}
