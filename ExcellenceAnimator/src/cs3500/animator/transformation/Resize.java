package cs3500.animator.transformation;

import cs3500.animator.shapes.IShape;

/**
 * Represents the resizing of an element in the animation.
 */
public class Resize implements Transformation {
  private final int endSizeX;
  private final int endSizeY;

  /**
   * Constructs a {@code Resize} object.
   * @param esx   the final width of the element
   * @param esy   the final height of the element
   * @throws IllegalArgumentException if the times or final sizes are invalid
   */
  public Resize(int esx, int esy) {
    if (esx < 0 || esy < 0) {
      throw new IllegalArgumentException("Cannot resize a shape smaller than 0");
    }
    this.endSizeX = esx;
    this.endSizeY = esy;
  }

  @Override
  public void update(IShape s) {
    s.resize(endSizeX, endSizeY);
  }

}

