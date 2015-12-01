package L.ast.statements;

import L.ast.expressions.Expression;

public final class AssignmentStatement extends Statement {
  public final String variable;
  public final Expression initializer;

  public AssignmentStatement(String variable, Expression initializer) {
    this.variable = variable;
    this.initializer = initializer;
  }

  @Override
  public void accept(Statement.Visitor visitor) {
    visitor.visitAssignmentStatement(this);
  }
}
