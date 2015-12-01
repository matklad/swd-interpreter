import L.ParseException;
import L.ast.Expression;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ArithmeticTest {
  @Test
  public void testEvalConst() throws Exception {
    Expression expression = Expression.parse("92");
    int actual = expression.evaluate();
    assertEquals(92, actual);
  }

  @Test
  public void testEvalOtherConst() throws Exception {
    Expression expression = Expression.parse("4");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testScopeInteger() throws Exception {
    Expression expression = Expression.parse("(42)");
    int actual = expression.evaluate();
    assertEquals(42, actual);
  }

  @Test(expected = ParseException.class)
  public void testInvalidParenthesis() throws Exception {
    Expression.parse("((");
  }

  @Test
  public void testSum() throws Exception {
    Expression expression = Expression.parse("2+2");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSpacedExpression() throws Exception {
    Expression expression = Expression.parse("(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(4, actual);
  }

  @Test
  public void testSumWithParenthesis() throws Exception {
    Expression expression = Expression.parse("2+(2 + 2)");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testComplicatedExpression() throws Exception {
    Expression expression = Expression.parse("((2)+(2 + 2))");
    int actual = expression.evaluate();
    assertEquals(6, actual);
  }

  @Test
  public void testVariableExpression() throws Exception {
    Expression.parse("foo");
  }

  @Test
  public void testEvaluateInEnvironment() throws Exception {
    Map<String, Integer> environment = Collections.singletonMap("x", 92);
    Expression expression = Expression.parse("x");
    assertEquals(92, expression.evaluate(environment));

  }
}
