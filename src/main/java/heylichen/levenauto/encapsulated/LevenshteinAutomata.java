package heylichen.levenauto.encapsulated;

import java.util.List;

/**
 * keep state as instance variable to make the methods simpler.
 */
public interface LevenshteinAutomata {
  void step(char ch);

  boolean isMatch();

  boolean canMatch();

  List<Character> transitions();
}
