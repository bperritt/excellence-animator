package cs3500.animator.view;

import cs3500.animator.model.hw05.IEasyAnimatorModel;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the textual view of an animation.
 */
public class TextualView implements IAnimatorView {
  private long tempo;
  private String view;
  private String textualModel;

  /**
   * Renders the textual output after an animation is run.
   * @return String that represents the textual animation
   */
  protected String render() {
    String output = "";
    Scanner sc = new Scanner(textualModel);
    output += sc.nextLine();
    int count = 0;
    while (sc.hasNext()) {
      String next = sc.next();
      if (next.equals("shape")) {
        output += "\nshape " + sc.nextLine();
        count = 0;
      }
      else if (next.equals("motion")) {
        output += "\nmotion ";
        count = 0;
      } else {
        if (count == 1 || count == 9) {
          output += (double) Integer.parseInt(next) / tempo + " ";
          count++;
        } else {
          output += next + " ";
          count++;
        }
      }
    }
    return output;
  }

  @Override
  public void animate() {
    this.view = this.render();
  }

  @Override
  public void setup(IEasyAnimatorModel model, long tempo) {
    this.textualModel = model.printAnimation();
    this.tempo = tempo;
  }

  @Override
  public void output(Appendable ap) {
    try {
      ap.append(this.view);
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to write to the given appendable");
    }
  }
}
