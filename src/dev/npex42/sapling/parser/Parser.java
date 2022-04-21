package dev.npex42.sapling.parser;

import dev.npex42.sapling.tokens.Token;
import dev.npex42.sapling.TokenScanner;
import dev.npex42.sapling.parser.ast.values.IntNode;
import dev.npex42.sapling.parser.ast.values.StringNode;
import dev.npex42.sapling.parser.ast.values.ValueNode;
import dev.npex42.sapling.errors.ParseError;

import static dev.npex42.sapling.tokens.TokenType.*;

import java.util.List;

public class Parser {
    private final TokenScanner scanner;

    public Parser(List<Token> tokens) {
        scanner = new TokenScanner(tokens);
    }

    public ValueNode<?> value() {
        if (scanner.match(INTEGER, IDENTIFIER, STRING)) {
            switch (scanner.peek().type()) {
                case INTEGER -> {
                    return integer();
                }
                case STRING -> {
                    return string();
                }
                case IDENTIFIER -> {
                    return identifier();
                }
                default -> {
                    throw new ParseError("Expected Integer, String or Identifier");
                }
            }
        } else {
            throw new ParseError("Expected Value");
        }
    }

    public IntNode integer() {
        if (scanner.match(INTEGER)) {
            Token number = scanner.pop();
            return new IntNode(number.integer());
        } else {
            throw new ParseError("Expected Integer");
        }
    }

    public StringNode string() {
        if (scanner.match(STRING)) {
            Token number = scanner.pop();
            return new StringNode(number.string());
        } else {
            throw new ParseError("Expected String");
        }
    }

    public StringNode identifier() {
        if (scanner.match(IDENTIFIER)) {
            Token number = scanner.pop();
            return new StringNode(number.string());
        } else {
            throw new ParseError("Expected Identifier");
        }
    }
}