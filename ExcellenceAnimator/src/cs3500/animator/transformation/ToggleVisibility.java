package cs3500.animator.transformation;

import cs3500.animator.shapes.IShape;

/**
 * Represents a change in visibility for an element in an animation.
 */
public class ToggleVisibility implements Transformation {
  private boolean visibility;

  /**
   * Constructs a {@code ToggleVisibility} object.
   * @param visibility boolean if shape is visible
   */
  public ToggleVisibility(boolean visibility) {
    this.visibility = visibility;
  }

  @Override
  public void update(IShape s) {
    s.changeVisible(this.visibility);
  }

}
