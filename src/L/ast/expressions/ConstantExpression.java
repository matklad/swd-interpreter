package L.ast.expressions;

public final class ConstantExpression extends Expression {
  public final int value;

  public ConstantExpression(int value) {
    this.value = value;
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {
    return visitor.visitConstantExpression(this);
  }
}
