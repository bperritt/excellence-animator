package cs3500.animator.shapes;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Represents a general element or a shape present in the animation.
 */
public abstract class Shape implements IShape {
  protected Color color;
  protected int x;
  protected int y;
  protected String name;
  protected int width;
  protected int height;
  protected boolean visible;

  /**
   * Constructs a {@code Shape} object.
   * @param color color of the Rectangle
   * @param x     x location of the Rectangle
   * @param y     y location of the Rectangle
   * @param w     width of the Rectangle
   * @param h     height of the Rectangle
   * @param name  name of the Rectangle
   * @throws IllegalArgumentException if the color codes or sizes are invalid
   */
  public Shape(Color color, int x, int y, int w, int h, String name, boolean visible) {
    if (w < 0 || h < 0) {
      throw new IllegalArgumentException("Cannot make a shape with dimensions smaller than 0");
    }
    if (color.getRed() < 0 || color.getRed() > 255 || color.getGreen() < 0
            || color.getGreen() > 255 || color.getBlue() < 0 || color.getBlue() > 255) {
      throw new IllegalArgumentException("Cannot make a shape with specified color codes");
    }
    this.color = color;
    this.x = x;
    this.y = y;
    this.width = w;
    this.height = h;
    this.name = name;
    this.visible = visible;
  }

  /**
   * Constructs a {@code Shape} object.
   * @param name  name of the Rectangle
   */
  public Shape(String name) {
    this.color = Color.BLACK;
    this.x = 0;
    this.y = 0;
    this.width = 1;
    this.height = 1;
    this.name = name;

  }

  /**
   * Changes the coordinates of the shape.
   * @param newX The new X coordinate
   * @param newY The new Y coordinate
   */
  public void moveTo(int newX, int newY) {
    this.x = newX;
    this.y = newY;
  }

  /**
   * Changes the color of the element.
   * @param newR The new red value
   * @param newG The new green value
   * @param newB The new blue value
   */
  public void changeColor(int newR, int newG, int newB) {
    int red = newR;
    int green = newG;
    int blue = newB;
    this.color = new Color(red, green, blue);
  }

  /**
   * Changes the color of the element.
   * @param isVisible whether or not the shape should be visible
   */
  public void changeVisible(boolean isVisible) {
    this.visible = isVisible;
  }

  /**
   * Resizes the width and height of the element.
   * @param newW The new width
   * @param newH The new height
   */
  public void resize(int newW, int newH) {
    width = newW;
    height = newH;
  }

  /**
   * Sets the x-coordinate of the shape.
   * @param x shape's x-coordinate
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Sets y-coordinate of the shape.
   * @param y shape's y-coordinate
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Sets visible to true if shape is visible.
   * @param visible if shape is visible or not
   */
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  /**
   * Sets shape width.
   * @param width shape width
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Sets shape height.
   * @param height shape height
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Sets shape color.
   * @param c shape color
   */
  public void setColor(Color c) {
    this.color = c;
  }

  /**
   * Returns true if shape is visible.
   * @return boolean if shape is visible
   */
  public boolean getVisible() {
    return this.visible;
  }

  /**
   * Gets the X coordinate of the element.
   * @return x coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the Y coordinate of the element.
   * @return y coordinate
   */
  public int getY() {
    return this.y;
  }

  /**
   * Gets the color of the element.
   * @return the color
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Gets the name of the element.
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the width of the element.
   * @return the width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the height of the element.
   * @return the height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Produces a clone of the element for when the original shouldn't be affected.
   * @return the clone
   */
  @Override
  public abstract IShape clone();

  /**
   * Determines if this element is equal to the given Object.
   * @param o Object to compare to
   * @return whether or not the two objects are equal
   */
  @Override
  public abstract boolean equals(Object o);

  /**
   * Converts the data on the element into a String format.
   * @return the textual representation of the element
   */
  @Override
  public abstract String toString();

  /**
   * Gets the hashcode of a shape.
   * @return the shape's hashcode
   */
  @Override
  public abstract int hashCode();

  /**
   * Draws the given shape on the canvas.
   * @param g         canvas to draw the shape on
   * @param xOffset   x offset of the shape's position
   * @param yOffset   y offset of the shape's position
   */
  public abstract void draw(Graphics g, int xOffset, int yOffset);
}
