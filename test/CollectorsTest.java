import L.Parser;
import L.ast.Program;
import L.statistic.Collectors;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectorsTest {
  @Test
  public void testNumberOfAdditions() throws Exception {
    Program program = Parser.parseProgram("var x = 1 + 2 + (3 + 4)\n    var y = x + x");
    assertEquals(4, Collectors.numberOfAdditions(program));
  }
}
