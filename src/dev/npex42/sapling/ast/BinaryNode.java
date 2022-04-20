package dev.npex42.sapling.ast;

import dev.npex42.sapling.Token.*;

public class BinaryNode implements SyntaxNode {
    private final SyntaxNode lhs;
    private final SyntaxNode rhs;
    private final TokenType op;

    public BinaryNode(SyntaxNode lhs, TokenType op, SyntaxNode rhs) {
        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;
    }

    @Override
    public String codegen() {

        String op = switch (this.op) {
            case PLUS -> "add";
            case MINUS -> "sub";
            case STAR -> "mul";
            case SLASH -> "div";
            default -> throw new IllegalStateException("Unexpected value: " + this.op);
        };

        return lhs.codegen() + "\n" + rhs.codegen() + "\n" + op;
    }

    public void print(int depth) {
        System.out.println("\t".repeat(depth) + op + ":");
        rhs.print(depth + 1);
        lhs.print(depth + 1);
    }
}
