package cs3500.animator.view;

import cs3500.animator.controller.Features;

import javax.swing.KeyStroke;

/**
 * Represents the various types of visual views that can run an animation as well as
 * accept user input to influence the animation. Promises everything in the IAnimatorView as
 * well as other functionality that would allow a user to change what the view is displaying
 * during runtime through actions such as key presses or button presses.
 */
public interface IControllableAnimatorView extends IAnimatorView {

  /**
   * Pauses and plays the animation.
   */
  void playPause();

  /**
   * Enables looping of the animation.
   */
  void toggleLooping();

  /**
   * Increases the animation speed.
   */
  void speedUp();

  /**
   * Slows animation speed down.
   */
  void slowDown();

  /**
   * Skips to a certain time in the animation.
   *
   * @param time to skip to
   */
  void skipToTime(int time);

  /**
   * Specifies what features should be supported by the interactive view.
   *
   * @param f features to add
   */
  void addFeatures(Features f);

  /**
   * Sets a specific key to trigger a certain feature of the view.
   *
   * @param key         key-press being set
   * @param featureName name of feature being set
   */
  void setHotKey(KeyStroke key, String featureName);

  /**
   * Returns current time in the animation.
   *
   * @return int time
   */
  int getCurrentTime();
}
