import cs3500.animator.model.hw05.EasyAnimatorModel;
import cs3500.animator.model.hw05.IEasyAnimatorModel;
import cs3500.animator.shapes.Ellipse;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.shapes.IShape;
import cs3500.animator.transformation.ColorShift;
import cs3500.animator.transformation.Movement;
import cs3500.animator.transformation.Resize;
import cs3500.animator.transformation.Transformation;
import cs3500.animator.transformation.AnimationInstruction;
import cs3500.animator.transformation.Instruction;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import org.junit.Test;

import java.awt.Color;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the views of the animation.
 */
public class IAnimatorViewTest {
  Rectangle rec1 = new Rectangle(Color.blue, 0, 0, 3, 2, "R1", true);
  Rectangle rec2 = new Rectangle(Color.green, 10, 0, 4, 4, "R2", true);
  Rectangle rec3 = new Rectangle(Color.red, 0, 20, 10, 2, "R3", true);
  Ellipse oval1 = new Ellipse(Color.red, 1, 0, 3, 2, "O1", true);
  Color ovalColor = new Color(35, 42, 0);
  Ellipse oval2 = new Ellipse(ovalColor, 0, 10, 4, 4, "02", true);
  Ellipse oval3 = new Ellipse(Color.blue, 20, 0, 2, 2, "O3", true);
  EasyAnimatorModel emptyModel = new EasyAnimatorModel();

  @Test
  public void testTextualView() {
    ColorShift changeRed = new ColorShift(255, 0, 0);
    ColorShift changeGreen = new ColorShift(0, 255, 0);
    ColorShift changeBlue = new ColorShift(0, 0, 255);
    Movement move3x = new Movement(3, 0);
    Movement move6y = new Movement(0, 6);
    Resize resizeRec = new Resize(6, 4);
    Resize resizeOval = new Resize(6, 2);

    //rectangle1 is moved 3 spaces and changes color from ticks 1-5
    //rectangle1 moves 6 spaces from ticks 6-12
    List<Transformation> firstRec1 = new ArrayList<>();
    List<Transformation> secondRec1 = new ArrayList<>();
    firstRec1.add(changeGreen);
    firstRec1.add(move3x);
    secondRec1.add(move6y);
    Instruction firstInsRec = new AnimationInstruction(firstRec1, 1, 5);
    Instruction secondInsRec = new AnimationInstruction(secondRec1, 6, 12);
    List<Instruction> rec1Instruction = new ArrayList<>();
    rec1Instruction.add(firstInsRec);
    rec1Instruction.add(secondInsRec);

    //oval1 changes color from tick 3-5
    //oval1 is resized and moved up by 6 from ticks 7-11
    List<Transformation> firstOval1 = new ArrayList<>();
    List<Transformation> secondOval1 = new ArrayList<>();
    firstOval1.add(changeGreen);
    secondOval1.add(resizeOval);
    secondOval1.add(move6y);
    Instruction firstInsOval = new AnimationInstruction(firstOval1, 3, 5);
    Instruction secondInsOval = new AnimationInstruction(secondOval1, 7, 11);
    List<Instruction> oval1Instruction = new ArrayList<>();
    oval1Instruction.add(firstInsOval);
    oval1Instruction.add(secondInsOval);

    //move oval2 to the right by 3 from tick 1-4
    List<Transformation> firstOval2 = new ArrayList<>();
    firstOval2.add(move3x);
    Instruction firstInsOval2 = new AnimationInstruction(firstOval2, 1, 4);
    List<Instruction> oval2Instruction = new ArrayList<>();
    oval2Instruction.add(firstInsOval2);

    //add shapes to model
    List<IShape> shapeList = new ArrayList<>();
    shapeList.add(rec1);
    shapeList.add(oval1);
    shapeList.add(oval2);

    List<List<Instruction>> instructionList = new ArrayList<>();
    instructionList.add(rec1Instruction);
    instructionList.add(oval1Instruction);
    instructionList.add(oval2Instruction);
    IEasyAnimatorModel testModel =
            new EasyAnimatorModel(0, 0, 1920, 1080, shapeList, instructionList);
    IAnimatorView testEx = new TextualView();
    testEx.setup(testModel, 4);
    testEx.animate();
    Appendable output = new StringBuilder();
    testEx.output(output);
    assertEquals("canvas 0 0 1920 1080\n"
            + "shape  R1 Rectangle\n"
            + "motion R1 0.0 0 0 3 2 0 0 255 0.25 0 0 3 2 0 0 255 \n"
            + "motion R1 0.25 0 0 3 2 0 0 255 1.25 3 0 3 2 0 255 0 \n"
            + "motion R1 1.25 3 0 3 2 0 255 0 1.5 3 0 3 2 0 255 0 \n"
            + "shape  O1 Ellipse\n"
            + "motion O1 0.0 1 0 3 2 255 0 0 0.75 1 0 3 2 255 0 0 \n"
            + "motion O1 0.75 1 0 3 2 255 0 0 1.25 1 0 3 2 0 255 0 \n"
            + "motion O1 1.25 1 0 3 2 0 255 0 1.75 1 0 3 2 0 255 0 \n"
            + "shape  02 Ellipse\n"
            + "motion 02 0.0 0 10 4 4 35 42 0 0.25 0 10 4 4 35 42 0 ", output.toString());
  }


