package dev.npex42.sapling;

import dev.npex42.sapling.errors.InvalidOperation;
import dev.npex42.sapling.tokens.Token;
import dev.npex42.sapling.tokens.TokenType;

import java.util.*;

public class TokenScanner {
    private final List<Token> tokens;
    private int current;

    public TokenScanner(List<Token> tokens) {
        this.tokens = tokens;
    }

    public static TokenScanner emptyScanner() {
        return new TokenScanner(Collections.EMPTY_LIST);
    }

    public boolean match(TokenType... types) {
        try {
            for (TokenType target : types) {
                if (target == peek().type()) {
                    return true;
                }
            }
            return false;
        } catch (InvalidOperation invalidOperation) {
            return false;
        }
    }

    public Token pop() throws InvalidOperation {
        if (!hasNextToken()) {
            throw new InvalidOperation("Cannot Pop From An Empty Scanner.");
        }

        return tokens.get(current++);
    }

    public Token peek() throws InvalidOperation {
        if (!hasNextToken()) {
            throw new InvalidOperation("Cannot Peek Into An Empty Scanner.");
        }

        return tokens.get(current);
    }

    public boolean hasNextToken() {
        return current < tokens.size();
    }
}
