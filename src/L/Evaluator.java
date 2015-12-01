package L;

import L.ast.Program;
import L.ast.expressions.ConstantExpression;
import L.ast.expressions.Expression;
import L.ast.expressions.SumExpression;
import L.ast.expressions.VariableExpression;
import L.ast.statements.AssignmentStatement;
import L.ast.statements.BlockStatement;
import L.ast.statements.ExpressionStatement;
import L.ast.statements.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Evaluator {
  private Evaluator() {
  }

  public static int evaluate(Expression expression, Map<String, Integer> environment) throws EvaluationException {
    return evaluate(expression, new Scope<>(environment));
  }

  private static int evaluate(Expression expression, Scope<Integer> environment) {
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

    Scope<Integer> environment = new Scope<>();
    program.accept(new EvalVisitor(environment));
    return environment.toMap();
  }

  public static List<String> traceEval(Program program) throws EvaluationException {
    List<String> trace = new ArrayList<>();

    class TracingVisitor extends EvalVisitor {
      TracingVisitor() {
        super(new Scope<>());
      }

      @Override
      public Void visitAssignmentStatement(AssignmentStatement statement) {
        super.visitAssignmentStatement(statement);
        trace.add(statement.variable + " == " + environment.get(statement.variable));
        return null;
      }

      @Override
      public Void visitExpressionStatement(ExpressionStatement statement) {
        super.visitExpressionStatement(statement);
        trace.add("");
        return null;
      }
    }

    program.accept(new TracingVisitor());
    return trace;
  }

  private static class EvalVisitor implements Program.Visitor<Void>, Statement.Visitor<Void> {
    final Scope<Integer> environment;

    EvalVisitor(Scope<Integer> environment) {
      this.environment = environment;
    }

    @Override
    public Void visitProgram(List<Statement> statements) {
      statements.forEach((s) -> s.accept(this));
      return null;
    }

    @Override
    public Void visitAssignmentStatement(AssignmentStatement statement) {
      int init = evaluate(statement.initializer, environment);
      environment.put(statement.variable, init);
      return null;
    }

    @Override
    public Void visitExpressionStatement(ExpressionStatement statement) {
      evaluate(statement.expression, environment);
      return null;
    }

    @Override
    public Void visitBlockStatement(BlockStatement block) {
      environment.pushScope();
      block.statements.forEach((s) -> s.accept(this));
      environment.popScope();
      return null;
    }
  }
}
