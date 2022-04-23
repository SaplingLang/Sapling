package dev.npex42.sapling.parser.ast.operators;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.errors.InvalidOperation;
import dev.npex42.sapling.parser.ast.values.Expression;
import dev.npex42.sapling.tokens.TokenType;

public class UnaryOp extends Expression {
    public final TokenType operator;
    public final Expression expr;

    public UnaryOp(TokenType operator, Expression expr) {
        this.operator = operator;
        this.expr = expr;
    }


    @Override
    public String toString() {
        return "-" + expr.toString();
    }
}
