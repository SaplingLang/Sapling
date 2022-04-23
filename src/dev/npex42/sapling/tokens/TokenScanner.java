package dev.npex42.sapling.tokens;

import dev.npex42.sapling.errors.InvalidOperation;

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

    public static TokenScanner from(Token... tokens) {
        return new TokenScanner(List.of(tokens));
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

    public TokenType popType() throws InvalidOperation {
        if (!hasNextToken()) {
            throw new InvalidOperation("Cannot Peek Into An Empty Scanner.");
        }

        return tokens.get(current++).type();
    }

    public TokenType peekType() throws InvalidOperation {
        if (!hasNextToken()) {
            throw new InvalidOperation("Cannot Peek Into An Empty Scanner.");
        }

        return tokens.get(current).type();
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

    public boolean isEmpty() {
        return tokens.size() == 0;
    }

    public boolean take(TokenType type) {
        if (peekType() == type) {
            pop();
            return true;
        } else {
            return false;
        }
    }
}
