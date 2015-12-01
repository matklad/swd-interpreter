package L.ast;

public class SumExpression extends Expression {
  private final Expression left;
  private final Expression right;

  public SumExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public int evaluate() {
    return left.evaluate() + right.evaluate();
  }
}
