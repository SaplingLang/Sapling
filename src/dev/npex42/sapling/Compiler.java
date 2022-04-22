package dev.npex42.sapling;


import dev.npex42.sapling.tokens.TokenScanner;

public class Compiler {
    public static TokenScanner tokenize(String src) {
        Lexer lexer = new Lexer(src);
        return new TokenScanner(lexer.lex());
    }
}
