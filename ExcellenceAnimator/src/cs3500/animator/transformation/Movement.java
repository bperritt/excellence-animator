package cs3500.animator.transformation;

import cs3500.animator.shapes.IShape;

/**
 * Represents a movement for an element in the animation.
 */
public class Movement implements Transformation {
  private final int endX;
  private final int endY;

  /**
   * Constructs a {@code Movement} object.
   * @param endX   the x coordinate of the final position
   * @param endY   the y coordinate of the final position
   * @throws IllegalArgumentException if the times are invalid
   */
  public Movement(int endX, int endY) {
    this.endX = endX;
    this.endY = endY;
  }

  @Override
  public void update(IShape s) {
    s.moveTo(endX, endY);
  }

}
