package cs3500.animator.view;

import cs3500.animator.controller.Features;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Mock class for testing purposes.
 */
public class MockControllableView extends VisualView implements IControllableAnimatorView {
  Appendable ap;
  public KeyComponent keyComponent;
  public JButton playPause;
  public JButton restart;
  public JToggleButton loopButton;
  public JButton speedUp;
  public JButton slowDown;
  public JButton skipForward;
  public JButton skipBack;
  public JLabel speedText;
  public JLabel timeStamp;
  public JButton helpButton;
  public boolean playing;
  public long tempo;


  /**
   * Constructor for Mock Controllable view.
   *
   * @param ap appendable
   */
  public MockControllableView(Appendable ap) {
    this.ap = ap;
    keyComponent = new KeyComponent();
    playPause = new JButton("Play");
    playPause.setFocusable(false);
    restart = new JButton("Restart");
    restart.setFocusable(false);
    loopButton = new JToggleButton("Loop");
    loopButton.setFocusable(false);
    speedUp = new JButton(">>");
    speedUp.setFocusable(false);
    slowDown = new JButton("<<");
    slowDown.setFocusable(false);
    skipForward = new JButton("2s >");
    skipForward.setFocusable(false);
    skipBack = new JButton("< 2s");
    skipBack.setFocusable(false);
    speedText = new JLabel();
    timeStamp = new JLabel();
    helpButton = new JButton("Help");
    helpButton.setFocusable(false);
    playing = false;
    currentTick = 500;
  }

  /**
   * Tests the play/pause button.
   */
  @Override
  public void playPause() {
    if (playing) {
      playing = false;
      try {
        ap.append("Paused");
      } catch (IOException e) {
        System.err.println("playPause failed");
      }
    } else {
      playing = true;
      try {
        ap.append("Playing");
      } catch (IOException e) {
        System.err.println("playPause failed");
      }
    }
  }

  /**
   * Test the looping toggle button.
   */
  @Override
  public void toggleLooping() {
    try {
      ap.append("looping enabled");
    } catch (IOException e) {
      System.err.println("toggleLooping failed");
    }
  }

  /**
   * Test the speedUp button.
   */
  @Override
  public void speedUp() {
    try {
      ap.append("sped up");
    } catch (IOException e) {
      System.err.println("speedUp failed");
    }
  }

  /**
   * Test the slowDown button.
   */
  @Override
  public void slowDown() {
    try {
      ap.append("slowed down");
    } catch (IOException e) {
      System.err.println("slowDown failed");
    }

  }

  /**
   * Test restart button.
   *
   * @param time to skip to
   */
  @Override
  public void skipToTime(int time) {
    try {
      ap.append("skipped to time: " + time);
    } catch (IOException e) {
      System.err.println("skip to time failed");
    }

  }

  /**
   * Used for testing the buttons.
   */
  @Override
  public void addFeatures(Features f) {
    this.keyComponent.addFeatures(f);
    this.keyComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0),
            "help");
    this.keyComponent.getActionMap().put("help", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        displayHelp();
      }
    });
    playPause.addActionListener(evt -> f.playPause());
    restart.addActionListener(evt -> f.restart());
    loopButton.addActionListener(evt -> f.toggleLooping());
    speedUp.addActionListener(evt -> f.speedUp());
    slowDown.addActionListener(evt -> f.slowDown());
    skipForward.addActionListener(evt -> f.skipAhead(2));
    skipBack.addActionListener(evt -> f.skipBack(2));
    helpButton.addActionListener(evt -> this.displayHelp());
  }

  /**
   * Test the help button.
   */
  private void displayHelp() {
    try {
      ap.append("help displayed");
    } catch (IOException e) {
      System.err.println("help failed");
    }
  }

  /**
   * Used for testing buttons.
   *
   * @param key         key-press being set
   * @param featureName name of feature being set
   */
  @Override
  public void setHotKey(KeyStroke key, String featureName) {
    this.keyComponent.getInputMap().put(key, featureName);
  }

  /**
   * Not needed for testing.
   *
   * @return 0
   */
  @Override
  public int getCurrentTime() {
    return (int) (this.currentTick / tempo);
  }
}
