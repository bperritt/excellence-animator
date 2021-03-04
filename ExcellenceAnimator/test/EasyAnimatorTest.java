//import cs3500.easyanimator.model.hw05.EasyAnimatorModel;
//import cs3500.easyanimator.shapes.Oval;
//import cs3500.easyanimator.shapes.Rectangle;
//import cs3500.easyanimator.shapes.Shape;
//import cs3500.easyanimator.transformation.ColorShift;
//import cs3500.easyanimator.transformation.Transformation;
//import cs3500.easyanimator.transformation.Movement;
//import cs3500.easyanimator.transformation.Resize;
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.List;
//import java.awt.Color;
//import static org.junit.Assert.assertEquals;
//
///**
// * Test class for the EasyAnimator.
// */
//public class EasyAnimatorTest {
//  Rectangle rec1 = new Rectangle(Color.blue, 0, 0, 3, 2, "R");
//  Oval oval1 = new Oval(Color.red, 1, 0, 3, 2, "O");
//  ColorShift changeRed = new ColorShift(255, 0, 0, 1, 5);
//  ColorShift changeGreen = new ColorShift(0, 255, 0, 5, 6);
//  EasyAnimatorModel model = new EasyAnimatorModel();
//
//  //tests that animation runs with color changes
//  @Test
//  public void testColorChange() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<Shape>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<Transformation> ovalInput = new ArrayList<>();
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    shapeList.add(rec1);
//    shapeList.add(oval1);
//    recInput.add(changeRed);
//    ovalInput.add(changeGreen);
//    totalInputs.add(recInput);
//    totalInputs.add(ovalInput);
//    model.addElements(shapeList,totalInputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(Color.red, model.getState().get(0).getColor());
//    assertEquals(Color.green, model.getState().get(1).getColor());
//  }
//
//  //tests adding shapes to the animation after it has started
//  @Test
//  public void testAddShapesLater() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<Shape>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<Transformation> ovalInput = new ArrayList<>();
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    shapeList.add(rec1);
//    shapeList.add(oval1);
//    recInput.add(changeRed);
//    ovalInput.add(changeGreen);
//    totalInputs.add(recInput);
//    totalInputs.add(ovalInput);
//    model.addElements(shapeList,totalInputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(Color.RED, model.getState().get(0).getColor());
//    assertEquals(Color.GREEN, model.getState().get(1).getColor());
//    List<Shape> newShape = new ArrayList<>();
//    Oval oval2 = new Oval(Color.blue, 5, 5, 1, 2, "O2");
//    newShape.add(oval2);
//    List<Transformation> newInput = new ArrayList<>();
//    ColorShift changeBlueShade = new ColorShift(0,150,0, 6, 7);
//    newInput.add(changeBlueShade);
//    List<List<Transformation>> moreInputs = new ArrayList<>();
//    moreInputs.add(newInput);
//    model.addElements(newShape, moreInputs);
//    model.updateAnimation();
//    Color color = new Color(0, 150, 0);
//    assertEquals(color, model.getState().get(2).getColor());
//  }
//
//  //tests an empty animation
//  @Test
//  public void testEmptyAnimation() {
//    model = new EasyAnimatorModel();
//    assertEquals(true, model.animationOver());
//
//  }
//
//  // makes sure that shapes added are actually present in animation
//  @Test
//  public void testCheckShapes() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<Shape>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<Transformation> ovalInput = new ArrayList<>();
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    shapeList.add(rec1);
//    shapeList.add(oval1);
//    recInput.add(changeRed);
//    ovalInput.add(changeGreen);
//    totalInputs.add(recInput);
//    totalInputs.add(ovalInput);
//    model.addElements(shapeList, totalInputs);
//    assertEquals(shapeList, model.getState());
//  }
//
//  // tests that moves with overlapping intervals throw exception
//  @Test (expected = IllegalArgumentException.class)
//  public void testOverlappingTicks() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<Shape>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    shapeList.add(rec1);
//    ColorShift changeBlueShade = new ColorShift(0,150,0, 2, 3);
//    recInput.add(changeRed);
//    recInput.add(changeBlueShade);
//    totalInputs.add(recInput);
//    model.addElements(shapeList, totalInputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//
//  }
//
//  // tests AnimationOver method
//  @Test
//  public void testAnimationOver() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapes = new ArrayList<>();
//    shapes.add(rec1);
//    shapes.add(oval1);
//    List<Transformation> recInput = new ArrayList<>();
//    recInput.add(changeRed);
//    List<Transformation> ovalInput = new ArrayList<>();
//    ovalInput.add(changeGreen);
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    totalInputs.add(recInput);
//    totalInputs.add(ovalInput);
//    model.addElements(shapes, totalInputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(true, model.animationOver());
//  }
//
//  //test if motions for one shape are consistent and even
//  @Test
//  public void testConsistentMovement() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<Shape>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    shapeList.add(rec1);
//    Movement moveRec = new Movement(1, 4,  4, 0);
//    recInput.add(moveRec);
//    Movement moveRec2 = new Movement(5, 6, 4, 4);
//    recInput.add(moveRec2);
//    totalInputs.add(recInput);
//    model.addElements(shapeList, totalInputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(4, model.getState().get(0).getX());
//    assertEquals(0, model.getState().get(0).getY());
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(4, model.getState().get(0).getX());
//    assertEquals(4, model.getState().get(0).getY());
//
//  }
//
//  //tests if 2 consecutive moves have matching start and end states
//  @Test
//  public void testConsecutiveMoves() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> inputs = new ArrayList<>();
//    shapeList.add(rec1);
//    Movement moveRec = new Movement(6, 7, 4, 0);
//    recInput.add(changeRed);
//    recInput.add(moveRec);
//    inputs.add(recInput);
//    model.addElements(shapeList, inputs);
//    model.updateAnimation();
//    Shape end = model.getState().get(0);
//    Shape start = model.getState().get(0);
//    assertEquals(end, start);
//    model.updateAnimation();
//  }
//
//  // tests if shapes are resized properly
//  @Test
//  public void testResize() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> inputs = new ArrayList<>();
//    shapeList.add(rec1);
//    Resize resizeRec = new Resize(1, 4, 4, 6);
//    recInput.add(resizeRec);
//    inputs.add(recInput);
//    model.addElements(shapeList, inputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(6, model.getState().get(0).getHeight());
//    assertEquals(4, model.getState().get(0).getWidth());
//
//  }
//
//  //tests that calling toString does not break the animation
//  @Test
//  public void testToStringDoesntBreakAnimation() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<Shape>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    shapeList.add(rec1);
//    Movement moveRec = new Movement(1, 4, 4, 0);
//    recInput.add(moveRec);
//    Movement moveRec2 = new Movement(5, 6, 4, 4);
//    recInput.add(moveRec2);
//    totalInputs.add(recInput);
//    model.addElements(shapeList, totalInputs);
//    assertEquals("\t\t\t\t\tstart                               end\n" +
//                    "\t\t\t------------------------------    -------------------------------\n" +
//                    "\t\t\tt\tx\ty\tw\th\tr\tg\tb\t\tt\tx\ty\tw\th\tr\tg\tb\n" +
//                    "Shape R Rectangle\n" +
//                    "motion R\t1\t0\t0\t2\t3\t0\t0\t255\t\t4 \t4\t0\t2\t3\t0\t0\t255\n" +
//                    "motion R\t5\t4\t0\t2\t3\t0\t0\t255\t\t6 \t4\t4\t2\t3\t0\t0\t255",
//            model.toString());
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(4, model.getState().get(0).getX());
//    assertEquals(0, model.getState().get(0).getY());
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(4, model.getState().get(0).getX());
//    assertEquals(4, model.getState().get(0).getY());
//  }
//
//  // tests that exception is thrown if negative color value is inputted
//  @Test (expected = IllegalArgumentException.class)
//  public void negColor() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> inputs = new ArrayList<>();
//    shapeList.add(rec1);
//    ColorShift negColor = new ColorShift(-3, 0, 0, 1, 5);
//    recInput.add(negColor);
//    inputs.add(recInput);
//    model.addElements(shapeList, inputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//  }
//
//  // tests toString for animation with overlapping moves
//  @Test
//  public void testToStringWithOverlappingMoves() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<Shape>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<Transformation> ovalInput = new ArrayList<>();
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    shapeList.add(rec1);
//    shapeList.add(oval1);
//    Movement moveRec = new Movement(1, 4, 4, 0);
//    recInput.add(changeRed);
//    recInput.add(moveRec);
//    Movement moveOval = new Movement(5, 8, 2, 0);
//    ovalInput.add(changeGreen);
//    ovalInput.add(moveOval);
//    totalInputs.add(recInput);
//    totalInputs.add(ovalInput);
//    model.addElements(shapeList,totalInputs);
//    assertEquals("\t\t\t\t\tstart                               end\n" +
//                    "\t\t\t------------------------------    -------------------------------\n" +
//                    "\t\t\tt\tx\ty\tw\th\tr\tg\tb\t\tt\tx\ty\tw\th\tr\tg\tb\n" +
//                    "Shape R Rectangle\n" +
//                    "motion R\t1\t0\t0\t2\t3\t0\t0\t255\t\t1 \t0\t0\t2\t3\t0\t0\t255\n" +
//                    "motion R\t1\t0\t0\t2\t3\t0\t0\t255\t\t4 \t4\t0\t2\t3\t192\t0\t64\n" +
//                    "motion R\t4\t4\t0\t2\t3\t192\t0\t64\t\t5 \t4\t0\t2\t3\t255\t0\t0\n" +
//                    "Shape O Oval\n" +
//                    "motion O\t5\t1\t0\t2\t3\t255\t0\t0\t\t5 \t1\t0\t2\t3\t255\t0\t0\n" +
//                    "motion O\t5\t1\t0\t2\t3\t255\t0\t0\t\t6 \t1\t0\t2\t3\t0\t255\t0\n" +
//                    "motion O\t6\t1\t0\t2\t3\t0\t255\t0\t\t8 \t2\t0\t2\t3\t0\t255\t0",
//            model.toString());
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(Color.red, model.getState().get(0).getColor());
//    assertEquals(Color.green, model.getState().get(1).getColor());
//    assertEquals(4, model.getState().get(0).getX());
//    assertEquals(2, model.getState().get(1).getX());
//
//  }
//
//  // test toString for animation with shapes added later
//  @Test
//  public void testToStringWithShapesAdded() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<Shape>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<Transformation> ovalInput = new ArrayList<>();
//    List<List<Transformation>> totalInputs = new ArrayList<>();
//    shapeList.add(rec1);
//    shapeList.add(oval1);
//    recInput.add(changeRed);
//    ovalInput.add(changeGreen);
//    totalInputs.add(recInput);
//    totalInputs.add(ovalInput);
//    model.addElements(shapeList,totalInputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    assertEquals(Color.RED, model.getState().get(0).getColor());
//    assertEquals(Color.GREEN, model.getState().get(1).getColor());
//    List<Shape> newShape = new ArrayList<>();
//    Oval oval2 = new Oval(Color.blue, 5, 5, 2, 1, "O2");
//    newShape.add(oval2);
//    List<Transformation> newInput = new ArrayList<>();
//    ColorShift changeBlueShade = new ColorShift(0,150,0, 6, 7);
//    newInput.add(changeBlueShade);
//    List<List<Transformation>> moreInputs = new ArrayList<>();
//    moreInputs.add(newInput);
//    model.addElements(newShape, moreInputs);
//    model.updateAnimation();
//    assertEquals("\t\t\t\t\tstart                               end\n" +
//            "\t\t\t------------------------------    -------------------------------\n" +
//            "\t\t\tt\tx\ty\tw\th\tr\tg\tb\t\tt\tx\ty\tw\th\tr\tg\tb\n" +
//            "Shape R Rectangle\n" +
//            "motion R\t1\t0\t0\t2\t3\t0\t0\t255\t\t5 \t0\t0\t2\t3\t255\t0\t0\n" +
//            "Shape O Oval\n" +
//            "motion O\t5\t1\t0\t2\t3\t255\t0\t0\t\t6 \t1\t0\t2\t3\t0\t255\t0\n" +
//            "Shape O2 Oval\n" +
//            "motion O2\t6\t5\t5\t1\t2\t0\t0\t255\t\t6 \t5\t5\t1\t2\t0\t0\t255\n" +
//            "motion O2\t6\t5\t5\t1\t2\t0\t0\t255\t\t7 \t5\t5\t1\t2\t0\t150\t0", model.toString());
//    Color color = new Color(0, 150, 0);
//    assertEquals(color, model.getState().get(2).getColor());
//  }
//
//  // tests that exception is thrown if negative resize is inputted
//  @Test (expected = IllegalArgumentException.class)
//  public void testNegResize() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> inputs = new ArrayList<>();
//    shapeList.add(rec1);
//    Resize negInput = new Resize(1, 3, -2, -3);
//    recInput.add(negInput);
//    inputs.add(recInput);
//    model.addElements(shapeList, inputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//
//  }
//
//  //tests removing elements at a certain index
//  @Test
//  public void testRemoveAtIndex() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<Transformation> ovalInput = new ArrayList<>();
//    List<List<Transformation>> inputs = new ArrayList<>();
//    shapeList.add(rec1);
//    shapeList.add(oval1);
//    recInput.add(changeRed);
//    ovalInput.add(changeGreen);
//    inputs.add(recInput);
//    inputs.add(ovalInput);
//    model.addElements(shapeList, inputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.removeElement(1);
//    assertEquals(1, model.getState().size());
//  }
//
//  //test invalid remove element at a certain index
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidRemove() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<Transformation> ovalInput = new ArrayList<>();
//    List<List<Transformation>> inputs = new ArrayList<>();
//    shapeList.add(rec1);
//    shapeList.add(oval1);
//    recInput.add(changeRed);
//    ovalInput.add(changeGreen);
//    inputs.add(recInput);
//    inputs.add(ovalInput);
//    model.addElements(shapeList, inputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.removeElement(3);
//  }
//
//  // tests that color inputs are valid
//  @Test (expected = IllegalArgumentException.class)
//  public void testInvalidColor() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> inputs = new ArrayList<>();
//    shapeList.add(rec1);
//    ColorShift illegalColor = new ColorShift(350, 0, 0, 1, 5);
//    recInput.add(illegalColor);
//    inputs.add(recInput);
//    model.addElements(shapeList, inputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//  }
//
//  // tests that the end tick for inputs are after the start tick
//  @Test(expected = IllegalArgumentException.class)
//  public void illegalTickInput() {
//    model = new EasyAnimatorModel();
//    List<Shape> shapeList = new ArrayList<>();
//    List<Transformation> recInput = new ArrayList<>();
//    List<List<Transformation>> inputs = new ArrayList<>();
//    shapeList.add(rec1);
//    ColorShift illegalTickColor = new ColorShift(250, 0, 0, 7, 5);
//    recInput.add(illegalTickColor);
//    inputs.add(recInput);
//    model.addElements(shapeList, inputs);
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//    model.updateAnimation();
//  }
//}
//*/
//

/**
 * Test class for the EasyAnimator.
 */
public class EasyAnimatorTest {

}