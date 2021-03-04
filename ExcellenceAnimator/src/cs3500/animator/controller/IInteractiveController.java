package cs3500.animator.controller;

import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.view.IControllableAnimatorView;

/**
 * Controller interface for interactive views. This interface only accepts interactive views and
 * does not support textual output due to the fact that a textual view has no way of being
 * interactive. This interface can take multiple views at a time, allowing it to control these views
 * synchronously using key or button presses.
 */
public interface IInteractiveController {
  /**
   * Animates the given model according to given tempo using the views within the controller.
   * @param model Model to be animated
   * @param tempo Tempo of animation
   */
  void animateUsingModel(IEasyAnimatorModel model, long tempo);

  /**
   * Adds an additional interactive view of animation.
   * One controller can control multiple views.
   * @param view Additional view
   */
  void addView(IControllableAnimatorView view);
}
