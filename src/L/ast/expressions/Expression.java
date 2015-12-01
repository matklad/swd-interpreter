package L.ast.expressions;

import java.util.Collections;
import java.util.Map;

public abstract class Expression {

  public final int evaluate() {
    return evaluate(Collections.emptyMap());
  }

  public abstract int evaluate(Map<String, Integer> environment);
}
