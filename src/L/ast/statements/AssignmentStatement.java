package L.ast.statements;

import L.ast.expressions.Expression;

import java.util.Map;

public class AssignmentStatement implements Statement {
  private final String variable;
  private final Expression initializer;

  public AssignmentStatement(String variable, Expression initializer) {
    this.variable = variable;
    this.initializer = initializer;
  }

  @Override
  public void evaluate(Map<String, Integer> environment) {
    int value = initializer.evaluate(environment);
    environment.put(variable, value);
  }
}
