import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IInteractiveController;
import cs3500.animator.model.hw05.EasyAnimatorModel;
import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.view.KeyComponent;
import cs3500.animator.view.MockControllableView;
import org.junit.Test;

import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for the controller of the interactive view.
 */
public class ControllerTest {
  Appendable ap = new StringBuilder();
  IInteractiveController controller = new AnimationController();
  MockControllableView view = new MockControllableView(ap);
  IEasyAnimatorModel model = new EasyAnimatorModel();

  public ControllerTest() {

    controller.addView(view);
  }


  @Test
  public void testPauseButton() {
    view.playing = true;
    view.playPause.doClick();
    assertEquals("Paused", ap.toString());

  }

  @Test
  public void testPlayButton() {
    view.playPause.doClick();
    assertEquals("Playing", ap.toString());
  }

  @Test
  public void testLoopingButton() {
    view.loopButton.doClick();
    assertEquals("looping enabled", ap.toString());
  }

  @Test
  public void testRestartButton() {
    view.tempo = 50;
    view.restart.doClick();
    assertEquals("skipped to time: 0", ap.toString());
  }

  @Test
  public void testSkipForwardButton() {
    view.tempo = 50;
    view.skipForward.doClick();
    assertEquals("skipped to time: 12", ap.toString());
  }

  @Test
  public void testSkipBackButton() {
    view.tempo = 50;
    view.skipBack.doClick();
    assertEquals("skipped to time: 8", ap.toString());
  }

  @Test
  public void testPlayPauseKey() {
    controller.animateUsingModel(model, 50);
    KeyComponent kc = view.keyComponent;
    Action a = kc.getActionMap().get(kc.getInputMap().get((KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,
            0))));
    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
    });
    assertEquals("Playing", ap.toString());
  }

  @Test
  public void testLoopingKey() {
    controller.animateUsingModel(model, 50);
    KeyComponent kc = view.keyComponent;
    Action a = kc.getActionMap().get(kc.getInputMap().get((KeyStroke.getKeyStroke(KeyEvent.VK_L,
            0))));
    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
    });
    assertEquals("looping enabled", ap.toString());
  }

  @Test
  public void testRestartKey() {
    view.tempo = 50;
    controller.animateUsingModel(model, 50);
    KeyComponent kc = view.keyComponent;
    Action a = kc.getActionMap().get(kc.getInputMap().get((KeyStroke.getKeyStroke(KeyEvent.VK_R,
            0))));
    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
    });
    assertEquals("skipped to time: 0", ap.toString());
  }

  @Test
  public void testSpeedUpKey() {
    controller.animateUsingModel(model, 50);
    KeyComponent kc = view.keyComponent;
    Action a = kc.getActionMap().get(kc.getInputMap().get((KeyStroke.getKeyStroke(KeyEvent.VK_UP,
            0))));
    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
    });
    assertEquals("sped up", ap.toString());
  }

  @Test
  public void testSlowDownKey() {
    controller.animateUsingModel(model, 50);
    KeyComponent kc = view.keyComponent;
    Action a = kc.getActionMap().get(kc.getInputMap().get((KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,
            0))));
    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
    });
    assertEquals("slowed down", ap.toString());
  }

  @Test
  public void testSkipForwardKey() {
    view.tempo = 50;
    controller.animateUsingModel(model, 50);
    KeyComponent kc = view.keyComponent;
    Action a = kc.getActionMap().get(kc.getInputMap().get((KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,
            0))));
    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
    });
    assertEquals("skipped to time: 12", ap.toString());
  }

  @Test
  public void testSkipBackwardKey() {
    view.tempo = 50;
    controller.animateUsingModel(model, 50);
    KeyComponent kc = view.keyComponent;
    Action a = kc.getActionMap().get(kc.getInputMap().get((KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,
            0))));
    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
    });
    assertEquals("skipped to time: 8", ap.toString());
  }
}




