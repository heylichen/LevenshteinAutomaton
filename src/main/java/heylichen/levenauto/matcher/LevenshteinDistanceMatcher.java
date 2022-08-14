package heylichen.levenauto.matcher;

import java.util.List;

/**
 * keep state as instance variable to make the methods simpler.
 */
public interface LevenshteinDistanceMatcher {
  void step(char ch);

  boolean isMatch();

  boolean canMatch();

  List<Character> transitions();

  List<Integer> getState();
}
