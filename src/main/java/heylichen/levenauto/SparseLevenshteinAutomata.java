package heylichen.levenauto;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SparseLevenshteinAutomata implements LevenshteinAutomata<List<IndexValue>> {
  private final String string;
  private final int maxEdits;

  public SparseLevenshteinAutomata(String string, int maxEdits) {
    this.string = string;
    this.maxEdits = maxEdits;
  }

  @Override
  public List<IndexValue> getStartState() {
    int initCount = maxEdits + 1;
    List<IndexValue> indexValues = new ArrayList<>(initCount);
    for (int i = 0; i < initCount; i++) {
      indexValues.add(new IndexValue(i, i));
    }
    return indexValues;
  }

  @Override
  public List<IndexValue> step(List<IndexValue> state, char ch) {
    IndexValue oldFirst = !state.isEmpty() ? state.get(0) : null;
    List<IndexValue> newStates = new ArrayList<>(maxEdits * 2 + 1);
    if (oldFirst != null && oldFirst.getStateIndex() == 0 && oldFirst.getDistanceValue() < maxEdits) {
      newStates.add(new IndexValue(0, oldFirst.getDistanceValue() + 1));
    }

    for (int i = 0; i < state.size(); i++) {
      IndexValue current = state.get(i);
      // currentStateIndex is the prefix len of this.string
      // currentStateIndex - 1 is the char index of this.string that has stepped in current state
      int currentStateIndex = current.getStateIndex();
      if (currentStateIndex == string.length()) {
        //this.string exhausted, stop
        break;
      }

      int cost = ch == string.charAt(currentStateIndex) ? 0 : 1;
      int value = current.getDistanceValue() + cost;

      IndexValue newPrevious = newStates.isEmpty() ? null : newStates.get(newStates.size() - 1);
      if (newPrevious != null && newPrevious.getStateIndex() == currentStateIndex) {
        value = Math.min(value, newPrevious.getDistanceValue() + 1);
      }

      IndexValue oldNext = i + 1 < state.size() ? state.get(i + 1) : null;
      if (oldNext != null && oldNext.getStateIndex() == currentStateIndex + 1) {
        value = Math.min(value, oldNext.getDistanceValue() + 1);
      }

      if (value <= maxEdits) {
        newStates.add(new IndexValue(currentStateIndex + 1, value));
      }
    }
    return newStates;
  }

  @Override
  public boolean isMatch(List<IndexValue> state) {
    return !state.isEmpty() && state.get(state.size() - 1).getStateIndex() == string.length();
  }

  @Override
  public boolean canMatch(List<IndexValue> state) {
    return !state.isEmpty();
  }

  @Override
  public Set<Character> transitions(List<IndexValue> state) {
    Set<Character> result = new LinkedHashSet<>();
    for (IndexValue indexValue : state) {
      int prefixLen = indexValue.getStateIndex();
      if (prefixLen < string.length()) {
        result.add(string.charAt(prefixLen));
      }
    }
    return result;
  }

}
