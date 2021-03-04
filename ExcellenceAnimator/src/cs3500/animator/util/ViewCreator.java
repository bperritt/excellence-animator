package cs3500.animator.util;

import cs3500.animator.view.ControllableVisualView;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualView;

/**
 * Creates the view based on its type.
 */
public class ViewCreator {
  /**
   * Creates the view based on given string.
   * @param view view type.
   * @return specified IAnimatorView
   */
  public static IAnimatorView create(String view) {
    switch (view) {
      case "text":
        return new TextualView();
      case "svg":
        return new SVGView();
      case "visual":
        return new VisualView();
      case "interactive":
        return new ControllableVisualView();
      default:
        throw new IllegalArgumentException("Please input a valid view type");
    }
  }
}
