package dev.npex42.sapling;

import static dev.npex42.sapling.Token.*;
import static dev.npex42.sapling.Token.TokenType.*;

import java.util.*;

public class Lexer {
    private static final Map<String, TokenType> keywords = new HashMap<>();

    static {
        keywords.put("let", LET);
        keywords.put("print", PRINT);
        keywords.put("fn", FN);

        keywords.put("public", PUBLIC);
        keywords.put("private", PRIVATE);
    }

    private final char[] stream;
    private final List<Token> tokens = new ArrayList<>();
    private int current, line = 1, col = 1;
    private boolean had_error = false;

    public Lexer(String text) {
        this.current = 0;
        this.stream = text.toCharArray();
    }

    public List<Token> lex() {
        while (has_next()) {
            primary();
        }
        return tokens;
    }

    private void primary() {
        char c = peek();
        switch (c) {
            // Arthimetic
            case '(':
                emitToken(LPAREN);
                break;
            case ')':
                emitToken(RPAREN);
                break;
            case '+':
                emitToken(PLUS);
                break;
            case '-':
                emitToken(MINUS);
                break;
            case '/':
                if (lookAhead(1) == '/') {
                    while (peek() != '\n' && has_next()) {
                        pop();
                    }
                } else {
                    emitToken(SLASH);
                }
                break;
            case '*':
                emitToken(STAR);
                break;
            case '\n':
                endOfLine();
                break;
            case '{':
                emitToken(LCURLY);
                break;
            case '}':
                emitToken(RCURLY);
                break;
            case ';':
                emitToken(SEMICOLON);
                break;
            case '"':
                string();
                return;
            default:
                if (isDigit(c)) {
                    integer();
                    return;
                } else if (isLetter(c) | c == '_') {
                    identifier();
                    return;
                }
                break;
        }

        pop();
    }

    private void identifier() {
        String output = "";
        while (isLetterOrDigit(peek()) | peek() == '_' | peek() == ':') {
            output += pop();
        }
        if (keywords.containsKey(output)) {
            emitToken(keywords.get(output));
        } else {
            emitToken(IDENTIFIER, output);
        }
    }


    // INTEGER := "[.\n]*"
    private void string() {
        String result = "";
        pop();
        while (peek() != '"') {
            result += pop();

            if (!has_next()) {
                error("Unterminated String");
                return;
            }
        }

        if (!take('"')) {
            error("Unterminated String");
            return;
        }

        //System.out.println("String Result: '"+result+"'");
        emitToken(STRING, result);
    }

    // INTEGER := [DIGIT]+
    private void integer() {
        String result = "";
        while (isDigit(peek())) {
            result += pop();
        }
        emitToken(INTEGER, Integer.parseInt(result));
    }

    private void endOfLine() {
        col = 1;
        line++;
    }


    private void emitToken(TokenType type) {
        emitToken(type, null);
    }

    private void emitToken(TokenType type, Object value) {
        //System.out.println("Emitting Token: "+type + " = " + value);
        tokens.add(new Token(type, value, line, col));
    }

    private boolean isLetterOrDigit(char c) {
        return isLetter(c) | isDigit(c);
    }

    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private boolean isLetter(char c) {
        return ('A' <= c && c <= 'Z') | ('a' <= c && c <= 'z');
    }

    private boolean take(char target) {
        if (peek() == target) {
            pop();
            return true;
        }

        return false;
    }

    private char lookBehind(int amount) {
        if (current - amount >= 0) {
            return stream[current - amount];
        }

        return '\0';
    }

    private char lookAhead(int amount) {
        if (current + amount < stream.length) {
            return stream[current + amount];
        }

        return '\0';
    }

    private char pop() {
        if (!has_next()) return '\0';
        col++;
        return stream[current++];
    }

    private char peek() {
        if (!has_next()) return '\0';

        return stream[current];
    }


    public boolean has_next() {
        return this.current < this.stream.length;
    }

    private void error(String msg) {
        System.err.printf("Error (%d:%d): %s%n", line, col, msg);
        had_error = true;
    }

    public boolean hadError() {
        return had_error;
    }


}
