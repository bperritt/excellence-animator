package cs3500.animator.transformation;

import cs3500.animator.shapes.IShape;

/**
 * Represents a change in color for an element in the animation.
 */
public class ColorShift implements Transformation {
  private final int endR;
  private final int endG;
  private final int endB;

  /**
   * Constructs a {@code ColorShift} object.
   * @param r      the amount of red in the final color
   * @param g      the amount of green in the final color
   * @param b      the amount of blue in the final color
   * @throws IllegalArgumentException if the times or colors are invalid
   */
  public ColorShift(int r, int g, int b) {
    if (r < 0 || g < 0 || b < 0
            || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Please use a valid color code");
    }
    this.endR = r;
    this.endG = g;
    this.endB = b;
  }

  @Override
  public void update(IShape s) {
    s.changeColor(endR, endG, endB);
  }

}
