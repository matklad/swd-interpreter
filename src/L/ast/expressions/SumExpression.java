package L.ast.expressions;

public final class SumExpression extends Expression {
  public final Expression left;
  public final Expression right;

  public SumExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {
    return visitor.visitSumExpression(this);
  }
}
