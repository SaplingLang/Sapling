package dev.npex42.sapling;

import static dev.npex42.sapling.Token.*;
import static dev.npex42.sapling.Token.TokenType.*;

import dev.npex42.sapling.ast.*;
import dev.npex42.sapling.ast.types.Identifier;
import dev.npex42.sapling.ast.types.IntNode;
import dev.npex42.sapling.ast.types.StringNode;

import java.util.*;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public SyntaxNode parse() {
        return block();
    }

    public Block block() {
        List<SyntaxNode> statements = new ArrayList<>();

        take(LCURLY);
        while (!take(RCURLY) && !take(EOF)) {
            statements.add(statement());
        }

        return new Block(statements);
    }

    private SyntaxNode statement() {
        if (take(LET)) {
            return assignment();
        } else if (take(PRINT)) {
            return print();
        } else if (match(PUBLIC, PRIVATE, FN)) {
            return funcDecl();
        } else {
            return expr();
        }
    }

    private Function funcDecl() {
        AccessModifier modifier = accessModifier();
        take(FN);
        Identifier func_name = identifier();
        ArgList args = argList();
        Block block = block();

        return new Function(func_name, args, block, modifier);
    }

    private AccessModifier accessModifier() {
        switch (pop().type()) {
            case PUBLIC -> {
                return AccessModifier.PUBLIC;
            }
            case PRIVATE -> {
                return AccessModifier.PRIVATE;
            }
            default -> {
                return AccessModifier.PRIVATE;
            }
        }
    }

    private ArgList argList() {
        List<Identifier> args = new ArrayList<>();

        if (take(LPAREN)) {
            while (match(IDENTIFIER)) {
                args.add(identifier());
                take(COMMA);
            }
        }

        take(RPAREN);

        return new ArgList(args);
    }

    private Assignment assignment() {
        Identifier identifier = identifier();
        if (take(EQUAL)) {
            SyntaxNode init = expr();

            if (!take(SEMICOLON)) {
                error("Missing Semicolon.");
            }

            return new Assignment(identifier, (Expression) init);
        } else {

            if (!take(SEMICOLON)) {
                error("Missing Semicolon.");
            }

            return new Assignment(identifier, null);
        }


    }

    private Print print() {
        SyntaxNode output = expr();

        take(SEMICOLON);

        return new Print(output);
    }

    private Expression expr() {
        return new Expression(sum());
    }

    private SyntaxNode sum() {
        SyntaxNode expr = product();
        while (match(PLUS, MINUS)) {
            Token operator = pop();
            SyntaxNode rhs = product();
            expr = new BinaryNode(expr, operator.type(), rhs);
        }

        return expr;
    }

    private SyntaxNode product() {
        SyntaxNode expr = atom();
        while (match(SLASH, STAR)) {
            Token operator = pop();
            SyntaxNode rhs = atom();
            expr = new BinaryNode(expr, operator.type(), rhs);
        }

        return expr;
    }

    private SyntaxNode atom() {
        if (match(INTEGER)) {
            return integer();
        } else if (match(IDENTIFIER)) {
            if (lookAhead(1).type() == LPAREN) {
                return call();
            } else {
                return lookup();
            }
        } else if (match(STRING)) {
            return string();
        } else {
            error("Unexpected Token: " + peek().type());
            return null;
        }
    }

    private Call call() {
        Identifier name = identifier();
        Params params = params();

        return new Call(name, params);
    }

    private Params params() {
        List<Expression> exprs = new ArrayList<>();
        if (take(LPAREN)) {
            while (!match(RPAREN)) {
                exprs.add(expr());
                take(COMMA);
            }
        }

        take(RPAREN);

        return new Params(exprs);
    }

    private Identifier identifier() {
        if (match(IDENTIFIER))
            return new Identifier((String) pop().value());
        error("Expected An Identifier");
        return null;
    }

    private StringNode string() {
        if (match(STRING))
            return new StringNode((String) pop().value());

        error("Expected A String");
        return null;
    }

    private Lookup lookup() {
        if (match(IDENTIFIER))
            return new Lookup((String) pop().value());

        error("Expected An Identifier");
        return null;
    }

    private IntNode integer() {
        if (match(INTEGER))
            return new IntNode((Integer) pop().value());

        error("Expected An Integer");
        return null;
    }

    private boolean match(TokenType... expected) {
        for (TokenType target : expected) {
            if (peek().type() == target) return true;
        }

        return false;
    }

    private boolean take(TokenType target) {
        if (peek().type() == target) {
            pop();
            return true;
        } else {
            return false;
        }
    }

    private Token pop() {
        if (!has_next()) {
            return EOF();
        }
        return tokens.get(current++);
    }

    private Token lookAhead(int amount) {
        if (current + amount < tokens.size()) {
            return tokens.get(current + amount);
        }

        return EOF();
    }

    private Token peek() {
        if (!has_next()) {
            return EOF();
        }
        return tokens.get(current);
    }

    public boolean has_next() {
        return (this.current < this.tokens.size()) && (tokens.get(current).type() != EOF);
    }

    private void error(String msg) throws RuntimeException {
        throw new RuntimeException(msg);
    }
}
