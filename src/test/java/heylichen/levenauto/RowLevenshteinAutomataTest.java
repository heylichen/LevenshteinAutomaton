package heylichen.levenauto;

import org.junit.Test;

import java.util.List;

public class RowLevenshteinAutomataTest {

  @Test
  public void testCanMatch() {
    LevenshteinAutomata<List<Integer>> la = new RowLevenshteinAutomata("banana", 1);
    List<Integer> state = la.getStartState();

    state = la.step(state, 'w');
    // True, "w" can match "bannana" with distance 1
    System.out.println(la.canMatch(state));
    state = la.step(state, 'o');
    //False, "wo" can't match "bannana" with distance 1
    System.out.println(la.canMatch(state));
  }
}
