package L;

import L.ast.Program;
import L.ast.expressions.ConstantExpression;
import L.ast.expressions.Expression;
import L.ast.expressions.SumExpression;
import L.ast.expressions.VariableExpression;
import L.ast.statements.AssignmentStatement;
import L.ast.statements.ExpressionStatement;
import L.ast.statements.Statement;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Parser {
  private Parser() {
  }

  public static Program parseProgram(String source) throws ParseException {
    String[] lines = source.split(System.lineSeparator());
    List<Statement> statements = Arrays.stream(lines)
        .map(Parser::parseStatement)
        .collect(Collectors.toList());

    return new Program(statements);
  }

  public static Expression parseExpression(String source) throws ParseException {
    source = source.trim();
    int outerPlus = findOuterPlus(source);

    if (outerPlus > 0) {
      Expression left = parseExpression(source.substring(0, outerPlus));
      Expression right = parseExpression(source.substring(outerPlus + 1));
      return new SumExpression(left, right);
    }

    if (source.startsWith("(") && source.endsWith(")")) {
      return parseExpression(source.substring(1, source.length() - 1));
    }

    if (Character.isDigit(source.charAt(0))) {
      return parseConstExpression(source);
    }

    return parseVariableExpression(source);
  }

  private static Statement parseStatement(String source) {
    // can be made static, but lets not optimize prematurely
    Pattern re = Pattern.compile("var\\s+(\\w+)\\s*=\\s*(.*)");
    Matcher match = re.matcher(source);
    if (match.matches()) {
      String variable = match.group(1);
      Expression expression = parseExpression(match.group(2));
      return new AssignmentStatement(variable, expression);
    }

    return new ExpressionStatement(parseExpression(source));
  }

  private static Expression parseVariableExpression(String source) {
    if (!source.matches("\\w+")) {
      throw new ParseException("Invalid variable name: " + source);
    }
    return new VariableExpression(source);
  }

  private static Expression parseConstExpression(String source) {
    int value;
    try {
      value = Integer.valueOf(source);
    } catch (NumberFormatException e) {
      throw new ParseException("Wrong number format for: " + source, e);
    }
    return new ConstantExpression(value);
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
