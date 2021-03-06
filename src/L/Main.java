package L;

import L.ast.Program;
import L.statistic.Collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Main {
  public static void main(String[] args) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(args[0]));
    String input = new String(bytes, "UTF-8");
    Program p = Parser.parseProgram(input);
    System.out.println("Program has " + Collectors.numberOfAdditions(p) + " additions");
    Evaluator.traceEval(p).forEach(System.out::println);
  }
}
