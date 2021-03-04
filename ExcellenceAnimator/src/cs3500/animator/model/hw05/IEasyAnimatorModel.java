package cs3500.animator.model.hw05;

import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.IState;
import cs3500.animator.transformation.Instruction;

import java.util.List;

/**
 * Model for the Easy Animator: maintains the state of the animation and updates the
 * animation accordingly.
 */
public interface IEasyAnimatorModel {

  /**
   * Adds new elements and their moves to the animation.
   *
   * @param s      The list of shapes to add to the animation
   * @param inputs The list of transformations for the shapes
   * @throws IllegalArgumentException if the user tries to add an
   *                                  unequal number of shapes and inputs
   */
  void addElement(IShape s, List<Instruction> inputs)
          throws IllegalArgumentException;

  /**
   * Gets the width of the canvas.
   *
   * @return canvas width
   */
  int getCanvasWidth();

  /**
   * Gets the height of the canvas.
   *
   * @return canvas height
   */
  int getCanvasHeight();

  /**
   * Gets x-coordinate of the canvas.
   *
   * @return canvas x-coord
   */
  int getCanvasX();

  /**
   * Gets the y-coordinate of the canvas.
   *
   * @return canvas y-coord
   */
  int getCanvasY();

  /**
   * Removes the element with name {@code name} from the animation, as well as
   * the transformations associated with it. If there are multiple shapes with the same
   * name, it removes the first in the list.
   *
   * @param name the name of the element to remove from the animation
   */
  void removeElement(String name);

  /**
   * Gets the element with name {@code name} from the animation.
   * If there are multiple shapes with the same name, it gets the first in the list.
   * If there are no elements with the same name, it returns null.
   *
   * @param name the name of the element to get from the animation
   */
  List<IState> getElementStates(String name);

  /**
   * Returns all states of shapes in the animation.
   *
   * @return list of shapes
   */
  List<List<IState>> getState();

  /**
   * Gets the initial state of the animation.
   *
   * @return list of shapes in their initial state
   */
  List<IShape> getInitialState();

  /**
   * Prints the animation.
   *
   * @return textual representation of animation
   */
  String printAnimation();

  /**
   * Finds the ending tick of the animation.
   * @return  The last tick of the animation
   */
  int getAnimationEnd();
}