  // input mismatch exception? may be because im not using IAnimatorView
  // cant use ianimatorview because no getter
  @Test
  public void testOneShapeTextualView() {
    //add shapes to model
    List<IShape> shapeList = new ArrayList<>();
    shapeList.add(rec1);
    ColorShift changeGreen = new ColorShift(0, 255, 0);
    Movement move3x = new Movement(3, 0);
    Movement move6y = new Movement(0, 6);
    //rectangle1 is moved 3 spaces and changes color from ticks 1-5
    //rectangle1 moves 6 spaces from ticks 6-12
    List<Transformation> firstRec1 = new ArrayList<>();
    List<Transformation> secondRec1 = new ArrayList<>();
    firstRec1.add(changeGreen);
    firstRec1.add(move3x);
    secondRec1.add(move6y);
    Instruction firstInsRec = new AnimationInstruction(firstRec1, 1, 5);
    Instruction secondInsRec = new AnimationInstruction(secondRec1, 6, 12);
    List<Instruction> rec1Instruction = new ArrayList<>();
    rec1Instruction.add(firstInsRec);
    rec1Instruction.add(secondInsRec);
    List<List<Instruction>> instructionList = new ArrayList<>();
    instructionList.add(rec1Instruction);
    IEasyAnimatorModel testModel =
            new EasyAnimatorModel(0, 0, 1920, 1080, shapeList, instructionList);
    IAnimatorView test = new TextualView();
    test.setup(testModel, 4);
    test.animate();
    Appendable output = new StringBuilder();
    test.output(output);
    assertEquals("canvas 0 0 1920 1080\n"
            + "shape  R1 Rectangle\n"
            + "motion R1 0.0 0 0 3 2 0 0 255 0.25 0 0 3 2 0 0 255 \n"
            + "motion R1 0.25 0 0 3 2 0 0 255 1.25 3 0 3 2 0 255 0 \n"
            + "motion R1 1.25 3 0 3 2 0 255 0 1.5 3 0 3 2 0 255 0 ", output.toString());
  }

