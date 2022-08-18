package heylichen.levenauto;

import java.util.*;

public abstract class AbstractLevenshteinAutomataDFA<T> {

  private final LevenshteinAutomata<T> automaton;
  // for generating state id
  private int idCounter = 0;
  // for remember state id that have been encountered
  private Map<String, Integer> stateMap;
  // result DFA states
  private List<Transition> transitions;
  private Set<Integer> matchedStateIds;

  // for match method, computed from transitions
  private Map<Integer, Map<Character, Integer>> transitionMap;
  private boolean matchEmptyInput;

  protected static final char SEP = ',';
  protected static final Character OTHER = '*';

  public AbstractLevenshteinAutomataDFA(LevenshteinAutomata<T> automaton) {
    this.automaton = automaton;
    init();
  }

  private void init() {
    matchedStateIds = new HashSet<>();
    stateMap = new HashMap<>();
    transitions = new ArrayList<>();
    explore(automaton.getStartState());
    transitions.sort(Comparator.comparing(Transition::getFromStateId).thenComparing(Transition::getToStateId).thenComparing(Transition::getCh));
    initForMatch();
  }

  private void initForMatch() {
    Map<Integer, Map<Character, Integer>> localTransitionMap = new HashMap<>();
    for (Transition transition : transitions) {
      Integer fromState = transition.fromStateId;
      Integer toState = transition.toStateId;
      Character ch = transition.ch;
      localTransitionMap.computeIfAbsent(fromState, (k) -> new HashMap<>())
          .put(ch, toState);
    }
    this.transitionMap = localTransitionMap;
    this.matchEmptyInput = automaton.isMatch(automaton.getStartState());
  }

  /**
   * print to graphviz dot dsl
   *
   * @return
   */
  public String printDot() {
    StringBuilder sb = new StringBuilder("digraph G {\n");
    for (Transition transition : transitions) {
      sb.append(String.format("%s -> %s [label=\" %s \"]\n", transition.fromStateId.toString(),
          transition.toStateId.toString(),
          transition.ch));
    }

    for (Integer matchedStateId : matchedStateIds) {
      sb.append(String.format("%s [style=filled]\n", matchedStateId));
    }
    sb.append("}");
    return sb.toString();
  }

  private Integer explore(T state) {
    String stateKey = genKey(state);
    Integer existedStateId = stateMap.get(stateKey);
    if (existedStateId != null) {
      return existedStateId;
    }
    int currentStateId = idCounter;
    idCounter++;
    stateMap.put(stateKey, currentStateId);

    if (automaton.isMatch(state)) {
      matchedStateIds.add(currentStateId);
    }
    Collection<Character> chars = automaton.transitions(state);
    // OTHER stands for any other characters
    chars.add(OTHER);

    for (Character ch : chars) {
      T newState = automaton.step(state, ch);
      Integer toStateId = explore(newState);
      transitions.add(new Transition(currentStateId, ch, toStateId));
    }
    return currentStateId;
  }

  /**
   * This implementation use the transitionMap and matchedStateIds as automaton state
   * (can be implemented directly using automatonCore).
   * no need to generate intermediate state compared to automatonCore.
   *
   * @param inputString
   * @return
   */
  public boolean match(String inputString) {
    if (inputString == null || inputString.length() == 0) {
      return matchEmptyInput;
    }
    Set<Integer> encountered = new HashSet<>();
    Integer currentState = 0;

    for (int i = 0; i < inputString.length(); i++) {
      Map<Character, Integer> transMap = transitionMap.get(currentState);
      char ch = inputString.charAt(i);
      Integer toState = transMap.get(ch);
      if (toState == null) {
        toState = transMap.get(OTHER);
      }
      if (encountered.contains(toState)) {
        return false;
      } else {
        encountered.add(toState);
        currentState = toState;
      }
    }
    return matchedStateIds.contains(currentState);
  }

  /**
   * gen key for a state, to check if a state has been encountered in stateMap
   *
   * @param state
   * @return
   */
  protected abstract String genKey(T state);

  private static class Transition {
    private final Integer fromStateId;
    private final Character ch;
    private final Integer toStateId;

    public Transition(Integer fromStateId, Character ch, Integer toStateId) {
      this.fromStateId = fromStateId;
      this.ch = ch;
      this.toStateId = toStateId;
    }

    public Integer getFromStateId() {
      return fromStateId;
    }

    public Character getCh() {
      return ch;
    }

    public Integer getToStateId() {
      return toStateId;
    }
  }

}
