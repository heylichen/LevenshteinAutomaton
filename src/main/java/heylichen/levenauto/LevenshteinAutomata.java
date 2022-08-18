package heylichen.levenauto;

import java.util.Collection;

// by heylichen@qq.com
// T is type of State, for example List<Integer>
public interface LevenshteinAutomata<T> {
  T getStartState();

  T step(T state, char ch);

  boolean isMatch(T state);

  boolean canMatch(T state);

  Collection<Character> transitions(T state);
}
