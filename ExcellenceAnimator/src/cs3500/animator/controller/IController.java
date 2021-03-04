package cs3500.animator.controller;

import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.view.IAnimatorView;

import java.io.IOException;

/**
 * Controller for non-interactive views of an animation.
 */
public interface IController {

  /**
   * Controller interface for non interactive views. Allows the addition of multiple views,
   * so multiple views can be animated at the same time. This interface accepts any kind of view,
   * but this prevents implementing controllers from having specific features.
   * @param model model to be animated
   * @param tempo tempo of animation
   */
  void animateUsingModel(IEasyAnimatorModel model, long tempo);

  /**
   * Animates the given model and returns text output of
   * animation using all views in the controller.
   * @param model model to be animated
   * @param tempo tempo of animation
   * @param ap appendable what the text output is added to
   * @throws IOException exception UnsupportedOperationException
   */
  void animateModelWithTextOutput(IEasyAnimatorModel model, long tempo, Appendable ap)
          throws IOException;

  /**
   * Adds additional view to the controller.
   * A single controller can control multiple views.
   * @param view view being added
   */
  void addView(IAnimatorView view);
}