  // don't know what to do for assert because typeis IAnimatorView
  @Test
  public void testTextualViewFromFile() {
    String filename = "smalldemo.txt";
    FileReader reader = null;
    try {
      reader = new FileReader(filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IEasyAnimatorModel model = AnimationReader.parseFile(reader, new EasyAnimatorModel.Builder());
    IAnimatorView textual = new TextualView();
    textual.setup(model, 50);
    textual.animate();
    Appendable output = new StringBuilder();
    textual.output(output);
    assertEquals("canvas 200 70 360 360\n"
            + "shape  R Rectangle\n"
            + "motion R 0.02 200 200 50 100 255 0 0 0.2 200 200 50 100 255 0 0 \n"
            + "motion R 0.2 200 200 50 100 255 0 0 1.0 300 300 50 100 255 0 0 \n"
            + "motion R 1.0 300 300 50 100 255 0 0 1.02 300 300 50 100 255 0 0 \n"
            + "motion R 1.02 300 300 50 100 255 0 0 1.4 300 300 25 100 255 0 0 \n"
            + "motion R 1.4 300 300 25 100 255 0 0 2.0 200 200 25 100 255 0 0 \n"
            + "shape  C Ellipse\n"
            + "motion C 0.12 440 70 120 60 0 0 255 0.4 440 70 120 60 0 0 255 \n"
            + "motion C 0.4 440 70 120 60 0 0 255 1.0 440 250 120 60 0 0 255 \n"
            + "motion C 1.0 440 250 120 60 0 0 255 1.4 440 370 120 60 0 170 85 \n"
            + "motion C 1.4 440 370 120 60 0 170 85 1.6 440 370 120 60 0 255 0 \n"
            + "motion C 1.6 440 370 120 60 0 255 0 2.0 440 370 120 60 0 255 0 ", output.toString());
  }

  @Test
  public void testEmptyTextualView() {
    IAnimatorView testExEmpty = new TextualView();
    testExEmpty.setup(emptyModel, 4);
    testExEmpty.animate();
    Appendable output = new StringBuilder();
    testExEmpty.output(output);
    assertEquals("canvas 0 0 1920 1080", output.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNoShapesTextualView() {
    ColorShift changeGreen = new ColorShift(0, 255, 0);
    Movement move3x = new Movement(3, 0);
    Movement move6y = new Movement(0, 6);
    Resize resizeOval = new Resize(6, 2);

    //rectangle1 is moved 3 spaces and changes color from ticks 1-5
    //rectangle1 moves 6 spaces from ticks 6-12
    List<Transformation> firstRec1 = new ArrayList<>();
    List<Transformation> secondRec1 = new ArrayList<>();
    firstRec1.add(changeGreen);
    firstRec1.add(move3x);
    secondRec1.add(move6y);
    Instruction firstInsRec = new AnimationInstruction(firstRec1, 1, 5);
    Instruction secondInsRec = new AnimationInstruction(secondRec1, 6, 12);
    List<Instruction> rec1Instruction = new ArrayList<>();
    rec1Instruction.add(firstInsRec);
    rec1Instruction.add(secondInsRec);

    //oval1 changes color from tick 3-5
    //oval1 is resized and moved up by 6 from ticks 7-11
    List<Transformation> firstOval1 = new ArrayList<>();
    List<Transformation> secondOval1 = new ArrayList<>();
    firstOval1.add(changeGreen);
    secondOval1.add(resizeOval);
    secondOval1.add(move6y);
    Instruction firstInsOval = new AnimationInstruction(firstOval1, 3, 5);
    Instruction secondInsOval = new AnimationInstruction(secondOval1, 7, 11);
    List<Instruction> oval1Instruction = new ArrayList<>();
    oval1Instruction.add(firstInsOval);
    oval1Instruction.add(secondInsOval);

    //move oval2 to the right by 3 from tick 1-4
    List<Transformation> firstOval2 = new ArrayList<>();
    firstOval2.add(move3x);
    Instruction firstInsOval2 = new AnimationInstruction(firstOval2, 1, 4);
    List<Instruction> oval2Instruction = new ArrayList<>();
    oval2Instruction.add(firstInsOval2);
    List<IShape> shapeList = new ArrayList<>();

    List<List<Instruction>> instructionList = new ArrayList<>();
    instructionList.add(rec1Instruction);
    instructionList.add(oval1Instruction);
    instructionList.add(oval2Instruction);
    IEasyAnimatorModel testModel =
            new EasyAnimatorModel(0, 0, 1920, 1080, shapeList, instructionList);
    IAnimatorView testEx = new TextualView();
    testEx.setup(testModel, 3);
    testEx.animate();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExampleWithNoInstructionTextualView() {
    List<IShape> shapeList = new ArrayList<>();
    shapeList.add(rec1);
    shapeList.add(oval1);
    shapeList.add(oval2);
    List<List<Instruction>> instructionList = new ArrayList<>();
    IEasyAnimatorModel testModel =
            new EasyAnimatorModel(0, 0, 1920, 1080, shapeList, instructionList);
    IAnimatorView testEx = new TextualView();
    testEx.setup(testModel, 3);
    testEx.animate();
  }

  @Test
  public void testSVG() {
    ColorShift changeRed = new ColorShift(255, 0, 0);
    ColorShift changeGreen = new ColorShift(0, 255, 0);
    ColorShift changeBlue = new ColorShift(0, 0, 255);
    Movement move3x = new Movement(3, 0);
    Movement move6y = new Movement(0, 6);
    Resize resizeRec = new Resize(6, 4);
    Resize resizeOval = new Resize(6, 2);

    //rectangle1 is moved 3 spaces and changes color from ticks 1-5
    //rectangle1 moves 6 spaces from ticks 6-12
    List<Transformation> firstRec1 = new ArrayList<>();
    List<Transformation> secondRec1 = new ArrayList<>();
    firstRec1.add(changeGreen);
    firstRec1.add(move3x);
    secondRec1.add(move6y);
    Instruction firstInsRec = new AnimationInstruction(firstRec1, 1, 5);
    Instruction secondInsRec = new AnimationInstruction(secondRec1, 6, 12);
    List<Instruction> rec1Instruction = new ArrayList<>();
    rec1Instruction.add(firstInsRec);
    rec1Instruction.add(secondInsRec);

    //oval1 changes color from tick 3-5
    //oval1 is resized and moved up by 6 from ticks 7-11
    List<Transformation> firstOval1 = new ArrayList<>();
    List<Transformation> secondOval1 = new ArrayList<>();
    firstOval1.add(changeGreen);
    secondOval1.add(resizeOval);
    secondOval1.add(move6y);
    Instruction firstInsOval = new AnimationInstruction(firstOval1, 3, 5);
    Instruction secondInsOval = new AnimationInstruction(secondOval1, 7, 11);
    List<Instruction> oval1Instruction = new ArrayList<>();
    oval1Instruction.add(firstInsOval);
    oval1Instruction.add(secondInsOval);

    //move oval2 to the right by 3 from tick 1-4
    List<Transformation> firstOval2 = new ArrayList<>();
    firstOval2.add(move3x);
    Instruction firstInsOval2 = new AnimationInstruction(firstOval2, 1, 4);
    List<Instruction> oval2Instruction = new ArrayList<>();
    oval2Instruction.add(firstInsOval2);

    //add shapes to model
    List<IShape> shapeList = new ArrayList<>();
    shapeList.add(rec1);
    shapeList.add(oval1);
    shapeList.add(oval2);

    List<List<Instruction>> instructionList = new ArrayList<>();
    instructionList.add(rec1Instruction);
    instructionList.add(oval1Instruction);
    instructionList.add(oval2Instruction);
    IEasyAnimatorModel testModel =
            new EasyAnimatorModel(0, 0, 1920, 1080, shapeList, instructionList);

    IAnimatorView testEx = new SVGView();
    testEx.setup(testModel, 4);
    testEx.animate();
    StringBuilder st = new StringBuilder();
    testEx.output(st);
    assertEquals("<svg viewBox=\"0 0 1920 1080\" version=\"1.1\"\n" +
            "\txmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R1\" x=\"0\" y=\"0\" width=\"3\" height=\"2\" fill=\"rgb(0,0,255)\" " +
            "visibility=\"hidden\">\n" +
            "\t<animate attributeType=\"xml\" begin=\"-1.0ms\" dur=\"1ms\" attributeName=\"" +
            "visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"250.0ms\" dur=\"1000.0ms\" " +
            "attributeName=\"x\" from=\"0\" to=\"3\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"250.0ms\" dur=\"1000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"1500.0ms\" " +
            "attributeName=\"x\" from=\"3\" to=\"0\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"1500.0ms\" " +
            "attributeName=\"y\" from=\"0\" to=\"6\" fill=\"freeze\"/>\n" +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"O1\" cx=\"2\" cy=\"1\" rx=\"1\" ry=\"1\" fill=\"" +
            "rgb(255,0,0)\" visibility=\"hidden\">\n" +
            "\t<animate attributeType=\"xml\" begin=\"-1.0ms\" dur=\"1ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"750.0ms\" dur=\"500.0ms\" attributeName=" +
            "\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,255,0)\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1750.0ms\" dur=\"1000.0ms\" attributeName=" +
            "\"cx\" from=\"2\" to=\"3\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1750.0ms\" dur=\"1000.0ms\" attributeName=" +
            "\"cy\" from=\"1\" to=\"9\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1750.0ms\" dur=\"1000.0ms\" attributeName=" +
            "\"rx\" from=\"1\" to=\"3\" fill=\"freeze\"/>\n" +
            "</ellipse>\n" +
            "\n" +
            "<ellipse id=\"02\" cx=\"2\" cy=\"12\" rx=\"2\" ry=\"2\" fill=\"" +
            "rgb(35,42,0)\" visibility=\"hidden\">\n" +
            "\t<animate attributeType=\"xml\" begin=\"-1.0ms\" dur=\"1ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"250.0ms\" dur=\"750.0ms\" attributeName=" +
            "\"cx\" from=\"2\" to=\"5\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"250.0ms\" dur=\"750.0ms\" attributeName=" +
            "\"cy\" from=\"12\" to=\"2\" fill=\"freeze\"/>\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>", st.toString());
  }

  @Test
  public void testSimpleSVG() {
    List<IShape> shapeList = new ArrayList<>();
    shapeList.add(oval2);
    Movement move3x = new Movement(3, 0);
    List<Transformation> firstOval2 = new ArrayList<>();
    firstOval2.add(move3x);
    Instruction firstInsOval2 = new AnimationInstruction(firstOval2, 1, 4);
    List<Instruction> oval2Instruction = new ArrayList<>();
    oval2Instruction.add(firstInsOval2);
    List<List<Instruction>> instructionList = new ArrayList<>();
    instructionList.add(oval2Instruction);
    IEasyAnimatorModel testModel =
            new EasyAnimatorModel(0, 0, 1920, 1080, shapeList, instructionList);


    IAnimatorView test = new SVGView();
    test.setup(testModel, 4);
    test.animate();
    StringBuilder st = new StringBuilder();
    test.output(st);
    assertEquals("<svg viewBox=\"0 0 1920 1080\" version=\"1.1\"\n" +
            "\txmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"02\" cx=\"2\" cy=\"12\" rx=\"2\" ry=\"2\" fill=\"" +
            "rgb(35,42,0)\" visibility=\"hidden\">\n" +
            "\t<animate attributeType=\"xml\" begin=\"-1.0ms\" dur=\"1ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"250.0ms\" dur=\"750.0ms\" " +
            "attributeName=\"cx\" from=\"2\" to=\"5\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"250.0ms\" dur=\"750.0ms\" " +
            "attributeName=\"cy\" from=\"12\" to=\"2\" fill=\"freeze\"/>\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>", st.toString());
  }

  // don't know how to test because IAnimatorView does not have methods that return string
  @Test
  public void testSVGFromFile() {
    String filename = "smalldemo.txt";
    FileReader reader = null;
    try {
      reader = new FileReader(filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    IEasyAnimatorModel model = AnimationReader.parseFile(reader, new EasyAnimatorModel.Builder());
    IAnimatorView svg = new SVGView();
    svg.setup(model, 50);
    svg.animate();
    StringBuilder st = new StringBuilder();
    svg.output(st);
    assertEquals("<svg viewBox=\"200 70 360 360\" version=\"1.1\"\n" +
            "\txmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" " +
            "fill=\"rgb(255,0,0)\" visibility=\"hidden\">\n" +
            "\t<animate attributeType=\"xml\" begin=\"19.0ms\" dur=\"1ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"200.0ms\" dur=\"800.0ms\" " +
            "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"200.0ms\" dur=\"800.0ms\" " +
            "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1020.0ms\" dur=\"380.0ms\" " +
            "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1400.0ms\" dur=\"600.0ms\" " +
            "attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1400.0ms\" dur=\"600.0ms\" " +
            "attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\"/>\n" +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"" +
            "rgb(0,0,255)\" visibility=\"hidden\">\n" +
            "\t<animate attributeType=\"xml\" begin=\"119.0ms\" dur=\"1ms\" " +
            "attributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"600.0ms\" " +
            "attributeName=\"cy\" from=\"100\" to=\"310\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"400.0ms\" " +
            "attributeName=\"cy\" from=\"280\" to=\"430\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"400.0ms\" attributeName=" +
            "\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\"/>\n" +
            "\t<animate attributeType=\"xml\" begin=\"1400.0ms\" dur=\"200.0ms\" attributeName=" +
            "\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\"/>\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>", st.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNoShapesSVGView() {
    ColorShift changeGreen = new ColorShift(0, 255, 0);
    Movement move3x = new Movement(3, 0);
    Movement move6y = new Movement(0, 6);
    Resize resizeOval = new Resize(6, 2);

    //rectangle1 is moved 3 spaces and changes color from ticks 1-5
    //rectangle1 moves 6 spaces from ticks 6-12
    List<Transformation> firstRec1 = new ArrayList<>();
    List<Transformation> secondRec1 = new ArrayList<>();
    firstRec1.add(changeGreen);
    firstRec1.add(move3x);
    secondRec1.add(move6y);
    Instruction firstInsRec = new AnimationInstruction(firstRec1, 1, 5);
    Instruction secondInsRec = new AnimationInstruction(secondRec1, 6, 12);
    List<Instruction> rec1Instruction = new ArrayList<>();
    rec1Instruction.add(firstInsRec);
    rec1Instruction.add(secondInsRec);

    //oval1 changes color from tick 3-5
    //oval1 is resized and moved up by 6 from ticks 7-11
    List<Transformation> firstOval1 = new ArrayList<>();
    List<Transformation> secondOval1 = new ArrayList<>();
    firstOval1.add(changeGreen);
    secondOval1.add(resizeOval);
    secondOval1.add(move6y);
    Instruction firstInsOval = new AnimationInstruction(firstOval1, 3, 5);
    Instruction secondInsOval = new AnimationInstruction(secondOval1, 7, 11);
    List<Instruction> oval1Instruction = new ArrayList<>();
    oval1Instruction.add(firstInsOval);
    oval1Instruction.add(secondInsOval);

    //move oval2 to the right by 3 from tick 1-4
    List<Transformation> firstOval2 = new ArrayList<>();
    firstOval2.add(move3x);
    Instruction firstInsOval2 = new AnimationInstruction(firstOval2, 1, 4);
    List<Instruction> oval2Instruction = new ArrayList<>();
    oval1Instruction.add(firstInsOval2);
    List<IShape> shapeList = new ArrayList<>();

    List<List<Instruction>> instructionList = new ArrayList<>();
    instructionList.add(rec1Instruction);
    instructionList.add(oval1Instruction);
    instructionList.add(oval2Instruction);
    IEasyAnimatorModel testModel =
            new EasyAnimatorModel(0, 0, 1920, 1080, shapeList, instructionList);
    IAnimatorView testEx = new TextualView();
    testEx.setup(testModel, 3);
    testEx.animate();
    StringBuilder st = new StringBuilder();
    testEx.output(st);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExampleWithNoInstructionSVGView() {
    List<IShape> shapeList = new ArrayList<>();
    shapeList.add(rec1);
    shapeList.add(oval1);
    shapeList.add(oval2);
    List<List<Instruction>> instructionList = new ArrayList<>();
    IEasyAnimatorModel testModel =
            new EasyAnimatorModel(0, 0, 1920, 1080, shapeList, instructionList);
    IAnimatorView testEx = new TextualView();
    testEx.setup(testModel, 3);
    testEx.animate();
    StringBuilder st = new StringBuilder();
    testEx.output(st);
  }
}