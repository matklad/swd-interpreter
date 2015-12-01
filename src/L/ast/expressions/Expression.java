package L.ast.expressions;

public abstract class Expression {
  // Ensure hierarchy is closed
  protected Expression() {
  }

  public abstract <T> T accept(Visitor<T> visitor);

  public interface Visitor<T> {
    T visitConstantExpression(ConstantExpression expression);

    T visitSumExpression(SumExpression expression);

    T visitVariableExpression(VariableExpression expression);
  }
}
