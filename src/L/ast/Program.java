package L.ast;

import L.ast.statements.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Program {
  public final List<Statement> statements;

  public Program(List<Statement> statements) {
      this.statements = Collections.unmodifiableList(new ArrayList<>(statements));
  }

  public void accept(Program.Visitor visitor) {
    visitor.visitProgram(this);
  }

  public interface Visitor<T> {
    T visitProgram(Program program);
  }
}
