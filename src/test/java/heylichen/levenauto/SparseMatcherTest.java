package heylichen.levenauto;

import heylichen.levenauto.matcher.LevenshteinDistanceMatcher;
import heylichen.levenauto.matcher.SparseLevenshteinDistanceMatcher;
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
  protected LevenshteinDistanceMatcher newMatcher() {
    return new SparseLevenshteinDistanceMatcher("woof", 2);
  }
}
