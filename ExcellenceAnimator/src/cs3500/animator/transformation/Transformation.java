package cs3500.animator.transformation;

import cs3500.animator.shapes.IShape;

/**
 * Represents some kind of change imparted on the elements within the model.
 */
public interface Transformation {

  /**
   * Updates the shape based on the transformation.
   * @param s shape being updated
   */
  void update(IShape s);
}
