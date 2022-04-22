package dev.npex42.sapling.interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import dev.npex42.sapling.Compiler;
import dev.npex42.sapling.parser.Parser;
import dev.npex42.sapling.parser.ast.values.Expression;
import dev.npex42.sapling.tokens.TokenScanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: sapling <file>");
            System.exit(1);
        }
        String file = Files.readString(Paths.get(args[0]));

        TokenScanner scanner = Compiler.tokenize(file);

        Parser parser = new Parser(scanner);


        Expression expr = (Expression) parser.parse();

        Interpreter interpreter = new Interpreter();
        System.out.println(interpreter.Evaluate(expr));


    }
}
