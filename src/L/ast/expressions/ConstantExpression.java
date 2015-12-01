package L.ast.expressions;

public class ConstantExpression extends Expression {
  private final int value;

  public ConstantExpression(int value) {
    this.value = value;
  }

  @Override
  public int evaluate() {
    return value;
  }
}
