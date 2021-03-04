package cs3500.animator.model.hw05;

import cs3500.animator.shapes.IState;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.State;
import cs3500.animator.transformation.Instruction;
import cs3500.animator.transformation.AnimationInstruction;
import cs3500.animator.transformation.ToggleVisibility;
import cs3500.animator.transformation.Resize;
import cs3500.animator.transformation.Movement;
import cs3500.animator.transformation.Transformation;
import cs3500.animator.transformation.ColorShift;
import cs3500.animator.util.AnimationBuilder;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

/**
 * This class represents the implementation of the EasyAnimator model.
 * One object of the model represents one animation.
 */
public final class EasyAnimatorModel implements IEasyAnimatorModel {
  private final List<List<Instruction>> moves = new ArrayList<>();
  private final List<List<IState>> states = new ArrayList<>();
  private final int canvasX;
  private final int canvasY;
  private final int canvasWidth;
  private final int canvasHeight;

  /**
   * Constructs a {@code EasyAnimatorModel} object.
   */
  public EasyAnimatorModel() {
    this.canvasX = 0;
    this.canvasY = 0;
    this.canvasWidth = 1920;
    this.canvasHeight = 1080;
  }

  /**
   * Constructs a {@code EasyAnimatorModel} object.
   * @param x             x offset of the canvas
   * @param y             y offset of the canvas
   * @param w             width of the canvas
   * @param h             height of the canvas
   * @param shapes        shapes in the animation
   * @param instructions  instructions for the shapes
   */
  public EasyAnimatorModel(int x, int y, int w, int h, List<IShape> shapes,
                           List<List<Instruction>> instructions) {
    this.canvasX = x;
    this.canvasY = y;
    this.canvasWidth = w;
    this.canvasHeight = h;
    if (shapes.size() != instructions.size()) {
      throw new IllegalArgumentException("There must be the same number of shapes as instructions");
    }
    for (int i = 0; i < shapes.size(); i++) {
      addElement(shapes.get(i), instructions.get(i));
    }
  }

  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  public int getCanvasX() {
    return this.canvasX;
  }

  public int getCanvasY() {
    return this.canvasY;
  }

  @Override
  public void addElement(IShape shape, List<Instruction> inputs) {
    this.orderByStart(inputs);
    if (this.checkOverlap(inputs)) {
      throw new IllegalArgumentException("Cannot have overlapping transformations");
    }
    moves.add(inputs);
    this.createState(shape, inputs, states.size());
  }

  /**
   * Adds a move to a shape in the animation.
   * @param index index of the shape to add the move to
   * @param move  move to add to the shape
   */
  public void addMove(int index, Instruction move) {
    if (states.get(index) == null) {
      throw new IllegalArgumentException("Cannot query empty index");
    }
    List<Instruction> shapeMoves = this.moves.get(index);
    shapeMoves.add(move);
    orderByStart(shapeMoves);
    if (!checkOverlap(shapeMoves)) {
      IShape s = states.get(index).get(0).getShape();
      states.remove(index);
      this.createState(s, shapeMoves, index);
    } else {
      shapeMoves.remove(move);
      throw new IllegalArgumentException("Cannot input overlapping transformations");
    }
  }

  private void createState(IShape s, List<Instruction> inputs, int index) {
    List<IState> shapeState = new ArrayList<>();
    shapeState.add(new State(0, s.clone()));
    int prevTick = 0;
    for (Instruction i : inputs) {
      IShape old = s.clone();
      if (prevTick != i.getBegin() && old.getVisible()) {
        shapeState.add(new State(i.getBegin(), s.clone()));
      }
      i.apply(s);
      if (old.getVisible() != s.getVisible()) {
        old.changeVisible(true);
        shapeState.add(new State(i.getBegin(), old));
      }
      shapeState.add(new State(i.getEnd(), s.clone()));
      prevTick = i.getEnd();
    }
    states.add(index, shapeState);
  }

  /**
   * Reorganizes the animation inputs.
   * Puts animation changes in order of occurrence.
   *
   * @param inputs Color, move, or resizing changes given to the animation
   */
  private void orderByStart(List<Instruction> inputs) {
    inputs.sort(Comparator.comparingInt(Instruction::getBegin));
  }

