package cs3500.animator.shapes;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Represents an Ellipse element in the animation.
 */
public class Ellipse extends Shape {

  /**
   * Constructs a {@code Ellipse} object.
   *
   * @param color color of the oval
   * @param x     x location of the oval
   * @param y     y location of the oval
   * @param w     width of the oval
   * @param h     height of the oval
   * @param name  name of the oval
   */
  public Ellipse(Color color, int x, int y, int w, int h, String name, boolean visible) {
    super(color, x, y, w, h, name, visible);
  }

  /**
   * Constructs a {@code Ellipse} object.
   *
   * @param name name of the oval
   */
  public Ellipse(String name) {
    super(name);
  }

  /**
   * Constructs a {@code Ellipse} object.
   */
  public Ellipse() {
    super("E");
  }

  @Override
  public String toString() {
    Color c = super.color;
    return super.x + "\t" + super.y + "\t" + this.width + "\t" + this.height
            + "\t" + c.getRed() + "\t" + c.getGreen() + "\t" + c.getBlue();
  }

  @Override
  public IShape clone() {
    return new Ellipse(this.color, this.x, this.y,
            this.width, this.height, this.name, this.visible);
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
    return this.color == ((Ellipse) o).color && this.x == ((Ellipse) o).x
            && this.y == ((Ellipse) o).y && this.width == ((Ellipse) o).width
            && this.height == ((Ellipse) o).height && this.name.equals(((Ellipse) o).name)
            && this.visible == ((Ellipse) o).visible;
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public void draw(Graphics g, int xOffset, int yOffset) {
    g.setColor(color);
    g.fillOval(x - xOffset, y - yOffset, width, height);
  }
}
