package L;

import L.ast.Program;
import L.ast.expressions.ConstantExpression;
import L.ast.expressions.Expression;
import L.ast.expressions.SumExpression;
import L.ast.expressions.VariableExpression;
import L.ast.statements.AssignmentStatement;
import L.ast.statements.ExpressionStatement;
import L.ast.statements.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Evaluator {
  private Evaluator() {
  }

  public static int evaluate(Map<String, Integer> environment, Expression expression) throws EvaluationException {
    class EvalVisitor implements Expression.Visitor<Integer> {
      @Override
      public Integer visitConstantExpression(ConstantExpression expression) {
        return expression.value;
      }

      @Override
      public Integer visitSumExpression(SumExpression expression) {
        return expression.left.accept(this) + expression.right.accept(this);
      }

      @Override
      public Integer visitVariableExpression(VariableExpression expression) {
        Integer value = environment.get(expression.name);
        if (value == null) {
          throw new EvaluationException("Variable unbound: " + expression.name);
        }
        return value;

      }
    }

    return expression.accept(new EvalVisitor());
  }

  public static Map<String, Integer> evaluate(Program program) throws EvaluationException {
    HashMap<String, Integer> environment = new HashMap<>();

    class EvalVisitor implements Program.Visitor<Void>, Statement.Visitor<Void> {

      @Override
      public Void visitStatements(List<Statement> statements) {
        statements.forEach((s) -> s.accept(this));
        return null;
      }

      @Override
      public Void visitAssignmentStatement(AssignmentStatement statement) {
        int init = statement.initializer.evaluate(environment);
        environment.put(statement.variable, init);
        return null;
      }

      @Override
      public Void visitExpressionStatement(ExpressionStatement statement) {
        statement.expression.evaluate(environment);
        return null;
      }

    }

    program.accept(new EvalVisitor());
    return environment;
  }

}
