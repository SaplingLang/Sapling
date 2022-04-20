package dev.npex42.sapling.ast;

import dev.npex42.sapling.AccessModifier;
import dev.npex42.sapling.ast.types.Identifier;

public class Function implements SyntaxNode {

    private final AccessModifier modifier;
    private final Identifier name;
    private final ArgList args;
    private final Block block;

    public Function(Identifier name, ArgList args, Block block) {
        this.name = name;
        this.args = args;
        this.block = block;
        this.modifier = AccessModifier.PRIVATE;
    }

    public Function(Identifier name, ArgList args, Block block, AccessModifier modifier) {
        this.name = name;
        this.args = args;
        this.block = block;
        this.modifier = modifier;
    }

    @Override
    public String codegen() {
        return null;
    }

    @Override
    public void print(int depth) {
        System.out.println("\t".repeat(depth) + modifier + " FUNC: ");
        name.print(depth + 1);
        args.print(depth + 1);
        block.print(depth + 1);
    }
}
