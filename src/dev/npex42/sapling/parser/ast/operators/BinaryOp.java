package dev.npex42.sapling.parser.ast.operators;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.parser.ast.values.Expression;
import dev.npex42.sapling.tokens.TokenType;

public class BinaryOp extends Expression<Number> {
    public final Expression<Number> lhs, rhs;
    public final TokenType op;

    public BinaryOp(Expression lhs, TokenType op, Expression<Number> rhs) {
        this.lhs = lhs;
        this.op = op;
        this.rhs = rhs;
    }


    @Override
    public Number evaluate() {
        Number r = rhs.evaluate();
        Number l = lhs.evaluate();

        return (r.intValue() + l.intValue());
    }
}
