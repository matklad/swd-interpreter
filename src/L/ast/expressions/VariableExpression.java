package L.ast.expressions;

import L.EvaluationException;

import java.util.Map;

public class VariableExpression extends Expression {

  private final String name;

  public VariableExpression(String name) {
    this.name = name;
  }

  @Override
  public int evaluateIn(Map<String, Integer> environment) {
    Integer value = environment.get(name);
    if (value == null) {
      throw new EvaluationException("Variable unbound: " + name);
    }
    return value;
  }
}
