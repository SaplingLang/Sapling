import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import dev.npex42.sapling.Compiler;
import dev.npex42.sapling.ast.SyntaxNode;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: sapling <file>");
            System.exit(1);
        }
        String file = Files.readString(Paths.get(args[0]));

        SyntaxNode node = Compiler.compile(file);

        if (node != null)
            node.print(0);
    }
}
