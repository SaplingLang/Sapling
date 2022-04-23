package dev.npex42.sapling.parser.ast.values;

public class IdentNode extends ValueNode {

    public IdentNode(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "Identifier(" + value + ")";
    }
}
