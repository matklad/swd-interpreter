package L.ast.expressions;

import java.util.Collections;
import java.util.Map;

public abstract class Expression {

  public final int evaluate() {
    return evaluate(Collections.emptyMap());
  }

  public final int evaluate(Map<String, Integer> environment) {
    // Unfortunately java collections cannot enforce this constraint at compile time,
    // so let's enforce it at run time.
    return evaluateIn(Collections.unmodifiableMap(environment));
  }

  protected abstract int evaluateIn(Map<String, Integer> environment);
}
