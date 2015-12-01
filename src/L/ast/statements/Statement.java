package L.ast.statements;

public abstract class Statement {
  // protected constructor to ensure hierarchy is closed
  Statement() {
  }

  public abstract <T> T accept(Visitor<T> visitor);

  public interface Visitor<T> {
    T visitAssignmentStatement(AssignmentStatement statement);

    T visitExpressionStatement(ExpressionStatement statement);

    T visitBlockStatement(BlockStatement statement);
  }
}
