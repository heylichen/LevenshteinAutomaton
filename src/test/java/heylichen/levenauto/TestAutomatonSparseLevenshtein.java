package heylichen.levenauto;

import org.junit.Assert;
import org.junit.Test;

public class TestAutomatonSparseLevenshtein {

  //not really a test, just to print automaton dot
  @Test
  public void testGenDot() {
    SparseLevenshteinAutomatonCore lm = new SparseLevenshteinAutomatonCore("woof", 1);
    SparseLevenshteinAutomaton ta = new SparseLevenshteinAutomaton(lm);
    System.out.println(ta.printDot());
  }

  @Test
  public void testMatch() {
    SparseLevenshteinAutomatonCore lm = new SparseLevenshteinAutomatonCore("woof", 2);
    SparseLevenshteinAutomaton ta = new SparseLevenshteinAutomaton(lm);

    Assert.assertTrue(ta.match("xoof"));
    Assert.assertTrue(ta.match("1xoof"));
    Assert.assertFalse(ta.match("11xoof"));

    Assert.assertTrue(ta.match("oof"));
    Assert.assertTrue(ta.match("of"));
    Assert.assertFalse(ta.match("f"));
  }

  @Test
  public void testNotMatchEmpty() {
    SparseLevenshteinAutomatonCore lm = new SparseLevenshteinAutomatonCore("woof", 2);
    SparseLevenshteinAutomaton ta = new SparseLevenshteinAutomaton(lm);
    Assert.assertFalse(ta.match(null));
  }

  @Test
  public void testMatchEmpty() {
    SparseLevenshteinAutomatonCore lm = new SparseLevenshteinAutomatonCore("wo", 2);
    SparseLevenshteinAutomaton ta = new SparseLevenshteinAutomaton(lm);
    Assert.assertTrue(ta.match(null));
  }
}