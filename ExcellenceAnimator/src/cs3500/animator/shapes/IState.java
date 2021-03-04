package cs3500.animator.shapes;

/**
 * Represents the state of a shape at a certain tick.
 */
public interface IState {

  /**
   * Gets the current tick of the state.
   * @return current tick
   */
  int getTick();

  /**
   * Gets the shape for this state.
   * @return shape
   */
  IShape getShape();
}
