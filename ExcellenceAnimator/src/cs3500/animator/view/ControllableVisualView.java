package cs3500.animator.view;

import cs3500.animator.controller.Features;
import cs3500.animator.model.hw05.IEasyAnimatorModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Represents the interactive view of an animation.
 * Visual view that is able to be manipulated through button clicks and key presses.
 */
public class ControllableVisualView extends VisualView implements IControllableAnimatorView {
  private final KeyComponent keyComponent;
  private final JButton playPause;
  private final JButton restart;
  private final JToggleButton loopButton;
  private final JButton speedUp;
  private final JButton slowDown;
  private final JButton skipForward;
  private final JButton skipBack;
  private final JLabel speedText;
  private final JLabel timeStamp;
  private boolean loop;
  private boolean playing;
  private int endTick;

  /**
   * Constructor for ControllableVisualView.
   * Adds the buttons to control animation.
   */
  public ControllableVisualView() {
    keyComponent = new KeyComponent();
    playPause = new JButton("Play");
    playPause.setFocusable(false);
    playPause.setToolTipText("Space");
    restart = new JButton("Restart");
    restart.setFocusable(false);
    restart.setToolTipText("R");
    loopButton = new JToggleButton("Loop");
    loopButton.setFocusable(false);
    loopButton.setToolTipText("L");
    speedUp = new JButton(">>");
    speedUp.setFocusable(false);
    speedUp.setToolTipText("Up Arrow");
    slowDown = new JButton("<<");
    slowDown.setFocusable(false);
    slowDown.setToolTipText("Down Arrow");
    skipForward = new JButton("2s >");
    skipForward.setFocusable(false);
    skipForward.setToolTipText("Right Arrow");
    skipBack = new JButton("< 2s");
    skipBack.setFocusable(false);
    skipBack.setToolTipText("Left Arrow");
    speedText = new JLabel();
    timeStamp = new JLabel();
  }

  @Override
  public void animate() {
    if (playing) {
      timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
          repaint();
          currentTick = (loop && currentTick > endTick)
                  ? 0 : currentTick > endTick ? currentTick : currentTick + 1;
          timeStamp.setText(currentTick / tempo + "/" + endTick / tempo + " s");
        }
      }, 1000 / tempo, 1000 / tempo);
    }
  }

  @Override
  public void setup(IEasyAnimatorModel model, long tempo) {
    playing = false;
    loop = false;
    endTick = model.getAnimationEnd();
    this.add(keyComponent);
    super.setup(model, tempo);
    frame.setMinimumSize(new Dimension(700, 0));

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    speedText.setText(tempo + " ticks/s");
    buttonPanel.add(speedText);
    buttonPanel.add(slowDown);
    buttonPanel.add(skipBack);
    buttonPanel.add(restart);
    buttonPanel.add(playPause);
    buttonPanel.add(loopButton);
    buttonPanel.add(skipForward);
    buttonPanel.add(speedUp);
    timeStamp.setText("0/" + model.getAnimationEnd() / tempo + " s");
    buttonPanel.add(timeStamp);
    Container c = frame.getContentPane();
    c.add(buttonPanel, BorderLayout.SOUTH);
  }

  @Override
  public void skipToTime(int seconds) {
    currentTick = Math.max((int) (seconds * tempo), 0);
    currentTick = Math.min(currentTick, endTick);
    timeStamp.setText(currentTick / tempo + "/" + endTick / tempo + " s");
    repaint();
  }

  @Override
  public void addFeatures(Features f) {
    this.keyComponent.addFeatures(f);
    playPause.addActionListener(evt -> f.playPause());
    restart.addActionListener(evt -> f.restart());
    loopButton.addActionListener(evt -> f.toggleLooping());
    speedUp.addActionListener(evt -> f.speedUp());
    slowDown.addActionListener(evt -> f.slowDown());
    skipForward.addActionListener(evt -> f.skipAhead(2));
    skipBack.addActionListener(evt -> f.skipBack(2));
  }

  @Override
  public void setHotKey(KeyStroke key, String featureName) {
    this.keyComponent.getInputMap().put(key, featureName);
  }

  @Override
  public void playPause() {
    if (playing) {
      timer.cancel();
      playing = false;
      playPause.setText("Play");
    } else {
      timer = new Timer();
      playing = true;
      animate();
      playPause.setText("Pause");
    }
  }

  @Override
  public void toggleLooping() {
    this.loop = !loop;
    loopButton.setSelected(loop);
  }

  @Override
  public void speedUp() {
    this.playPause();
    if (tempo < 10) {
      tempo++;
    } else {
      this.tempo = (long) (tempo * 1.2);
      tempo = Math.min(tempo, 1000);
    }
    this.playPause();
    speedText.setText(tempo + " ticks/s");
    timeStamp.setText(currentTick / tempo + "/" + endTick / tempo + " s");
  }

  @Override
  public void slowDown() {
    this.playPause();
    this.tempo = (long) (tempo * 0.8);
    if (tempo == 0) {
      tempo = 1;
    }
    this.playPause();
    speedText.setText(tempo + " ticks/s");
    timeStamp.setText(currentTick / tempo + "/" + endTick / tempo + " s");
  }

  @Override
  public int getCurrentTime() {
    return (int) (this.currentTick / tempo);
  }
}
