package L.ast.statements;

import L.ast.expressions.Expression;

import java.util.Map;

public class ExpressionStatement implements Statement {
  private final Expression expression;

  public ExpressionStatement(Expression expression) {
    this.expression = expression;
  }

  @Override
  public void evaluate(Map<String, Integer> environment) {
    // Not sure what is the semantic of expression statement.
    expression.evaluate(environment);
  }
}
