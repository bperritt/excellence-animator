package cs3500.animator.view;

import cs3500.animator.model.hw05.IEasyAnimatorModel;

/**
 * Represents a view for rendering a model as an animation.
 */
public interface IAnimatorView {

  /**
   * Starts the animation.
   */
  void animate();

  /**
   * Sets up the animation.
   *
   * @param model model of the animation
   * @param tempo tempo of the animation
   */
  void setup(IEasyAnimatorModel model, long tempo);

  /**
   * Outputs the view to an appendable if it is supported by the view.
   *
   * @param a appendable
   */
  void output(Appendable a) throws UnsupportedOperationException;
}
