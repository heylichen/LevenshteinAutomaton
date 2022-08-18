package heylichen.levenauto;

import junit.framework.TestCase;
import org.junit.Test;

public class RowLevenshteinAutomataDFATest extends TestCase {
  @Test
  public void testGenDot() {
    RowLevenshteinAutomata lm = new RowLevenshteinAutomata("woof", 1);
    RowLevenshteinAutomataDFA ta = new RowLevenshteinAutomataDFA(lm);
    System.out.println(ta.printDot());
  }
}