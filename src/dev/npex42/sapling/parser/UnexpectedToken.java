package dev.npex42.sapling.parser;

import dev.npex42.sapling.tokens.TokenType;

public class UnexpectedToken extends RuntimeException {
    public UnexpectedToken(TokenType expected, TokenType tokenType) {
        super("Unexpected Token. Expected " + expected + ", Got " + tokenType + ".");
    }
}
