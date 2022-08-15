package heylichen.levenauto;


public interface LevenshteinAutomata<T> {
  T getStartState();

  T step(T state, char ch);

  boolean isMatch(T state);

  boolean canMatch(T state);

  Iterable<Character> transitions(T state);
}
