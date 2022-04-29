package dev.npex42.sapling.parser.ast.statements;

import dev.npex42.sapling.parser.ast.values.IdentNode;

import java.util.List;

public class Arguments {
    public final List<IdentNode> args;

    public Arguments(List<IdentNode> args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Arguments{" +
                "args=" + args +
                '}';
    }
}
