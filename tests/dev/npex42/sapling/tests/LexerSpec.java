package dev.npex42.sapling.tests;

import dev.npex42.sapling.Lexer;
import dev.npex42.sapling.tokens.Token;

import static dev.npex42.sapling.tokens.TokenType.*;

import dev.npex42.sapling.tokens.TokenType;
import org.junit.*;

import java.util.List;

public class LexerSpec {
    @Test
    public void Should_Return_Integer_10() {
        Lexer lexer = new Lexer("10");
        List<Token> tokens = lexer.lex();

        Assert.assertEquals(INTEGER, tokens.get(0).type());
        Assert.assertEquals(10, tokens.get(0).integer());
    }


    @Test
    public void Should_Return_Integer_0() {
        Lexer lexer = new Lexer("0");
        List<Token> tokens = lexer.lex();

        Assert.assertEquals(INTEGER, tokens.get(0).type());
        Assert.assertEquals(0, tokens.get(0).integer());
    }

    @Test
    public void Should_Return_String_TEST() {
        Lexer lexer = new Lexer("\"TEST\"");
        List<Token> tokens = lexer.lex();

        Assert.assertEquals(STRING, tokens.get(0).type());
        Assert.assertEquals("TEST", tokens.get(0).string());
    }

    @Test
    public void Should_Return_String_TEST_WITH_SPACES() {
        Lexer lexer = new Lexer("\"TEST WITH SPACES\"");
        List<Token> tokens = lexer.lex();

        Assert.assertEquals(STRING, tokens.get(0).type());
        Assert.assertEquals("TEST WITH SPACES", tokens.get(0).string());
    }

    @Test
    public void Should_Return_Identifier_Dave() {
        Lexer lexer = new Lexer("Dave");
        List<Token> tokens = lexer.lex();

        Assert.assertEquals(IDENTIFIER, tokens.get(0).type());
        Assert.assertEquals("Dave", tokens.get(0).string());
    }

    @Test
    public void Should_Alse_Return_Identifier_Dave() {
        Lexer lexer = new Lexer("_Dave");
        List<Token> tokens = lexer.lex();

        Assert.assertEquals(IDENTIFIER, tokens.get(0).type());
        Assert.assertEquals("_Dave", tokens.get(0).string());
    }

    @Test
    public void Should_Return_Integer_1_Identifier_Dave() {
        Lexer lexer = new Lexer("1Dave");
        List<Token> tokens = lexer.lex();

        Assert.assertEquals(2, tokens.size());

        Assert.assertEquals(INTEGER, tokens.get(0).type());
        Assert.assertEquals(1, tokens.get(0).integer());

        Assert.assertEquals(IDENTIFIER, tokens.get(1).type());
        Assert.assertEquals("Dave", tokens.get(1).string());
    }

    @Test
    public void Should_Also_Return_Integer_1_Identifier_Dave() {
        Lexer lexer = new Lexer("1 Dave");
        List<Token> tokens = lexer.lex();

        Assert.assertEquals(2, tokens.size());

        Assert.assertEquals(INTEGER, tokens.get(0).type());
        Assert.assertEquals(1, tokens.get(0).integer());

        Assert.assertEquals(IDENTIFIER, tokens.get(1).type());
        Assert.assertEquals("Dave", tokens.get(1).string());
    }

    @Test
    public void Should_Return_FN() {
        Test_Simple_Token("fn", FN);
    }

    @Test
    public void Should_Return_PUBLIC() {
        Test_Simple_Token("public", PUBLIC);
    }

    @Test
    public void Should_Return_PRIVATE() {
        Test_Simple_Token("private", PRIVATE);
    }

    @Test
    public void Should_Return_LPAREN() {
        Test_Simple_Token("(", LPAREN);
    }

    @Test
    public void Should_Return_RPAREN() {
        Test_Simple_Token(")", RPAREN);
    }

    @Test
    public void Should_Return_PLUS() {
        Test_Simple_Token("+", PLUS);
    }

    @Test
    public void Should_Return_SLASH() {
        Test_Simple_Token("/", SLASH);
    }

    @Test
    public void Should_Comment_Out_Line() {
        Test_Simple_Token("// TEST\n 10", INTEGER);
    }


    private void Test_Simple_Token(String input, TokenType expected_type) {
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.lex();

        //System.err.println(tokens);

        Assert.assertEquals(1, tokens.size());

        Assert.assertEquals(expected_type, tokens.get(0).type());
    }


}
