package L.ast;

import L.ParseException;

import java.util.Map;

public class VariableExpression extends Expression {

  private final String name;

  private VariableExpression(String name) {
    this.name = name;
  }

  public static VariableExpression of(String s) {
    if (!s.matches("\\w+")) {
      throw new ParseException("Invalid variable name: " + s);
    }
    return new VariableExpression(s);
  }

  @Override
  public int evaluate() {
    return 0;
  }

  @Override
  public int evaluate(Map<String, Integer> environment) {
    return environment.get(name);
  }
}