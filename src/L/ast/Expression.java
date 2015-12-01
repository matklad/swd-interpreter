package L.ast;

import java.util.Map;

public abstract class Expression {
  public static Expression parse(String s) {
    s = s.trim();
    int outerPlus = findOuterPlus(s);
    if (outerPlus > 0) {
      return new SumExpression(
          Expression.parse(s.substring(0, outerPlus)),
          Expression.parse(s.substring(outerPlus + 1)));
    }

    if (s.startsWith("(") && s.endsWith(")")) {
      return Expression.parse(s.substring(1, s.length() - 1));
    }

    if (Character.isDigit(s.charAt(0))) {
      return ConstantExpression.of(s);
    }

    return VariableExpression.of(s);
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

  public abstract int evaluate();

  public int evaluate(Map<String, Integer> environment) {
     return evaluate();
  }
}