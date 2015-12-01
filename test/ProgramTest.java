import L.EvaluationException;
import L.Parser;
import L.ast.Program;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class ProgramTest {
  private Map<String, Integer> evaluateProgram(String... lines) {
    String source = String.join("\n", lines);
    Program program = Parser.parseProgram(source);
    return program.evaluate();
  }

  @Test
  public void testExpression() throws Exception {
    Map<String, Integer> environment = evaluateProgram("92");
    assertEquals(0, environment.size());
  }

  @Test
  public void testAssignExpression() throws Exception {
    Map<String, Integer> environment = evaluateProgram("var x = 92");
    assertEquals(92, (int) environment.get("x"));
  }

  @Test
  public void testThreadsEnvironment() throws Exception {
    Map<String, Integer> environment = evaluateProgram(
        "var x = 92",
        "var y = x + x"
    );
    assertEquals(92, (int) environment.get("x"));
    assertEquals(184, (int) environment.get("y"));
  }

  @Test(expected = EvaluationException.class)
  public void testUnboundVariable() throws Exception {
    evaluateProgram("z");
  }
}
