package dev.npex42.sapling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: sapling <file>");
            System.exit(1);
        }
        String file = Files.readString(Paths.get(args[0]));

        Lexer lexer = new Lexer(file);
        List<Token> tokens = lexer.lex();

        System.out.println("==== TOKENS ====");
        for (Token t : tokens) {
            System.out.printf("%s%n", t);
        }

        System.out.println();

        if (!lexer.hadError()) {
            Parser parser = new Parser(tokens);

            System.out.println("==== AST ====");
            parser.parse().print(0);
        }
    }
}
