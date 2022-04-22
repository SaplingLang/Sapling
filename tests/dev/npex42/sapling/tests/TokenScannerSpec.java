package dev.npex42.sapling.tests;

import dev.npex42.sapling.errors.InvalidOperation;
import dev.npex42.sapling.tokens.Token;

import static dev.npex42.sapling.tokens.TokenType.*;

import dev.npex42.sapling.tokens.TokenScanner;
import dev.npex42.sapling.tokens.TokenType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TokenScannerSpec {

    private Token createToken(TokenType type, Object value) {
        return new Token(type, value, 0, 0);
    }

    private Token createToken(TokenType type) {
        return new Token(type, null, 0, 0);
    }

    @Test
    public void empty_scanner_shouldnt_have_next() {
        Assert.assertFalse(TokenScanner.emptyScanner().hasNextToken());
    }

    @Test
    public void empty_scanner_should_fail_to_peek() {
        try {
            TokenScanner.emptyScanner().peek();
            fail("TokenScanner.peek() Failed To Throw Exception");
        } catch (InvalidOperation invalidOperation) {
        }
    }

    @Test
    public void empty_scanner_should_return_false_on_match() {
        assertFalse(TokenScanner.emptyScanner().match(INTEGER));
    }

    @Test
    public void non_empty_scanner_should_return_true_on_match_if_types_match() {
        TokenScanner scanner = TokenScanner.from(createToken(INTEGER));
        assertTrue(scanner.match(INTEGER));
    }

    @Test
    public void non_empty_scanner_should_return_false_on_match_if_types_dont_match() {
        TokenScanner scanner = TokenScanner.from(createToken(IDENTIFIER));
        assertFalse(scanner.match(INTEGER));
    }

    @Test
    public void non_empty_scanner_should_return_current_token_on_peek() {
        try {
            TokenScanner tsc = new TokenScanner(List.of(
                    createToken(INTEGER, 10)
            ));

            tsc.peek();
        } catch (InvalidOperation invalidOperation) {
            fail("TokenScanner.peek() Threw: " + invalidOperation.getLocalizedMessage());
        }
    }


}