  /**
   * Checks if animation changes have overlapping start/end times.
   *
   * @param inputs Color, move, or resizing changes given to the animation
   * @return true if animation changes overlap
   */
  private boolean checkOverlap(List<Instruction> inputs) {
    for (int i = 0; i < inputs.size() - 1; i++) {
      Instruction input1 = inputs.get(i);
      for (int j = i + 1; j < inputs.size(); j++) {
        if (inputs.get(j).getBegin() < input1.getEnd()) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void removeElement(String name) {
    for (int i = 0; i < states.size(); i++) {
      List<IState> shapeState = states.get(i);
      for (IState state : shapeState) {
        if (state.getShape().getName().equals(name)) {
          this.states.remove(shapeState);
          this.moves.remove(shapeState);
          break;
        }
      }
    }
  }

  @Override
  public List<IState> getElementStates(String name) {
    for (List<IState> shapeState : states) {
      for (IState state : shapeState) {
        if (state.getShape().getName().equals(name)) {
          List<IState> clone = new ArrayList<>();
          for (IState state2 : shapeState) {
            clone.add(new State(state2.getTick(), state2.getShape().clone()));
          }
          return clone;
        }
      }
    }
    return null;
  }

  @Override
  public List<List<IState>> getState() {
    List<List<IState>> statesCopy = new ArrayList<>();
    for (List<IState> state : states) {
      List<IState> stateCopy = new ArrayList<>();
      for (IState shapeState : state) {
        stateCopy.add(new State(shapeState.getTick(), shapeState.getShape().clone()));
      }
      statesCopy.add(stateCopy);
    }
    return statesCopy;
  }

  @Override
  public List<IShape> getInitialState() {
    List<IShape> shapes = new ArrayList<>();
    for (List<IState> s : this.states) {
      shapes.add(s.get(0).getShape().clone());
    }
    return shapes;
  }

  /**
   * Prints out the transformations for an individual shape in the animation.
   *
   * @return A text output representing the transformations for the shape
   */
  public String printAnimation() {
    String output = "canvas " + this.canvasX + " " + this.canvasY
            + " " + this.canvasWidth + " " + this.canvasHeight;
    for (List<IState> shapeStates : this.states) {
      String name = shapeStates.get(0).getShape().getName();
      output += "\nshape " + name + " "
              + shapeStates.get(0).getShape().getClass().getSimpleName();
      int count = 0;
      for (IState state : shapeStates) {
        if (count >= 1 && state.getShape().getVisible()) {
          output += "\t\t" + state.getTick() + "\t" + state.getShape().toString();
        }
        if (count >= shapeStates.size() - 2) {
          break;
        }
        if (state.getShape().getVisible()) {
          output += "\nmotion " + name + " " + state.getTick() + "\t" + state.getShape().toString();
          count++;
        }
      }
    }
    return output;
  }

  @Override
  public int getAnimationEnd() {
    int endTick = 0;
    for (List<IState> s : states) {
      int compareTick = s.get(s.size() - 1).getTick();
      if (compareTick > endTick) {
        endTick = compareTick;
      }
    }
    return endTick;
  }

  /**
   * This class represents an adapter between an animation reader and an animation model.
   */
  public static final class Builder implements AnimationBuilder<IEasyAnimatorModel> {
    int xpos;
    int ypos;
    int width;
    int height;
    List<IShape> shapes = new ArrayList<>();
    Map<String, List<Instruction>> moves = new HashMap<>();

    @Override
    public IEasyAnimatorModel build() {
      List<IShape> shapeList = new ArrayList<>();
      List<List<Instruction>> moveList = new ArrayList<>();
      for (IShape s : this.shapes) {
        shapeList.add(s);
        moveList.add(moves.get(s.getName()));
      }
      return new EasyAnimatorModel(xpos, ypos, width, height, shapeList, moveList);
    }

    @Override
    public AnimationBuilder<IEasyAnimatorModel> setBounds(int x, int y, int width, int height) {
      this.xpos = x;
      this.ypos = y;
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public AnimationBuilder<IEasyAnimatorModel> declareShape(String name, String type) {
      switch (type) {
        case "rectangle":
          shapes.add(new Rectangle(name));
          break;
        case "ellipse":
          shapes.add(new Ellipse(name));
          break;
        default:
          throw new IllegalArgumentException("Please use a valid shape type.");
      }
      return this;
    }

    @Override
    public AnimationBuilder<IEasyAnimatorModel> addMotion(String name, int t1, int x1, int y1,
                                                          int w1, int h1, int r1, int g1, int b1,
                                                          int t2, int x2, int y2, int w2, int h2,
                                                          int r2, int g2, int b2) {
      List<Transformation> t = new ArrayList<>();
      if (x1 != x2 || y1 != y2) {
        t.add(new Movement(x2, y2));
      }
      if (w1 != w2 || h1 != h2) {
        t.add(new Resize(w2, h2));
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        t.add(new ColorShift(r2, g2, b2));
      }
      if (moves.get(name) == null) {
        for (IShape s : this.shapes) {
          if (s.getName().equals(name)) {
            s.setX(x1);
            s.setY(y1);
            s.setWidth(w1);
            s.setHeight(h1);
            s.setColor(new Color(r1, g1, b1));
          }
        }
        t.add(new ToggleVisibility(true));
        Instruction i = new AnimationInstruction(t, t1, t2);
        List<Instruction> newList = new ArrayList<>();
        newList.add(i);
        moves.put(name, newList);
      } else {
        Instruction i = new AnimationInstruction(t, t1, t2);
        moves.get(name).add(i);
      }
      return this;
    }
  }
}
