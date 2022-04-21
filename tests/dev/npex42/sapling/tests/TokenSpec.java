package dev.npex42.sapling.tests;

import dev.npex42.sapling.InvalidType;
import dev.npex42.sapling.tokens.Token;
import dev.npex42.sapling.tokens.TokenType;
import org.junit.Assert;
import org.junit.Test;

public class TokenSpec {
    @Test
    public void string_to_int_should_throw_InvalidType() {
        Token bad_string = new Token(TokenType.IDENTIFIER, "BAD_STRING", 0, 0);
        try {
            bad_string.integer();
            Assert.fail("Failed To Throw InvalidType.");
        } catch (InvalidType invalidType) {
        }
    }

    @Test
    public void int_to_string_should_throw_InvalidType() {
        Token bad_int = new Token(TokenType.INTEGER, 1, 0, 0);
        try {
            bad_int.string();
            Assert.fail("Failed To Throw InvalidType.");
        } catch (InvalidType invalidType) {
        }
    }

    @Test
    public void null_to_string_should_throw_InvalidType() {
        Token null_value = new Token(TokenType.INTEGER, null, 0, 0);
        try {
            null_value.string();
            Assert.fail("Failed To Throw InvalidType.");
        } catch (InvalidType invalidType) {
        }
    }

    @Test
    public void null_to_int_should_throw_InvalidType() {
        Token null_value = new Token(TokenType.INTEGER, null, 0, 0);
        try {
            null_value.integer();
            Assert.fail("Failed To Throw InvalidType.");
        } catch (InvalidType invalidType) {
        }
    }
}
