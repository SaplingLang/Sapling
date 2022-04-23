package dev.npex42.sapling.tests;

import dev.npex42.sapling.parser.Parser;
import dev.npex42.sapling.parser.ast.operators.BinaryOp;
import dev.npex42.sapling.parser.ast.values.ValueNode;
import dev.npex42.sapling.tokens.Token;
import dev.npex42.sapling.tokens.TokenType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static dev.npex42.sapling.tokens.TokenType.PLUS;
import static junit.framework.Assert.assertEquals;

public class ParserSpec {

    private List<Token> buildTokenList(Token... tokens) {
        return new ArrayList<Token>(List.of(tokens));
    }

    @Test
    public void should_return_IntegerNode_10() {
        List<Token> tokens = buildTokenList(new Token(TokenType.INTEGER, 10));

        Parser parser = new Parser(tokens);

        Assert.assertEquals(10, parser.integer().value());

    }

    @Test
    public void should_return_StringNode_TEST() {
        List<Token> tokens = buildTokenList(new Token(TokenType.STRING, "TEST"));

        Parser parser = new Parser(tokens);

        Assert.assertEquals("TEST", parser.string().value());

    }

    @Test
    public void should_return_IdentNode_TEST() {
        List<Token> tokens = buildTokenList(new Token(TokenType.IDENTIFIER, "TEST"));

        Parser parser = new Parser(tokens);

        Assert.assertEquals("TEST", parser.identifier().value());
    }


    @Test
    public void should_return_ValueNode_INT_10() {
        List<Token> tokens = buildTokenList(new Token(TokenType.INTEGER, 10));

        Parser parser = new Parser(tokens);

        Assert.assertEquals(10, ((ValueNode) parser.value()).intValue());
    }

    @Test
    public void should_also_return_ValueNode_INT_10() {
        List<Token> tokens = buildTokenList(new Token(TokenType.INTEGER, 10));

        Parser parser = new Parser(tokens);

        Assert.assertEquals(10, ((ValueNode) parser.unary()).intValue());
    }

    @Test
    public void should_return_ValueNode_String_TEST() {
        List<Token> tokens = buildTokenList(new Token(TokenType.STRING, "TEST"));

        Parser parser = new Parser(tokens);

        Assert.assertEquals("TEST", ((ValueNode) parser.value()).stringValue());
    }

    @Test
    public void should_return_ValueNode_Identifier_TEST() {
        List<Token> tokens = buildTokenList(new Token(TokenType.IDENTIFIER, "TEST"));

        Parser parser = new Parser(tokens);

        Assert.assertEquals("TEST", ((ValueNode) parser.value()).stringValue());
    }

    @Test
    public void should_return_BinaryOp_Plus() {
        List<Token> tokens = buildTokenList(
                new Token(TokenType.INTEGER, 10),
                new Token(PLUS),
                new Token(TokenType.INTEGER, 20)
        );

        Parser parser = new Parser(tokens);

        BinaryOp op = (BinaryOp) parser.sum();
        ValueNode lhs = (ValueNode) op.lhs;
        ValueNode rhs = (ValueNode) op.rhs;

        assertEquals(10, lhs.intValue());
        assertEquals(PLUS, op.op);
        assertEquals(20, rhs.intValue());
    }

}
