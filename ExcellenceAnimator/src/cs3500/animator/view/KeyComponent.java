package cs3500.animator.view;

import cs3500.animator.controller.Features;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the JPanel in the view that handles the key events.
 * Keeps track of keys pressed and their corresponding actions.
 */
public class KeyComponent extends JPanel {
  private List<Features> featureListeners = new ArrayList<>();

  void addFeatures(Features f) {
    this.featureListeners.add(f);
  }

  /**
   * Constructor for KeyComponent.
   * Executes corresponding command to key pressed.
   */
  public KeyComponent() {
    this.getActionMap().put("playPause", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featureListeners) {
          f.playPause();
        }
      }
    });
    this.getActionMap().put("restart", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featureListeners) {
          f.restart();
        }
      }
    });
    this.getActionMap().put("enableLooping", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featureListeners) {
          f.toggleLooping();
        }
      }
    });
    this.getActionMap().put("slowDown", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featureListeners) {
          f.slowDown();
        }
      }
    });
    this.getActionMap().put("speedUp", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featureListeners) {
          f.speedUp();
        }
      }
    });
    this.getActionMap().put("skipBack", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featureListeners) {
          f.skipBack(2);
        }
      }
    });
    this.getActionMap().put("skipAhead", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (Features f : featureListeners) {
          f.skipAhead(2);
        }
      }
    });
  }
}
