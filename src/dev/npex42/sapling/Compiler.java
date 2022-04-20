package dev.npex42.sapling;

import dev.npex42.sapling.ast.Expression;
import dev.npex42.sapling.ast.SyntaxNode;

import java.util.*;

public class Compiler {
    private static Lexer lexer;
    private static Parser parser;

    public static SyntaxNode compile(String src) {
        lexer = new Lexer(src);
        List<Token> tokens = lexer.lex();
        if (lexer.hadError()) {
            System.err.println("Lexing Error...");
            return null;
        } else {
            parser = new Parser(tokens);
            try {
                return parser.parse();
            } catch (Exception ex) {
                System.err.println("Parsing Error: " + ex.getLocalizedMessage());
                return null;
            }
        }
    }
}
