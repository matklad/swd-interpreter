package L.ast.statements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockStatement extends Statement {
  public final List<Statement> statements;

  public BlockStatement(List<Statement> statements) {
    this.statements = Collections.unmodifiableList(new ArrayList<>(statements));
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {
    return visitor.visitBlockStatement(this);
  }
}
