package heylichen.levenauto;

import org.junit.Assert;
import org.junit.Test;

public class TestAutomatonSparseLevenshtein {

  //not really a test, just to print automaton dot
  @Test
  public void testGenDot() {
    SparseLevenshteinAutomata lm = new SparseLevenshteinAutomata("woof", 1);
    SparseLevenshteinAutomataDFA ta = new SparseLevenshteinAutomataDFA(lm);
    System.out.println(ta.printDot());
  }

  @Test
  public void testMatch() {
    SparseLevenshteinAutomata lm = new SparseLevenshteinAutomata("woof", 2);
    SparseLevenshteinAutomataDFA ta = new SparseLevenshteinAutomataDFA(lm);

    Assert.assertTrue(ta.match("xoof"));
    Assert.assertTrue(ta.match("1xoof"));
    Assert.assertFalse(ta.match("11xoof"));

    Assert.assertTrue(ta.match("oof"));
    Assert.assertTrue(ta.match("of"));
    Assert.assertFalse(ta.match("f"));
  }

  @Test
  public void testNotMatchEmpty() {
    SparseLevenshteinAutomata lm = new SparseLevenshteinAutomata("woof", 2);
    SparseLevenshteinAutomataDFA ta = new SparseLevenshteinAutomataDFA(lm);
    Assert.assertFalse(ta.match(null));
  }

  @Test
  public void testMatchEmpty() {
    SparseLevenshteinAutomata lm = new SparseLevenshteinAutomata("wo", 2);
    SparseLevenshteinAutomataDFA ta = new SparseLevenshteinAutomataDFA(lm);
    Assert.assertTrue(ta.match(null));
  }
}