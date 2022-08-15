package heylichen.levenauto;


import java.util.ArrayList;
import java.util.List;

public class RowLevenshteinAutomata implements LevenshteinAutomata<List<Integer>> {
  private final List<Integer> startState;
  private final String string;
  private final int maxEdits;

  @Override
  public List<Integer> getStartState() {
    return startState;
  }

  public RowLevenshteinAutomata(String string, int maxEdits) {
    this.string = string;
    List<Integer> localState = new ArrayList<>(string.length() + 1);
    localState.add(0);
    for (int i = 0; i < string.length(); i++) {
      localState.add(i + 1);
    }
    this.startState = localState;
    this.maxEdits = maxEdits;
  }

  @Override
  public List<Integer> step(List<Integer> state, char ch) {
    List<Integer> newState = new ArrayList<>(string.length() + 1);
    newState.add(state.get(0) + 1);

    for (int i = 0; i < string.length(); i++) {
      int cost = ch == string.charAt(i) ? 0 : 1;
      // dist = min(state.get(i) + cost, state.get(i + 1) + 1, newState.get(i) + 1)
      int dist = Math.min(
          state.get(i) + cost,
          state.get(i + 1) + 1
      );
      dist = Math.min(dist, newState.get(i) + 1);
      newState.add(dist);
    }
    return newState;
  }

  @Override
  public boolean isMatch(List<Integer> state) {
    return state.get(state.size() - 1) <= maxEdits;
  }

  @Override
  public boolean canMatch(List<Integer> state) {
    for (Integer integer : state) {
      if (integer <= maxEdits) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<Character> transitions(List<Integer> state) {
    List<Character> result = new ArrayList<>(string.length());
    for (int i = 0; i < string.length(); i++) {
      if (state.get(i) <= maxEdits) {
        result.add(string.charAt(i));
      }
    }
    return result;
  }
}
