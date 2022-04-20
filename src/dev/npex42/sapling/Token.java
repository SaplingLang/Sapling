package dev.npex42.sapling;


public class Token {

    private final TokenType type;
    private final Object value;

    private final int line;
    private final int column;

    public Token(TokenType type, Object value, int line, int column) {
        this.type = type;
        this.value = value;

        this.column = column;
        this.line = line;
    }

    public static Token EOF() {
        return new Token(TokenType.EOF, null, -1, -1);
    }

    public Object value() {
        return value;
    }

    public TokenType type() {
        return type;
    }

    @Override
    public String toString() {
        return type + "(" + value + "," + line + ":" + column + ")";
    }

    public enum TokenType {
        INTEGER,
        IDENTIFIER,
        STRING,

        LET,
        PRINT,
        FN,
        PUBLIC,
        PRIVATE,

        PLUS,
        MINUS,
        SLASH,
        STAR,
        SEMICOLON,

        LPAREN,
        RPAREN,
        LCURLY,
        RCURLY,
        COMMA,

        EQUAL,
        EOF
    }
}
