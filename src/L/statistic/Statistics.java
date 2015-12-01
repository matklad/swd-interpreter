package L.statistic;

import L.ast.Program;
import L.ast.expressions.ConstantExpression;
import L.ast.expressions.Expression;
import L.ast.expressions.SumExpression;
import L.ast.expressions.VariableExpression;
import L.ast.statements.AssignmentStatement;
import L.ast.statements.BlockStatement;
import L.ast.statements.ExpressionStatement;
import L.ast.statements.Statement;

public final class Statistics {
  private Statistics() {
  }

  public interface Collector {
    default void visitConstantExpression(ConstantExpression expression) {
    }

    default void visitSumExpression(SumExpression expression) {
    }

    default void visitVariableExpression(VariableExpression expression) {
    }

    default void visitAssignmentStatement(AssignmentStatement statement) {
    }


    default void visitExpressionStatement(ExpressionStatement statement) {
    }


    default void visitBlockStatement(BlockStatement statement) {
    }

    default void visitProgram(Program program) {
    }
  }

  public static void collect(Program program, Collector collector) {
    program.accept(new StatisticsVisitor(collector));
  }

  private static class StatisticsVisitor implements
      Program.Visitor<Void>,
      Statement.Visitor<Void>,
      Expression.Visitor<Void> {

    private final Collector collector;

    private StatisticsVisitor(Collector collector) {
      this.collector = collector;
    }

    @Override
    public Void visitConstantExpression(ConstantExpression expression) {
      collector.visitConstantExpression(expression);
      return null;
    }

    @Override
    public Void visitSumExpression(SumExpression expression) {
      collector.visitSumExpression(expression);
      expression.left.accept(this);
      expression.right.accept(this);
      return null;
    }

    @Override
    public Void visitVariableExpression(VariableExpression expression) {
      collector.visitVariableExpression(expression);
      return null;
    }

    @Override
    public Void visitAssignmentStatement(AssignmentStatement statement) {
      collector.visitAssignmentStatement(statement);
      statement.initializer.accept(this);
      return null;
    }

    @Override
    public Void visitExpressionStatement(ExpressionStatement statement) {
      collector.visitExpressionStatement(statement);
      statement.expression.accept(this);
      return null;
    }

    @Override
    public Void visitBlockStatement(BlockStatement statement) {
      collector.visitBlockStatement(statement);
      statement.statements.forEach((s) -> s.accept(this));
      return null;
    }

    @Override
    public Void visitProgram(Program program) {
      collector.visitProgram(program);
      program.statements.forEach((s) -> s.accept(this));
      return null;
    }
  }

}
