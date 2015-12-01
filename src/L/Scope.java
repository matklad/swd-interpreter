package L;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Scope<T> {
  private final ArrayDeque<Map<String, T>> scopes;

  public Scope(Map<String, T> environment) {
    scopes = new ArrayDeque<>();
    scopes.push(new HashMap<>(environment));
  }

  public Scope() {
    this(Collections.emptyMap());
  }


  T get(String name) {
    for (Map<String, T> scope : scopes) {
      if (scope.containsKey(name)) {
        return scope.get(name);
      }
    }
    return null;
  }

  void put(String name, T value) {
    scopes.peek().put(name, value);
  }

  void pushScope() {
    scopes.push(new HashMap<>());
  }

  void popScope() {
    scopes.pop();
  }

  public Map<String, T> toMap() {
    HashMap<String, T> result = new HashMap<>();
    for (Map<String, T> scope : scopes) {
      for (Map.Entry<String, T> entry : scope.entrySet()) {
        if (!result.containsKey(entry.getKey())) {
          result.put(entry.getKey(), entry.getValue());
        }
      }
    }
    return result;
  }
}
