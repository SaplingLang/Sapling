package dev.npex42.sapling.parser.ast.statements;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.parser.ast.values.Expression;
import dev.npex42.sapling.parser.ast.values.IdentNode;

public class Variable extends SyntaxNode {
    public final IdentNode name;
    public final Expression init;

    public Variable(IdentNode name, Expression init) {
        this.name = name;
        this.init = init;
    }

    @Override
    public String toString() {
        return name + "=" + init;
    }
}
