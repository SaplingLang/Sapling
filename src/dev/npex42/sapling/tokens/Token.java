package dev.npex42.sapling.tokens;


import dev.npex42.sapling.InvalidType;

public class Token {

    private final TokenType type;
    private final Object value;

    private final int line;
    private final int column;

    public Token(TokenType type) {
        this(type, null, 0, 0);
    }

    public Token(TokenType type, Object value) {
        this(type, value, 0, 0);
    }

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

    public int integer() throws InvalidType {
        if (this.value instanceof Integer) {
            return (Integer) this.value;
        } else {
            throw new InvalidType("Cannot cast value to an int.");
        }
    }

    public String string() throws InvalidType {
        if (this.value instanceof String) {
            return (String) this.value;
        } else {
            throw new InvalidType("Cannot cast value to an String.");
        }
    }

    public TokenType type() {
        return type;
    }

    public boolean equals(Object other) {
        if (other instanceof Token) {
            Token other_token = (Token) other;
            return (other_token.value.equals(value)) && (other_token.type.equals(type));
        } else if (other instanceof TokenType) {
            TokenType other_type = (TokenType) other;
            return other_type == type;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return type + "(" + value + "," + line + ":" + column + ")";
    }


}
