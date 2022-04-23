package dev.npex42.sapling.parser.ast.operators;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.errors.InvalidOperation;
import dev.npex42.sapling.parser.ast.values.Expression;
import dev.npex42.sapling.tokens.TokenType;

public class BinaryOp extends Expression {
    public final Expression lhs, rhs;
    public final TokenType op;

    public BinaryOp(Expression lhs, TokenType op, Expression rhs) {
        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;
    }


    @Override
    public String toString() {
        return "(" + lhs + " " + op + " " + rhs + ")";
    }
}
