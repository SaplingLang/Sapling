package dev.npex42.sapling.parser;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.parser.ast.operators.BinaryOp;
import dev.npex42.sapling.parser.ast.operators.UnaryOp;
import dev.npex42.sapling.parser.ast.values.Expression;
import dev.npex42.sapling.tokens.Token;
import dev.npex42.sapling.tokens.TokenScanner;
import dev.npex42.sapling.parser.ast.values.IntNode;
import dev.npex42.sapling.parser.ast.values.StringNode;
import dev.npex42.sapling.parser.ast.values.ValueNode;
import dev.npex42.sapling.errors.ParseError;
import dev.npex42.sapling.tokens.TokenType;

import static dev.npex42.sapling.tokens.TokenType.*;

import java.util.List;

public class Parser {
    private final TokenScanner scanner;

    public Parser(List<Token> tokens) {
        scanner = new TokenScanner(tokens);
    }

    public Parser(TokenScanner scanner) {
        this.scanner = scanner;
    }

    public SyntaxNode parse() {
        if (scanner.isEmpty()) {
            return null;
        }

        return unary();
    }

    public Expression unary() {
        if (scanner.match(MINUS)) {
            TokenType operator = scanner.popType();
            Expression expr = value();
            return new UnaryOp(operator, expr);
        } else {
            return value();
        }

    }

    public Expression addition() {
        Expression expr = unary();
        while (scanner.match(PLUS, MINUS)) {
            TokenType operator = scanner.popType();

            Expression rhs = unary();

            expr = new BinaryOp(expr, operator, rhs);
        }

        return expr;
    }

    public Expression value() {
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