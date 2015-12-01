import L.EvaluationException;
import L.Evaluator;
import L.ParseException;
import L.Parser;
import L.ast.expressions.Expression;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExpressionTest {
  private int evaluateExpression(String source) {
    Expression expression = Parser.parseExpression(source);
    return Evaluator.evaluate(expression, Collections.emptyMap());
  }

  private int evaluateExpression(String source, Map<String, Integer> environment) {
    Expression expression = Parser.parseExpression(source);
    return Evaluator.evaluate(expression, environment);
  }

  @Test
  public void testEvalConst() throws Exception {
    assertEquals(92, evaluateExpression("92"));
  }

  @Test
  public void testEvalOtherConst() throws Exception {
    assertEquals(4, evaluateExpression("4"));
  }

  @Test
  public void testScopeInteger() throws Exception {
    assertEquals(42, evaluateExpression("(42)"));
  }

  @Test(expected = ParseException.class)
  public void testInvalidParenthesis() throws Exception {
    Parser.parseExpression("((");
  }

  @Test
  public void testSum() throws Exception {
    assertEquals(4, evaluateExpression("2+2"));
  }

  @Test
  public void testSpacedExpression() throws Exception {
    assertEquals(4, evaluateExpression("2 + 2"));
  }

  @Test
  public void testSumWithParenthesis() throws Exception {
    assertEquals(6, evaluateExpression("2+(2 + 2)"));
  }

  @Test
  public void testComplicatedExpression() throws Exception {
    assertEquals(6, evaluateExpression("((2)+(2 + 2))"));
  }

  @Test
  public void testVariableExpression() throws Exception {
    Parser.parseExpression("foo");
  }

  @Test
  public void testEvaluateInEnvironment() throws Exception {
    Map<String, Integer> environment = Collections.singletonMap("x", 92);
    assertEquals(92, evaluateExpression("x", environment));
  }

  @Test(expected = EvaluationException.class)
  public void testUnboundVariable() throws Exception {
    assertEquals(92, evaluateExpression("x", Collections.emptyMap()));
  }
}
