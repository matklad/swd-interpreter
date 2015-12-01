package L;

import L.ast.ConstantExpression;
import L.ast.Expression;
import L.ast.SumExpression;
import L.ast.VariableExpression;

public final class Parser {
  private Parser() {
  }

  public static Expression parseExpression(String source) throws ParseException {
    source = source.trim();
    int outerPlus = findOuterPlus(source);

    if (outerPlus > 0) {
      Expression left = parseExpression(source.substring(0, outerPlus));
      Expression right = parseExpression(source.substring(outerPlus + 1));
      return SumExpression.of(left, right);
    }

    if (source.startsWith("(") && source.endsWith(")")) {
      return parseExpression(source.substring(1, source.length() - 1));
    }

    if (Character.isDigit(source.charAt(0))) {
      int value;
      try {
        value = Integer.valueOf(source);
      } catch (NumberFormatException e) {
        throw new ParseException("Wrong number format for: " + source, e);
      }
      return ConstantExpression.of(value);
    }

    if (!source.matches("\\w+")) {
      throw new ParseException("Invalid variable name: " + source);
    }
    return VariableExpression.of(source);
  }

  private static int findOuterPlus(String s) {
    int scopeCounter = 0;
    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case ')':
          scopeCounter += 1;
          break;
        case '(':
          scopeCounter -= 1;
          break;
        case '+':
          if (scopeCounter == 0) {
            return i;
          }
      }
    }
    return -1;
  }

}
