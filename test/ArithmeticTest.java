import L.ParseException;
import L.Parser;
import L.ast.expressions.Expression;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ArithmeticTest {
  @Test
  public void testEvalConst() throws Exception {
    Expression expression = Parser.parseExpression("92");
    int actual = expression.evaluate();
    assertEquals(92, actual);
  }

  @Test
  public void testEvalOtherConst() throws Exception {
    Expression expression = Parser.parseExpression("4");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testScopeInteger() throws Exception {
    Expression expression = Parser.parseExpression("(42)");
    int actual = expression.evaluate();
    assertEquals(42, actual);
  }

  @Test(expected = ParseException.class)
  public void testInvalidParenthesis() throws Exception {
    Parser.parseExpression("((");
  }

  @Test
  public void testSum() throws Exception {
    Expression expression = Parser.parseExpression("2+2");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSpacedExpression() throws Exception {
    Expression expression = Parser.parseExpression("(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSumWithParenthesis() throws Exception {
    Expression expression = Parser.parseExpression("2+(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testComplicatedExpression() throws Exception {
    Expression expression = Parser.parseExpression("((2)+(2 + 2))");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testVariableExpression() throws Exception {
    Parser.parseExpression("foo");
  }

  @Test
  public void testEvaluateInEnvironment() throws Exception {
    Map<String, Integer> environment = Collections.singletonMap("x", 92);
    Expression expression = Parser.parseExpression("x");
    assertEquals(92, expression.evaluate(environment));

  }
}
