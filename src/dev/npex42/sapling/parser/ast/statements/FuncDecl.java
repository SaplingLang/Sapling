package dev.npex42.sapling.parser.ast.statements;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.parser.ast.values.IdentNode;

public class FuncDecl extends SyntaxNode {
    public final IdentNode name;
    public final Arguments args;
    public final Block body;

    public FuncDecl(IdentNode name, Arguments args, Block body) {
        this.name = name;
        this.args = args;
        this.body = body;
    }

    public int arity() {
        return args.args.size();
    }

    @Override
    public String toString() {
        return "FuncDecl{" +
                "name=" + name +
                ", args=" + args +
                ", body=" + body +
                '}';
    }
}
