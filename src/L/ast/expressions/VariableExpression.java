package L.ast.expressions;

import java.util.Map;

public class VariableExpression extends Expression {

  private final String name;

  public VariableExpression(String name) {
    this.name = name;
  }

  @Override
  public int evaluate() {
    return 0;
  }

  @Override
  public int evaluate(Map<String, Integer> environment) {
    return environment.get(name);
  }
}
