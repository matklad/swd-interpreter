package L.ast.expressions;

import java.util.Map;

public class ConstantExpression extends Expression {
  private final int value;

  public ConstantExpression(int value) {
    this.value = value;
  }

  @Override
  public int evaluateIn(Map<String, Integer> environment) {
    return value;
  }
}
