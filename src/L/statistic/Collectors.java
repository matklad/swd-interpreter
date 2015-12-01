package L.statistic;

import L.ast.Program;
import L.ast.expressions.SumExpression;

public final class Collectors {
  private Collectors() {
  }

  public static int numberOfAdditions(Program program) {

    class AdditionsCollector implements Statistics.Collector {
      int total = 0;

      @Override
      public void visitSumExpression(SumExpression expression) {
        total += 1;
      }
    }

    AdditionsCollector collector = new AdditionsCollector();
    Statistics.collect(program, collector);
    return collector.total;
  }
}
