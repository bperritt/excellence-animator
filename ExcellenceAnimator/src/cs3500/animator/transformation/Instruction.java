package cs3500.animator.transformation;

import cs3500.animator.shapes.IShape;

/**
 * Represents the transformations that must be performed on an element
 * between 2 given times.
 */
public interface Instruction {

  /**
   * Applies all transformations in the instruction to the given shape.
   * @param s shape that the transformation should be applied to
   */
  void apply(IShape s);

  /**
   * Returns the start tick of the instruction.
   * @return start tick
   */
  int getBegin();

  /**
   * Returns the end tick of the instruction.
   * @return end tick
   */
  int getEnd();
}
