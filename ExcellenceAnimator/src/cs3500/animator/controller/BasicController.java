package cs3500.animator.controller;

import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.view.IAnimatorView;

import javax.swing.SwingUtilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for a view that cannot be interacted with once it is run.
 */
public class BasicController implements IController {
  private final List<IAnimatorView> views = new ArrayList<>();

  /**
   * Controller interface for non interactive views. Allows the addition of multiple views,
   * so multiple views can be animated at the same time. This interface accepts any kind of view,
   * but this prevents implementing controllers from having specific features.
   * @param model model to be animated
   * @param tempo tempo of animation
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
   * Animates the given model and returns text output of
   * animation using all views in the controller.
   * @param model model to be animated
   * @param tempo tempo of animation
   * @param ap appendable what the text output is added to
   * @throws IOException UnsupportedOperationException
   */
  public void animateModelWithTextOutput(IEasyAnimatorModel model, long tempo, Appendable ap)
          throws IOException {
    for (IAnimatorView view : views) {
      view.setup(model, tempo);
      view.animate();
      try {
        view.output(ap);
      } catch (UnsupportedOperationException e) {
        ap.append(e.getMessage());
      }
    }
  }

  /**
   * Adds additional view to the controller.
   * A single controller can control multiple views.
   * @param view view being added
   */
  @Override
  public void addView(IAnimatorView view) {
    this.views.add(view);
  }
}
