package cs3500.animator.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents any kind of shape in an animation.
 */
public interface IShape {

  /**
   * Changes the coordinates of the shape.
   * @param newX The new X coordinate
   * @param newY The new Y coordinate
   */
  void moveTo(int newX, int newY);

  /**
   * Changes the color of the element.
   * @param newR The new red value
   * @param newG The new green value
   * @param newB The new blue value
   */
  void changeColor(int newR, int newG, int newB);

  /**
   * Changes the visibility of the element.
   * @param isVisible whether or not the shape should be visible
   */
  void changeVisible(boolean isVisible);

  /**
   * Resizes the width and height of the element.
   * @param newW The new width
   * @param newH The new height
   */
  void resize(int newW, int newH);

  /**
   * Sets the x-coordinate of the shape.
   * @param x shape's x-coordinate
   */
  void setX(int x);

  /**
   * Sets y-coordinate of the shape.
   * @param y shape's y-coordinate
   */
  void setY(int y);

  /**
   * Sets visible to true if shape is visible.
   * @param visible if shape is visible or not
   */
  void setVisible(boolean visible);

  /**
   * Sets shape width.
   * @param width shape width
   */
  void setWidth(int width);

  /**
   * Sets shape height.
   * @param height shape height
   */
  void setHeight(int height);

  /**
   * Sets shape color.
   * @param c shape color
   */
  void setColor(Color c);

  /**
   * Returns true if shape is visible.
   * @return boolean if shape is visible
   */
  boolean getVisible();

  /**
   * Gets the X coordinate of the element.
   * @return x coordinate
   */
  int getX();

  /**
   * Gets the Y coordinate of the element.
   * @return y coordinate
   */
  int getY();

  /**
   * Gets the color of the element.
   * @return the color
   */
  Color getColor();

  /**
   * Gets the name of the element.
   * @return the name
   */
  String getName();

  /**
   * Gets the width of the element.
   * @return the width
   */
  int getWidth();

  /**
   * Gets the height of the element.
   * @return the height
   */
  int getHeight();

  /**
   * Produces a clone of the element for when the original shouldn't be affected.
   * @return the clone
   */
  IShape clone();

  /**
   * Determines if this element is equal to the given Object.
   * @param o Object to compare to
   * @return whether or not the two objects are equal
   */
  @Override
  boolean equals(Object o);

  /**
   * Converts the data on the element into a String format.
   * @return the textual representation of the element
   */
  @Override
  String toString();

  /**
   * Gets the hashcode of a shape.
   * @return the shape's hashcode
   */
  @Override
  int hashCode();

  /**
   * Draws the given shape on the canvas.
   * @param g         canvas to draw the shape on
   * @param xOffset   x offset of the shape's position
   * @param yOffset   y offset of the shape's position
   */
  void draw(Graphics g, int xOffset, int yOffset);
}
