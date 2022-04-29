package dev.npex42.sapling.parser.ast.statements;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.parser.ast.values.Expression;
import dev.npex42.sapling.parser.ast.values.IdentNode;

import java.util.List;

public class Call extends SyntaxNode {
    public final IdentNode name;
    public final List<Expression> args;

    public Call(IdentNode name, List<Expression> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString() {
        return "Call{" +
                "name=" + name +
                ", args=" + args +
                '}';
    }
}
