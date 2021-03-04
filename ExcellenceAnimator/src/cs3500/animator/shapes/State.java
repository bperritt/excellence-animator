package cs3500.animator.shapes;

/**
 * Represents a shape within an animation at a certain tick (or a keyframe).
 */
public class State implements IState {
  private int tick;
  private IShape shape;

  /**
   * Constructs a {@code State} object.
   * @param t current tick
   * @param s shape
   */
  public State(int t, IShape s) {
    this.tick = t;
    this.shape = s;
  }

  /**
   * Gets the current tick of the state.
   * @return current tick
   */
  public int getTick() {
    return this.tick;
  }

  /**
   * Gets the shape for this state.
   * @return shape
   */
  public IShape getShape() {
    return this.shape;
  }
}
