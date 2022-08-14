package heylichen.levenauto;

import heylichen.levenauto.matcher.LevenshteinDistanceMatcher;

public abstract class BaseLevenshteinDistanceMatcherTest {

  protected boolean match(String input) {
    LevenshteinDistanceMatcher lm = newMatcher();
    return canMatch(lm, input);
  }

  protected abstract LevenshteinDistanceMatcher newMatcher();

  private boolean canMatch(LevenshteinDistanceMatcher lm, String input) {
    int i = 0;
    for (; i < input.length() && lm.canMatch(); i++) {
      lm.step(input.charAt(i));
      if (i == input.length() - 1 && lm.isMatch()) {
        return true;
      }
    }
    return false;
  }
}
