package heylichen.levenauto;

import java.util.List;

public class RowLevenshteinAutomataDFA extends AbstractLevenshteinAutomataDFA<List<Integer>> {

  public RowLevenshteinAutomataDFA(LevenshteinAutomata<List<Integer>> automaton) {
    super(automaton);
  }

  protected String genKey(List<Integer> state) {
    if (state.isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (Integer iv : state) {
      sb.append(iv.toString()).append(SEP);
    }
    return sb.substring(0, sb.length() - 1);
  }
}
