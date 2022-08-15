package heylichen.levenauto;

public class IndexValue {
  // stateIndex is a prefix length of string, which can be 0,1,... string.length()-1
  private final int stateIndex;
  private final int distanceValue;

  public IndexValue(int stateIndex, int distanceValue) {
    this.stateIndex = stateIndex;
    this.distanceValue = distanceValue;
  }

  @Override
  public String toString() {
    return stateIndex + " " + distanceValue;
  }

  public int getStateIndex() {
    return stateIndex;
  }

  public int getDistanceValue() {
    return distanceValue;
  }
}