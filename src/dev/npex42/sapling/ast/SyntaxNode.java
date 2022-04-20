package dev.npex42.sapling.ast;

import java.util.*;

public interface SyntaxNode {
    String codegen();

    void print(int depth);
}
