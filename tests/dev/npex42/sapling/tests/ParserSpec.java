package dev.npex42.sapling.tests;

import dev.npex42.sapling.Lexer;
import dev.npex42.sapling.Parser;
import dev.npex42.sapling.ast.SyntaxNode;
import org.junit.Test;

public class ParserSpec {
    @Test
    public void Expression() {
        String input = "10 + 20";
        Parser p = new Parser(new Lexer(input).lex());
        SyntaxNode node = p.parse();
        node.print(0);
    }
}
