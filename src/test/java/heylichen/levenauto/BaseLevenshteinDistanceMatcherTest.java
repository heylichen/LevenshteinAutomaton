package heylichen.levenauto;

import heylichen.levenauto.encapsulated.LevenshteinAutomata;

public abstract class BaseLevenshteinDistanceMatcherTest {

  protected boolean match(String input) {
    LevenshteinAutomata lm = newMatcher();
    return canMatch(lm, input);
  }

  protected abstract LevenshteinAutomata newMatcher();

  private boolean canMatch(LevenshteinAutomata lm, String input) {
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
