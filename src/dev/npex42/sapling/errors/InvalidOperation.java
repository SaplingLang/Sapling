package dev.npex42.sapling.errors;

public class InvalidOperation extends RuntimeException {
    public InvalidOperation(String message) {
        super(message);
    }

    public InvalidOperation() {
        this("Invalid Operation");
    }
}
