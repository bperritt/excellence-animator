package cs3500.animator.transformation;

/**
 * Represents the difference between one attribute
 * of a shape in the form of a string.
 * For the SVG view.
 */
public class Change {
  private String attribute;
  private String startVal;
  private String endVal;

  /**
   * Constructor for {@code Change} object.
   * @param attribute attribute of a shape
   * @param startVal of a shape
   * @param endVal of a shape
   */
  public Change(String attribute, String startVal, String endVal) {
    this.attribute = attribute;
    this.startVal = startVal;
    this.endVal = endVal;
  }

  /**
   * Returns the attribute.
   * @return attribute
   */
  public String getAttribute() {
    return attribute;
  }

  /**
   * Returns the start value.
   * @return start value
   */
  public String getStartVal() {
    return startVal;
  }

  /**
   * Returns the end value.
   * @return end value
   */
  public String getEndVal() {
    return endVal;
  }
}
