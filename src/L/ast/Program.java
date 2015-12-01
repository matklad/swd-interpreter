package L.ast;

import L.ast.statements.Statement;

import java.util.Collections;
import java.util.List;

public final class Program {
  private List<Statement> statements;

  public Program(List<Statement> statements) {
    this.statements = statements;
  }

  public void accept(Program.Visitor visitor) {
    visitor.visitStatements(Collections.unmodifiableList(statements));
  }

  public interface Visitor<T> {
    T visitStatements(List<Statement> statements);
  }
}
