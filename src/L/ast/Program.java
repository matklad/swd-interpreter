package L.ast;

import L.EvaluationException;
import L.Evaluator;
import L.ast.statements.AssignmentStatement;
import L.ast.statements.ExpressionStatement;
import L.ast.statements.Statement;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
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

  public Map<String, Integer> evaluate() throws EvaluationException {
    return Evaluator.evaluate(this);
  }
}
