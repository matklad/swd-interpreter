package L.ast;

import L.ast.statements.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Program {
  private List<Statement> statements;

  public Program(List<Statement> statements) {
      this.statements = Collections.unmodifiableList(new ArrayList<>(statements));
  }

  public void accept(Program.Visitor visitor) {
    visitor.visitProgram(statements);
  }

  public interface Visitor<T> {
    T visitProgram(List<Statement> statements);
  }
}
