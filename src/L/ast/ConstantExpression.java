package L.ast;

public class ConstantExpression extends Expression {
  private final int value;

  private ConstantExpression(int value) {
    this.value = value;
  }

  public static ConstantExpression of(int value) {
    return new ConstantExpression(value);
  }

  @Override
  public int evaluate() {
    return value;
  }
}
