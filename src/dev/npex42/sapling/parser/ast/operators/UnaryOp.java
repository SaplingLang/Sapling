package dev.npex42.sapling.parser.ast.operators;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.errors.InvalidOperation;
import dev.npex42.sapling.parser.ast.values.Expression;
import dev.npex42.sapling.tokens.TokenType;

public class UnaryOp extends Expression<Number> {
    public final TokenType operator;
    public final Expression<Number> expr;

    public UnaryOp(TokenType operator, Expression<Number> expr) {
        this.operator = operator;
        this.expr = expr;
    }

    @Override
    public Integer evaluate() {
        Number expr_eval = expr.evaluate();
        return (int) -(expr_eval.longValue());
    }
}
