package heylichen.levenauto.matcher;

import heylichen.levenauto.IndexValue;

import java.util.ArrayList;
import java.util.List;

public class SparseLevenshteinDistanceMatcher implements LevenshteinDistanceMatcher {
  private final String string;
  private final int maxEdits;
  //only stores IndexValue with distance <= maxEdits
  private List<IndexValue> state;

  public SparseLevenshteinDistanceMatcher(String string, int maxEdits) {
    this.string = string;
    this.maxEdits = maxEdits;
    int initCount = maxEdits + 1;
    state = new ArrayList<>(initCount);
    for (int i = 0; i < initCount; i++) {
      state.add(new IndexValue(i, i));
    }
  }

  public void step(char ch) {
    IndexValue oldFirst = !state.isEmpty() ? state.get(0) : null;
    List<IndexValue> newStates = new ArrayList<>(maxEdits * 2 + 1);
    if (oldFirst != null && oldFirst.getStateIndex() == 0 && oldFirst.getDistanceValue() < maxEdits) {
      newStates.add(new IndexValue(0, oldFirst.getDistanceValue() + 1));
    }

    for (int i = 0; i < state.size(); i++) {
      IndexValue current = state.get(i);
      if (current.getStateIndex() == string.length()) {
        //this.string exhausted, stop
        break;
      }
      int nextCharIndex = current.getStateIndex();
      int cost = ch == string.charAt(nextCharIndex) ? 0 : 1;
      int value = current.getDistanceValue() + cost;

      IndexValue newPrevious = newStates.isEmpty() ? null : newStates.get(newStates.size() - 1);
      if (newPrevious != null && newPrevious.getStateIndex() == i) {
        value = Math.min(value, newPrevious.getDistanceValue() + 1);
      }

      IndexValue oldNext = i + 1 < state.size() ? state.get(i + 1) : null;
      if (oldNext != null) {
        value = Math.min(value, oldNext.getDistanceValue() + 1);
      }

      if (value <= maxEdits) {
        newStates.add(new IndexValue(current.getStateIndex() + 1, value));
      }
    }
    this.state = newStates;
  }

  public boolean isMatch() {
    return !state.isEmpty() && state.get(state.size() - 1).getStateIndex() == string.length();
  }

  public boolean canMatch() {
    return !state.isEmpty();
  }

  public List<Character> transitions() {
    List<Character> result = new ArrayList<>();
    for (IndexValue indexValue : state) {
      int prefixLen = indexValue.getStateIndex();
      if (prefixLen < string.length()) {
        result.add(string.charAt(prefixLen));
      }
    }
    return result;
  }

  @Override
  public List<Integer> getState() {
    List<Integer> states = new ArrayList<>(state.size());
    for (IndexValue indexValue : state) {
      states.add(indexValue.getStateIndex());
    }
    return states;
  }
}
