package L.ast.expressions;

import L.EvaluationException;
import L.Evaluator;

import java.util.Collections;
import java.util.Map;

public abstract class Expression {
  // Ensure hierarchy is closed
  protected Expression() {
  }

  public final int evaluate() throws EvaluationException {
    return evaluate(Collections.emptyMap());
  }

  public final int evaluate(Map<String, Integer> environment) throws EvaluationException {
    return Evaluator.evaluate(environment, this);
  }

  public abstract <T> T accept(Visitor<T> visitor);

  public interface Visitor<T> {
    T visitConstantExpression(ConstantExpression expression);

    T visitSumExpression(SumExpression expression);

    T visitVariableExpression(VariableExpression expression);
  }
}
