package cs3500.animator.view;

import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.shapes.IState;
import cs3500.animator.shapes.IShape;
import cs3500.animator.transformation.Change;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an SVG animation.
 */
public class SVGView implements IAnimatorView {
  private long tempo;
  private String svgFormattedText;
  private List<IShape> initialState;
  private List<List<IState>> states;
  private int canvasX;
  private int canvasY;
  private int canvasHeight;
  private int canvasWidth;

  @Override
  public void animate() {
    this.svgFormattedText = createSVG();
  }

  @Override
  public void setup(IEasyAnimatorModel model, long tempo) {
    this.states = model.getState();
    this.initialState = model.getInitialState();
    this.canvasHeight = model.getCanvasHeight();
    this.canvasWidth = model.getCanvasWidth();
    this.canvasX = model.getCanvasX();
    this.canvasY = model.getCanvasY();
    this.tempo = tempo;
  }

  @Override
  public void output(Appendable a) {
    try {
      a.append(svgFormattedText);
    } catch (IOException e) {
      throw new IllegalArgumentException("Please input a valid appendable.");
    }
  }

  /**
   * Constructs the SVG string based on the model.
   * @return string of SVG representation of the animation
   */
  private String createSVG() {
    List<IShape> shapes = initialState;
    String output = "<svg viewBox=\"" + canvasX + " "
            + canvasY + " "
            + canvasWidth + " "
            + canvasHeight + "\" version=\"1.1\"\n"
            + "\txmlns=\"http://www.w3.org/2000/svg\">\n";
    for (int i = 0; i < shapes.size(); i++) {
      IShape s = shapes.get(i);
      switch (s.getClass().getSimpleName()) {
        case "Rectangle":
          output += "<rect id=\"" + s.getName() + "\" x=\"" +
                  s.getX() + "\" y=\"" + s.getY() + "\" width=\""
                  + s.getWidth() + "\" height=\"" + s.getHeight() + "\" fill=\""
                  + this.colorToText(s.getColor()) + "\" visibility=\"hidden\">\n";
          break;
        case "Ellipse":
          output += "<ellipse id=\"" + s.getName() + "\" cx=\"" +
                  (s.getX() + s.getWidth() / 2) + "\" cy=\"" + (s.getY() + s.getHeight() / 2)
                  + "\" rx=\""
                  + s.getWidth() / 2 + "\" ry=\"" + s.getHeight() / 2 + "\" fill=\""
                  + this.colorToText(s.getColor()) + "\" visibility=\"hidden\">\n";
          break;
        default:
          throw new IllegalArgumentException("Input an unrecognized Shape");
      }
      List<IState> shapeStates = states.get(i);
      int firstVisible = firstVisibleState(shapeStates);
      output += "\t<animate attributeType=\"xml\" begin=\""
              + ((shapeStates.get(firstVisible).getTick()) * (1000.0 / tempo) - 1.0)
              + "ms\" dur=\"" + 1
              + "ms\" attributeName=\"" + "visibility"
              + "\" from=\"" + "hidden" + "\" to=\""
              + "visible" + "\" fill=\"freeze\"/>\n";
      for (int j = firstVisible; j < shapeStates.size() - 1; j++) {
        IState s1 = shapeStates.get(j);
        IState s2 = shapeStates.get(j + 1);
        if (!s1.getShape().equals(s2.getShape())) {
          List<Change> changes = getChanges(s1.getShape(), s2.getShape());
          for (Change c : changes) {
            output += "\t<animate attributeType=\"xml\" begin=\""
                    + s1.getTick() * (1000.0 / tempo)
                    + "ms\" dur=\"" + (s2.getTick() - s1.getTick()) * (1000.0 / tempo)
                    + "ms\" attributeName=\"" + c.getAttribute()
                    + "\" from=\"" + c.getStartVal() + "\" to=\""
                    + c.getEndVal() + "\" fill=\"freeze\"/>\n";
          }
        }
      }
      switch (s.getClass().getSimpleName()) {
        case "Rectangle":
          output += "</rect>\n\n";
          break;
        case "Ellipse":
          output += "</ellipse>\n\n";
          break;
        default:
          throw new IllegalArgumentException("Input an unrecognized Shape");
      }
    }
    output += "</svg>";
    return output;
  }

  /**
   * Returns the index of the first state that is visible.
   * @param states states of the animation
   * @return int of first visible state
   */
  private int firstVisibleState(List<IState> states) {
    int count = 0;
    for (IState s : states) {
      if (s.getShape().getVisible()) {
        return count;
      } else {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns list of changes between 2 states of a shape.
   * @param s1 start state of shape
   * @param s2 end state of shape
   * @return list of changes in shape
   */
  private List<Change> getChanges(IShape s1, IShape s2) {
    List<Change> changes = new ArrayList<>();

    if (s1.getClass().getSimpleName().equals("Rectangle")) {
      if (s1.getX() != s2.getX()) {
        changes.add(new Change("x", Integer.toString(s1.getX()), Integer.toString(s2.getX())));
      }

      if (s1.getY() != s2.getY()) {
        changes.add(new Change("y", Integer.toString(s1.getY()), Integer.toString(s2.getY())));
      }

      if (s1.getWidth() != s2.getWidth()) {
        changes.add(new Change("width", Integer.toString(s1.getWidth()),
                Integer.toString(s2.getWidth())));
      }

      if (s1.getHeight() != s2.getHeight()) {
        changes.add(new Change("height", Integer.toString(s1.getHeight()),
                Integer.toString(s2.getHeight())));
      }

    } else {
      if (s1.getX() != s2.getX()) {
        changes.add(new Change("cx", Integer.toString(s1.getX() + s1.getWidth() / 2),
                Integer.toString(s2.getX() + s2.getWidth() / 2)));
      }

      if (s1.getY() != s2.getY()) {
        changes.add(new Change("cy", Integer.toString(s1.getY() + s1.getHeight() / 2),
                Integer.toString(s2.getY() + s2.getWidth() / 2)));
      }

      if (s1.getWidth() != s2.getWidth()) {
        changes.add(new Change("rx", Integer.toString(s1.getWidth() / 2),
                Integer.toString(s2.getWidth() / 2)));
      }
      if (s1.getHeight() != s2.getHeight()) {
        changes.add(new Change("ry", Integer.toString(s1.getHeight() / 2),
                Integer.toString(s2.getHeight() / 2)));
      }
    }

    if (s1.getColor() != s2.getColor()) {
      changes.add(new Change("fill", colorToText(s1.getColor()), colorToText(s2.getColor())));
    }
    return changes;
  }

  /**
   * Returns string of color value.
   * @param c color input
   * @return string of color formatted for SVG
   */
  private String colorToText(Color c) {
    return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
  }
}
