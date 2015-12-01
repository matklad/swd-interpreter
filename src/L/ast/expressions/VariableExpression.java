package L.ast.expressions;

public final class VariableExpression extends Expression {

  public final String name;

  public VariableExpression(String name) {
    this.name = name;
  }


  @Override
  public <T> T accept(Visitor<T> visitor) {
    return visitor.visitVariableExpression(this);
  }
}
