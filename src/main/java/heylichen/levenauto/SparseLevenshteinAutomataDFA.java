package heylichen.levenauto;

import java.util.List;

public class SparseLevenshteinAutomataDFA extends AbstractLevenshteinAutomataDFA<List<IndexValue>> {
  public SparseLevenshteinAutomataDFA(LevenshteinAutomata<List<IndexValue>> automaton) {
    super(automaton);
  }

  protected String genKey(List<IndexValue> state) {
    if (state.isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (IndexValue iv : state) {
      sb.append(iv.getStateIndex()).append(SEP).append(iv.getDistanceValue()).append(SEP);
    }
    return sb.substring(0, sb.length() - 1);
  }
}
