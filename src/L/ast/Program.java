package L.ast;

import L.EvaluationException;
import L.ast.statements.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
  private List<Statement> statements;

  public Program(List<Statement> statements) {
    this.statements = statements;
  }

  public Map<String, Integer> evaluate() throws EvaluationException {
    HashMap<String, Integer> environment = new HashMap<>();
    for (Statement statement : statements) {
      statement.evaluate(environment);
    }
    return environment;
  }
}
