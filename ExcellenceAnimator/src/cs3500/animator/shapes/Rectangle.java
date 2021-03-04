package cs3500.animator.shapes;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Represents a Rectangle element in the animation.
 */
public class Rectangle extends Shape {

  /**
   * Constructs a {@code Rectangle} object.
   * @param color color of the Rectangle
   * @param x     x location of the Rectangle
   * @param y     y location of the Rectangle
   * @param w     width of the Rectangle
   * @param h     height of the Rectangle
   * @param name  name of the Rectangle
   */
  public Rectangle(Color color, int x, int y, int w, int h, String name, boolean visible) {
    super(color, x, y, w, h, name, visible);
  }

  /**
   * Constructs a {@code Rectangle} object.
   * @param name  name of the Rectangle
   */
  public Rectangle(String name) {
    super(name);
  }

  /**
   * Constructs a {@code Rectangle} object.
   */
  public Rectangle() {
    super("R");
  }

  @Override
  public IShape clone() {
    return new Rectangle(this.color, this.x, this.y,
            this.width, this.height, this.name, this.visible);
  }

  @Override
  public String toString() {
    Color c = super.color;
    return super.x + "\t" + super.y + "\t" + this.width + "\t" + this.height
            + "\t" + c.getRed() + "\t" + c. getGreen() + "\t" + c.getBlue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (this.getClass() != o.getClass()) {
      return false;
    }
    return this.color == ((Rectangle)o).color && this.x == ((Rectangle)o).x
            && this.y == ((Rectangle)o).y && this.width == ((Rectangle)o).width
            && this.height == ((Rectangle)o).height && this.name.equals(((Rectangle)o).name)
            && this.visible == ((Rectangle)o).visible;
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public void draw(Graphics g, int xOffset, int yOffset) {
    g.setColor(color);
    g.fillRect(x - xOffset, y - yOffset, width, height);
  }
}
