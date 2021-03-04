package cs3500.animator.view;

import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.shapes.IState;
import cs3500.animator.shapes.IShape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a visual animation.
 */
public class VisualView extends JPanel implements IAnimatorView {
  protected int currentTick;
  protected long tempo;
  protected Timer timer;
  protected JFrame frame;
  protected JScrollPane scrollPane;
  protected List<List<IState>> states;
  protected int xOffset;
  protected int yOffset;

  @Override
  public void animate() {
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        repaint();
        currentTick++;
      }
    }, 1000 / tempo, 1000 / tempo);
  }

  @Override
  public void setup(IEasyAnimatorModel model, long tempo) {
    this.xOffset = model.getCanvasX();
    this.yOffset = model.getCanvasY();
    this.states = model.getState();
    orderStates();
    this.tempo = tempo;
    currentTick = 0;
    timer = new Timer();

    frame = new JFrame();
    frame.setLayout(new BorderLayout());
    scrollPane = new JScrollPane();
    scrollPane.setViewportView(this);
    this.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container c = frame.getContentPane();
    c.add(scrollPane, BorderLayout.CENTER);

    frame.setBounds(0, 0, model.getCanvasWidth(), model.getCanvasHeight());
    this.setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
    frame.setVisible(true);
  }

  /**
   * Ensures the states for each of the shapes are in the order in which they occur.
   */
  private void orderStates() {
    for (List<IState> shapeState : states) {
      shapeState.sort(Comparator.comparingInt(IState::getTick));
    }
  }

  @Override
  public void output(Appendable a) {
    throw new UnsupportedOperationException("Operation not supported by visual view");
  }

  /**
   * Creates the shapes in a visual animation.
   * @param g graphics component
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (List<IState> shapeStates : states) {
      IShape s = findState(shapeStates);
      if (s.getVisible()) {
        s.draw(g, xOffset, yOffset);
      }
    }
  }

  /**
   * Returns shapes and their various states in an animation.
   * @param shapeStates shapeStates in the animaiton
   * @return State of shape at current time
   */
  private IShape findState(List<IState> shapeStates) {
    if (currentTick >= shapeStates.get(shapeStates.size() - 1).getTick()) {
      return shapeStates.get(shapeStates.size() - 1).getShape();
    }
    for (int i = 0; i < shapeStates.size(); i++) {
      IState s = shapeStates.get(i);
      if (s.getTick() == currentTick) {
        return s.getShape();
      }
      if (currentTick < s.getTick()) {
        return inBetween(shapeStates.get(i - 1).getShape(), s.getShape(),
                shapeStates.get(i - 1).getTick(), shapeStates.get(i).getTick());
      }
    }
    return null;
  }

  /**
   * Calculates the inBetween states for a shape at an interval of ticks.
   * @param s1 shape in beginning state
   * @param s2 shape in end state
   * @param t1 beginning tick
   * @param t2 end tick
   * @return shape state between the specified ticks
   */
  private IShape inBetween(IShape s1, IShape s2, double t1, double t2) {

    IShape newShape = s1.clone();

    if (s1.equals(s2)) {
      return newShape;
    }

    if (s1.getX() != s2.getY()) {
      int newX = (int) (s1.getX() * ((t2 - currentTick) / (t2 - t1))
              + s2.getX() * ((currentTick - t1) / (t2 - t1)));
      newShape.setX(newX);
    }
    if (s1.getY() != s2.getY()) {
      newShape.setY((int) (s1.getY() * ((t2 - currentTick) / (t2 - t1))
              + s2.getY() * ((currentTick - t1) / (t2 - t1))));
    }
    if (s1.getWidth() != s2.getHeight()) {
      newShape.setWidth((int) (s1.getWidth() * ((t2 - currentTick) / (t2 - t1))
              + s2.getWidth() * ((currentTick - t1) / (t2 - t1))));
    }
    if (s1.getHeight() != s2.getHeight()) {
      newShape.setHeight((int) (s1.getHeight() * ((t2 - currentTick) / (t2 - t1))
              + s2.getHeight() * ((currentTick - t1) / (t2 - t1))));
    }
    if (s1.getColor() != s2.getColor()) {
      int r1 = s1.getColor().getRed();
      int r2 = s2.getColor().getRed();
      int g1 = s1.getColor().getGreen();
      int g2 = s2.getColor().getGreen();
      int b1 = s1.getColor().getBlue();
      int b2 = s2.getColor().getBlue();
      int newR = (int) (r1 * ((t2 - currentTick) / (t2 - t1))
              + r2 * ((currentTick - t1) / (t2 - t1)));
      int newG = (int) (g1 * ((t2 - currentTick) / (t2 - t1))
              + g2 * ((currentTick - t1) / (t2 - t1)));
      int newB = (int) (b1 * ((t2 - currentTick) / (t2 - t1))
              + b2 * ((currentTick - t1) / (t2 - t1)));
      newShape.setColor(new Color(newR, newG, newB));
    }
    return newShape;
  }
}
