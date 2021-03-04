package cs3500.animator.transformation;

import cs3500.animator.shapes.IShape;

import java.util.List;

/**
 * Implementation of an instruction for the case of an animation.
 */
public class AnimationInstruction implements Instruction {
  private List<Transformation> instructions;
  private int startTick;
  private int endTick;

  /**
   * Constructions an {@code Instruction} with a list of transformations,
   * startTick, and endTick.
   * @param instructions list of transformations
   * @param startTick start tick of transformations
   * @param endTick end tick of transformations
   */
  public AnimationInstruction(List<Transformation> instructions, int startTick, int endTick) {
    if (startTick >= endTick) {
      for (Transformation t: instructions) {
        if (t.getClass() != ToggleVisibility.class) {
          throw new IllegalArgumentException("Movement cannot start after it ends");
        }
      }
    }
    this.instructions = instructions;
    this.startTick = startTick;
    this.endTick = endTick;
  }

  /**
   * Applies each transformation in a list
   * of transformations to the given shape.
   * @param s shape that the transformation should be applied to
   */
  public void apply(IShape s) {
    for (Transformation t : instructions) {
      t.update(s);
    }
  }

  /**
   * Returns the start tick of the instruction.
   * @return start tick
   */
  public int getBegin() {
    return this.startTick;
  }

  /**
   * Returns the end tick of the instruction.
   * @return end tick
   */
  public int getEnd() {
    return this.endTick;
  }
}
