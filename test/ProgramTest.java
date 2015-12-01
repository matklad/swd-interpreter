import L.EvaluationException;
import L.Evaluator;
import L.Parser;
import L.ast.Program;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class ProgramTest {
  private Map<String, Integer> evaluateProgram(String... lines) {
    String source = String.join("\n", lines);
    Program program = Parser.parseProgram(source);
    return Evaluator.evaluate(program);
  }

  private void checkTrace(String... lines) {
    if (lines.length % 2 != 0) {
      throw new IllegalArgumentException("Bad trace");
    }

    String source = "";
    List<String> trace = new ArrayList<>();
    int index = 0;
    for (String line : lines) {
      if (index % 2 == 0) {
        source += line + "\n";
      } else {
        trace.add(line.trim());
      }
      index += 1;
    }

    Program program = Parser.parseProgram(source);
    List<String> actualTrace = Evaluator.traceEval(program);
    assertEquals(trace, actualTrace);
  }

  @Test
  public void testEmpty() throws Exception {
    evaluateProgram("");
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

  @Test
  public void testScopes() throws Exception {
    checkTrace(
        "var x = 2 + 6",      "x == 8",
        "    var y = x + 8",  "y == 16",
        "    var x = 0",      "x == 0",
        "    var q = x + 7",  "q == 7",
        "var z = x + 1",      "z == 9"
    );
  }

  @Test
  public void testDeepNesting() throws Exception {
    checkTrace(
        "var x = 1",          "x == 1",
        "  var x = x + 1",    "x == 2",
        "    var x = x + 1",  "x == 3"
    );
  }

}
