package L.ast;

public class SumExpression extends Expression {
  private final Expression left;
  private final Expression right;

  private SumExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  public static Expression of(Expression left, Expression right) {
    return new SumExpression(left, right);
  }

  @Override
  public int evaluate() {
    return left.evaluate() + right.evaluate();
  }
}
