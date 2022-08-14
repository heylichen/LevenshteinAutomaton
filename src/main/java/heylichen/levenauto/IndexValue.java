package heylichen.levenauto;

public class IndexValue {
  // stateIndex is a prefix length of this.string, which can be 0,1,... string.length()-1
  private int stateIndex;
  private int distanceValue;

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

  public void setStateIndex(int stateIndex) {
    this.stateIndex = stateIndex;
  }

  public int getDistanceValue() {
    return distanceValue;
  }

  public void setDistanceValue(int distanceValue) {
    this.distanceValue = distanceValue;
  }
}