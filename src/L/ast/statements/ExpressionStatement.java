package L.ast.statements;

import L.ast.expressions.Expression;

public final class ExpressionStatement extends Statement {
  public final Expression expression;

  public ExpressionStatement(Expression expression) {
    this.expression = expression;
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {
    return visitor.visitExpressionStatement(this);
  }
}
