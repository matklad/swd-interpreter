package L.ast;

import L.ParseException;

public class ConstantExpression extends Expression {
  private final int value;

  public ConstantExpression(int value) {
    this.value = value;
  }

  @Override
  public int evaluate() {
    return value;
  }

  public static ConstantExpression of(String s) {
    try {
      return new ConstantExpression(Integer.valueOf(s));
    } catch (NumberFormatException e) {
      throw new ParseException("Wrong number format for: " + s, e);
    }
  }
}
