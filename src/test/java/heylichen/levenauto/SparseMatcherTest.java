package heylichen.levenauto;

import heylichen.levenauto.encapsulated.LevenshteinAutomata;
import heylichen.levenauto.encapsulated.SparseLevenshteinAutomata;
import org.junit.Assert;
import org.junit.Test;

public class SparseMatcherTest extends BaseLevenshteinDistanceMatcherTest {

  @Test
  public void testMatch() {
    Assert.assertTrue(match("xoof"));
    Assert.assertTrue(match("1xoof"));
    Assert.assertFalse(match("11xoof"));

    Assert.assertTrue(match("oof"));
    Assert.assertTrue(match("of"));
    Assert.assertFalse(match("f"));
  }

  @Override
  protected LevenshteinAutomata newMatcher() {
    return new SparseLevenshteinAutomata("woof", 2);
  }
}
