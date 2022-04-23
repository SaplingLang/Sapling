package dev.npex42.sapling.parser;

import dev.npex42.sapling.SyntaxNode;
import dev.npex42.sapling.parser.ast.operators.BinaryOp;
import dev.npex42.sapling.parser.ast.operators.UnaryOp;
import dev.npex42.sapling.parser.ast.statements.Block;
import dev.npex42.sapling.parser.ast.statements.Print;
import dev.npex42.sapling.parser.ast.statements.Variable;
import dev.npex42.sapling.parser.ast.values.*;
import dev.npex42.sapling.tokens.Token;
import dev.npex42.sapling.tokens.TokenScanner;
import dev.npex42.sapling.errors.ParseError;
import dev.npex42.sapling.tokens.TokenType;

import static dev.npex42.sapling.tokens.TokenType.*;

import java.util.ArrayList;
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
        List<SyntaxNode> statements = new ArrayList<>();
        while (scanner.hasNextToken()) {
            statements.add(Statement());
        }

        return new Block(statements);
    }

    public SyntaxNode Statement() {
        if (scanner.match(PRINT)) {
            return print();
        } else if (scanner.match(LET)) {
            return assignment();
        } else {
            throw new ParseError("Expected Statement, Got:" + scanner.peekType());
        }
    }

    public SyntaxNode print() {
        if (scanner.take(PRINT)) {
            return new Print(expr());
        }

        throw new ParseError("Expected Keyword Print, Got " + scanner.peekType());
    }

    public Variable assignment() {
        if (!scanner.take(LET)) {
            throw new ParseError("Expected keyword Let, got: " + scanner.peekType());
        }

        IdentNode name = identifier();
        Expression init = null;
        if (scanner.take(EQUAL)) {
            init = expr();
        }

        expect(SEMICOLON);

        return new Variable(name, init);
    }

    public Expression expr() {
        return sum();
    }


    public Expression sum() {
        Expression expr = product();
        while (scanner.match(PLUS, MINUS)) {
            TokenType operator = scanner.popType();

            Expression rhs = product();

            expr = new BinaryOp(expr, operator, rhs);
        }

        return expr;
    }

    public Expression product() {
        Expression expr = unary();
        while (scanner.match(SLASH, STAR)) {
            TokenType operator = scanner.popType();

            Expression rhs = unary();

            expr = new BinaryOp(expr, operator, rhs);
        }

        return expr;
    }

    public Expression unary() {
        if (scanner.match(MINUS)) {
            TokenType operator = scanner.popType();
            Expression expr;
            expr = unary();
            return new UnaryOp(operator, expr);
        } else {
            return value();
        }

    }

    public Expression group() {
        if (scanner.take(LPAREN)) {
            Expression expr = expr();
            if (!scanner.take(RPAREN)) {
                throw new ParseError("Missing Closing ')'");
            }
            return expr;
        } else {
            return (value());
        }
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
            return group();
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

    public IdentNode identifier() {
        if (scanner.match(IDENTIFIER)) {
            Token number = scanner.pop();
            return new IdentNode(number.string());
        } else {
            throw new ParseError("Expected Identifier");
        }
    }


    public void expect(TokenType expected) {

        if (!scanner.hasNextToken()) {
            throw new UnexpectedToken(expected, EOF);
        }

        if (!scanner.take(expected)) {
            throw new UnexpectedToken(expected, scanner.peekType());
        }
    }
}