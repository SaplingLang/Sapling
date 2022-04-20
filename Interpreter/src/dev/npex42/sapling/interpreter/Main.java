package dev.npex42.sapling.interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import dev.npex42.sapling.Compiler;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: sapling <file>");
            System.exit(1);
        }
        String file = Files.readString(Paths.get(args[0]));

    }
}
