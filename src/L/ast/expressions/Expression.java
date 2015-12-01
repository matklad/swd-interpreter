package L.ast.expressions;

import java.util.Map;

public abstract class Expression {

  public abstract int evaluate();

  public int evaluate(Map<String, Integer> environment) {
     return evaluate();
  }
}
