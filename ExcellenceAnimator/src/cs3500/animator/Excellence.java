package cs3500.animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.BasicController;
import cs3500.animator.controller.IController;
import cs3500.animator.controller.IInteractiveController;
import cs3500.animator.model.hw05.EasyAnimatorModel;
import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.util.ViewCreator;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.IControllableAnimatorView;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Class that holds the main method.
 */
public final class Excellence {

  /**
   * Processes command line inputs and constructs the MVC accordingly.
   *
   * @param args command line arguments given by user.
   */
  public static void main(String[] args) {
    IEasyAnimatorModel model = null;
    IAnimatorView view;
    File outFile = null;
    int tickSpeed = 1;
    String typeView = null;
    PrintStream output = System.out;
    final JPanel panel = new JPanel();

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          i++;
          try {
            model = AnimationReader.parseFile(new FileReader(args[i]),
                    new EasyAnimatorModel.Builder());
          } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(panel, "Invalid or nonexistent file",
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Invalid or nonexistent file");
          }
          break;
        case "-view":
          i++;
          typeView = args[i];
          break;
        case "-out":
          i++;
          outFile = new File(args[i]);
          break;
        case "-speed":
          i++;
          try {
            tickSpeed = Integer.parseInt(args[i]);
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Invalid tick speed",
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Invalid tick speed");
          }
          break;
        default:
          JOptionPane.showMessageDialog(panel, "Invalid input file",
                  "Error", JOptionPane.ERROR_MESSAGE);
          throw new IllegalArgumentException("Invalid input file");
      }
    }
    if (model == null || typeView == null) {
      JOptionPane.showMessageDialog(panel, "Must input an input file and a view type",
              "Error", JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("Must input an input file and a view type");
    }
    try {
      if (outFile != null) {
        output = new PrintStream(outFile);
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(panel, "Could not write to file",
              "Error", JOptionPane.ERROR_MESSAGE);
      throw new IllegalArgumentException("Could not write to file");
    }
    try {
      view = ViewCreator.create(typeView);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(panel, e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
      throw e;
    }
    if (typeView.equals("interactive")) {
      IInteractiveController controller = new AnimationController();
      controller.addView((IControllableAnimatorView) view);
      controller.animateUsingModel(model, tickSpeed);
    } else {
      IController controller = new BasicController();
      controller.addView(view);
      if (typeView.equals("text") || typeView.equals("svg")) {
        try {
          controller.animateModelWithTextOutput(model, tickSpeed, output);
        } catch (IOException e) {
          JOptionPane.showMessageDialog(panel, "Could not write to file",
                  "Error", JOptionPane.ERROR_MESSAGE);
          throw new IllegalArgumentException("Could not write to file");
        }
      } else {
        controller.animateUsingModel(model, tickSpeed);
      }
    }
  }
}