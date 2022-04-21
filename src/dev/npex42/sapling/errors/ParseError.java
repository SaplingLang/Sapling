package dev.npex42.sapling.errors;

public class ParseError extends RuntimeException {
    public ParseError() {
        this("Parsing Error Occurred");
    }

    public ParseError(String message) {
        super(message);
    }
}
