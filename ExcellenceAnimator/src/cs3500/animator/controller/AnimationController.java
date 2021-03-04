package cs3500.animator.controller;

import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.IControllableAnimatorView;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the animation controller
 * for an interactive view.
 */
public class AnimationController implements Features, IInteractiveController {
  private final List<IControllableAnimatorView> views = new ArrayList<>();

  /**
   * Animates the given model according to given tempo using the views within the controller.
   * @param model Model to be animated
   * @param tempo Tempo of animation
   */
  public void animateUsingModel(IEasyAnimatorModel model, long tempo) {
    for (IAnimatorView view : views) {
      SwingUtilities.invokeLater(() -> {
        view.setup(model, tempo);
        view.animate();
      });
    }
  }

  /**
   * Allows the animation to be paused or played.
   */
  @Override
  public void playPause() {
    for (IControllableAnimatorView view : views) {
      view.playPause();
    }
  }

  /**
   * Allows the animation to be restarted.
   */
  @Override
  public void restart() {
    for (IControllableAnimatorView view : views) {
      view.skipToTime(0);
    }
  }

  /**
   * Enables looping in the interactive animation view.
   */
  @Override
  public void toggleLooping() {
    for (IControllableAnimatorView view : views) {
      view.toggleLooping();
    }
  }

  /**
   * Speeds up animation in interactive view.
   */
  @Override
  public void speedUp() {
    for (IControllableAnimatorView view : views) {
      view.speedUp();
    }
  }

  /**
   * Slows down animation in interactive view.
   */
  @Override
  public void slowDown() {
    for (IControllableAnimatorView view : views) {
      view.slowDown();
    }
  }

  /**
   * Skips the animation ahead in interactive view.
   *
   * @param seconds where the animation should skip to
   */
  @Override
  public void skipAhead(int seconds) {
    for (IControllableAnimatorView view : views) {
      view.skipToTime(view.getCurrentTime() + seconds);
    }
  }

  /**
   * Skips the animation back in interactive view.
   *
   * @param seconds where the animation should skip back to
   */
  @Override
  public void skipBack(int seconds) {
    for (IControllableAnimatorView view : views) {
      view.skipToTime(view.getCurrentTime() - seconds);
    }
  }

  /**
   * Adds an additional interactive view of animation.
   * One controller can control multiple views.
   * @param v Additional view
   */
  public void addView(IControllableAnimatorView v) {
    this.views.add(v);
    v.addFeatures(this);

    v.setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "playPause");
    v.setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "restart");
    v.setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "enableLooping");
    v.setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "slowDown");
    v.setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "speedUp");
    v.setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "skipBack");
    v.setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "skipAhead");
  }
}
