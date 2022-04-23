package dev.npex42.sapling.parser.ast.statements;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.parser.ast.values.Expression;

public class Print extends SyntaxNode {
    public final Expression expr;

    public Print(Expression expr) {
        this.expr = expr;
    }


    @Override
    public String toString() {
        return "PRINT " + expr.toString();
    }
}
