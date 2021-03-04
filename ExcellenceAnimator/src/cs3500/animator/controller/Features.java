package cs3500.animator.controller;

/**
 * Lists the features a controller expects its interactive views to be able to fulfill.
 */
public interface Features {
  /**
   * Pauses and plays the animation.
   */
  void playPause();

  /**
   * Restarts the animation.
   */
  void restart();

  /**
   * Enables looping of the animation.
   */
  void toggleLooping();

  /**
   * Speeds up the animation.
   */
  void speedUp();

  /**
   * Slows down the animation.
   */
  void slowDown();

  /**
   * Skips the animation ahead by a given number of seconds.
   *
   * @param seconds amount to skip the animation forward by.
   */
  void skipAhead(int seconds);

  /**
   * Skips the animation behind by a given number of seconds.
   *
   * @param seconds amount to skip the animation backward by.
   */
  void skipBack(int seconds);
}
