package dev.npex42.sapling.interpreter;

public class UndefinedVariable extends RuntimeException {
    public UndefinedVariable(String name) {
        super("Undefined Variable: '" + name + "'");
    }
}
