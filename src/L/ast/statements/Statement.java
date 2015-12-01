package L.ast.statements;

import java.util.Map;

public interface Statement {
  void evaluate(Map<String, Integer> environment);
}
