package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**e
 * A variety of tests for the Matrix class.
 *
 * @author Samuel A. Rebelsky
 */
class TestMatrix {
  /**
   * Make sure that we can create and change 1x1 matrices.
   */
  @Test
  public void testOneByOne() {
    Integer five = Integer.valueOf(5);
    Integer six = Integer.valueOf(6);

    Matrix<Integer> oneByOneA = new MatrixV0<Integer>(1, 1);
    assertMatrixEquals(new Integer[][] {{null}}, oneByOneA,
        "one-by-one of null");
    oneByOneA.set(0, 0, five);
    assertMatrixEquals(new Integer[][] {{five}}, oneByOneA,
        "after setting one-by-one to five");

    Matrix<String> oneByOneB = new MatrixV0<String>(1, 1);
    assertMatrixEquals(new String[][] {{null}}, oneByOneB,
        "one-by-one of null");
    oneByOneB.set(0, 0, "hi");
    assertMatrixEquals(new String[][] {{"hi"}}, oneByOneB,
        "after setting one-by-one to 'hi'");

    Matrix<Integer> oneByOneC = new MatrixV0<Integer>(1, 1, five);
    assertMatrixEquals(new Integer[][] {{five}}, oneByOneC,
        "one-by-one of 5");
    oneByOneC.set(0, 0, six);
    assertMatrixEquals(new Integer[][] {{six}}, oneByOneC,
        "after setting one-by-one to six");

    Matrix<String> oneByOneD = new MatrixV0<String>(1, 1, " ");
    assertMatrixEquals(new String[][] {{" "}}, oneByOneD,
        "one-by-one of space");
    oneByOneD.set(0, 0, "");
    assertMatrixEquals(new String[][] {{""}}, oneByOneD,
        "after setting one-by-one to empty string");
  } // testOneByOne()

  /**
   * Make sure that we can build and change Nx1 matrices.
   */
  @Test
  public void testHorizontal() throws ArraySizeException {
    Integer i0 = Integer.valueOf(0);
    Integer i1 = Integer.valueOf(1);
    Integer i2 = Integer.valueOf(2);
    Integer i3 = Integer.valueOf(3);
    Integer i4 = Integer.valueOf(4);
    Integer i5 = Integer.valueOf(5);

    Matrix<Integer> horizA = new MatrixV0<Integer>(5, 1);
    assertMatrixEquals(new Integer[][] {{null, null, null, null, null}}, horizA,
        "R: 5x1 matrix of null");
    horizA.set(0, 0, i0);
    horizA.set(0, 1, i1);
    horizA.set(0, 2, i2);
    horizA.set(0, 3, i3);
    horizA.set(0, 4, i4);
    assertMatrixEquals(new Integer[][] {{i0, i1, i2, i3, i4}}, horizA,
        "M: 5x1 matrix of null after setting values");
    horizA.deleteCol(2);
    assertMatrixEquals(new Integer[][] {{i0, i1, i3, i4}}, horizA,
        "M: delete column 2 / 5x1 matrix now 4x1 matrix");
    horizA.deleteCol(0);
    assertMatrixEquals(new Integer[][] {{i1, i3, i4}}, horizA,
        "M: delete first column / 4x1 matrix now 3x1 matrix");
    horizA.deleteCol(2);
    assertMatrixEquals(new Integer[][] {{i1, i3}}, horizA,
        "M: delete last column / 3x1 matrix now 2x1 matrix");
    horizA.insertCol(0);
    assertMatrixEquals(new Integer[][] {{null, i1, i3}}, horizA,
        "M: insert first column / 2x1 matrix now 3x1 matrix");
    horizA.insertCol(3);
    assertMatrixEquals(new Integer[][] {{null, i1, i3, null}}, horizA,
        "M: insert last column / 3x1 matrix now 4x1 matrix");
    horizA.set(0, 3, i0);
    assertMatrixEquals(new Integer[][] {{null, i1, i3, i0}}, horizA,
        "M: set last column");
    horizA.insertCol(4);
    assertMatrixEquals(new Integer[][] {{null, i1, i3, i0, null}}, horizA,
        "M: insert last column again / 4x1 matrix now 5x1 matrix");
    horizA.deleteCol(1);
    assertMatrixEquals(new Integer[][] {{null, i3, i0, null}}, horizA,
        "M: delete interior column / 5x1 matrix now 4x1 matrix");
    horizA.insertCol(0, new Integer[] {i0});
    assertMatrixEquals(new Integer[][] {{i0, null, i3, i0, null}}, horizA,
        "M: insert first column / 4x1 matrix now 5x1 matrix");
    horizA.insertCol(5, new Integer[] {i5});
    assertMatrixEquals(new Integer[][] {{i0, null, i3, i0, null, i5}}, horizA,
        "M: insert last column / 5x1 matrix now 6x1 matrix");

    Matrix<Integer> horizB = new MatrixV0<Integer>(3, 1, i5);
    assertMatrixEquals(new Integer[][] {{i5, i5, i5}}, horizB,
        "R: 3x1 matrix of 5");
    horizB.set(0, 0, i3);
    horizB.set(0, 1, i2);
    horizB.set(0, 2, i1);
    assertMatrixEquals(new Integer[][] {{i3, i2, i1}}, horizB,
        "M: 3x1 matrix after updating");
    horizB.insertCol(0);
    assertMatrixEquals(new Integer[][] {{i5, i3, i2, i1}}, horizB,
        "M: insert first column / 3x1 matrix now 4x1");
    horizB.insertCol(4);
    assertMatrixEquals(new Integer[][] {{i5, i3, i2, i1, i5}}, horizB,
        "M: insert last column / 4x1 matrix now 5x1");
    horizB.insertCol(1);
    assertMatrixEquals(new Integer[][] {{i5, i5, i3, i2, i1, i5}}, horizB,
        "M: insert column one / 5x1 matrix now 6x1");
    horizB.insertCol(3);
    assertMatrixEquals(new Integer[][] {{i5, i5, i3, i5, i2, i1, i5}}, horizB,
        "M: insert column three / 6x1 matrix now 7x1");
    horizB.deleteCol(6);
    assertMatrixEquals(new Integer[][] {{i5, i5, i3, i5, i2, i1}}, horizB,
        "M: delete last column / 7x1 matrix now 6x1");
    horizB.deleteCol(0);
    assertMatrixEquals(new Integer[][] {{i5, i3, i5, i2, i1}}, horizB,
        "M: delete first column / 6x1 matrix now 5x1");
    horizB.insertCol(0, new Integer[] {i4});
    assertMatrixEquals(new Integer[][] {{i4, i5, i3, i5, i2, i1}}, horizB,
        "M: insert first column / 5x1 matrix now 6x1");
    horizB.insertCol(6, new Integer[] {i0});
    assertMatrixEquals(new Integer[][] {{i4, i5, i3, i5, i2, i1, i0}}, horizB,
        "M: insert last column / 6x1 matrix now 7x1");
  } // testHorizontal()

  /**
   * Make sure that we can build and change 1xM matrices.
   */
  @Test
  public void testVertical() throws ArraySizeException {
    String s0 = "zero";
    String s1 = "one";
    String s2 = "two";
    String s3 = "three";
    String s4 = "four";
    String s5 = "five";

    Matrix<String> vertA = new MatrixV0<String>(1, 5);
    assertMatrixEquals(new String[][] {{null}, {null}, {null}, {null}, {null}},
        vertA,
        "R: 1x5 matrix of null");
    vertA.set(0, 0, s0);
    vertA.set(1, 0, s1);
    vertA.set(2, 0, s2);
    vertA.set(3, 0, s3);
    vertA.set(4, 0, s4);
    assertMatrixEquals(new String[][] {{s0}, {s1}, {s2}, {s3}, {s4}}, vertA,
        "M: 5x1 matrix of null after setting values");
    vertA.deleteRow(2);
    assertMatrixEquals(new String[][] {{s0}, {s1}, {s3}, {s4}}, vertA,
        "M: delete column 2 / 1x5 matrix now 1x4 matrix");
    vertA.deleteRow(0);
    assertMatrixEquals(new String[][] {{s1}, {s3}, {s4}}, vertA,
        "M: delete first column / 1x4 matrix now 1x3 matrix");
    vertA.deleteRow(2);
    assertMatrixEquals(new String[][] {{s1}, {s3}}, vertA,
        "M: delete last column / 1x3 matrix now 1x2 matrix");
    vertA.insertRow(0);
    assertMatrixEquals(new String[][] {{null}, {s1}, {s3}}, vertA,
        "M: insert first column / 1x2 matrix now 1x3 matrix");
    vertA.insertRow(3);
    assertMatrixEquals(new String[][] {{null}, {s1}, {s3}, {null}}, vertA,
        "M: insert last column / 1x3 matrix now 1x4 matrix");
    vertA.set(3, 0, s0);
    assertMatrixEquals(new String[][] {{null}, {s1}, {s3}, {s0}}, vertA,
        "M: set last column");
    vertA.insertRow(4);
    assertMatrixEquals(new String[][] {{null}, {s1}, {s3}, {s0}, {null}}, vertA,
        "M: insert last column again / 1x4 matrix now 1x5 matrix");
    vertA.deleteRow(1);
    assertMatrixEquals(new String[][] {{null}, {s3}, {s0}, {null}}, vertA,
        "M: delete interior column / 1x5 matrix now 1x4 matrix");
    vertA.insertRow(0, new String[] {s0});
    assertMatrixEquals(new String[][] {{s0}, {null}, {s3}, {s0}, {null}}, vertA,
        "M: insert first column / 1x4 matrix now 1x5 matrix");
    vertA.insertRow(5, new String[] {s5});
    assertMatrixEquals(new String[][] {{s0}, {null}, {s3}, {s0}, {null}, {s5}},
        vertA,
        "M: insert last column / 1x5 matrix now 1x6 matrix");

    Matrix<String> vertB = new MatrixV0<String>(1, 3, s5);
    assertMatrixEquals(new String[][] {{s5}, {s5}, {s5}}, vertB,
         "R: 1x3 matrix of 5");
    vertB.set(0, 0, s3);
    vertB.set(1, 0, s2);
    vertB.set(2, 0, s1);
    assertMatrixEquals(new String[][] {{s3}, {s2}, {s1}}, vertB,
        "M: 1x3 matrix after updating");
    vertB.insertRow(0);
    assertMatrixEquals(new String[][] {{s5}, {s3}, {s2}, {s1}}, vertB,
        "M: insert first column / 1x3 matrix now 1x4");
    vertB.insertRow(4);
    assertMatrixEquals(new String[][] {{s5}, {s3}, {s2}, {s1}, {s5}}, vertB,
        "M: insert last column / 1x4 matrix now 1x5");
    vertB.insertRow(1);
    assertMatrixEquals(new String[][] {{s5}, {s5}, {s3}, {s2}, {s1}, {s5}},
        vertB,
        "M: insert column one / 1x5 matrix now 1x6");
    vertB.insertRow(3);
    assertMatrixEquals(new String[][] {{s5}, {s5}, {s3}, {s5}, {s2}, {s1}, {s5}},
        vertB,
        "M: insert column three / 1x6 matrix now 1x7");
    vertB.deleteRow(6);
    assertMatrixEquals(new String[][] {{s5}, {s5}, {s3}, {s5}, {s2}, {s1}},
        vertB,
        "M: delete last column / 1x7 matrix now 1x6");
    vertB.deleteRow(0);
    assertMatrixEquals(new String[][] {{s5}, {s3}, {s5}, {s2}, {s1}}, vertB,
        "M: delete first column / 1x6 matrix now 1x5");
    vertB.insertRow(0, new String[] {s4});
    assertMatrixEquals(new String[][] {{s4}, {s5}, {s3}, {s5}, {s2}, {s1}},
        vertB,
        "M: insert first column / 1x5 matrix now 1x6");
    vertB.insertRow(6, new String[] {s0});
    assertMatrixEquals(new String[][] {{s4}, {s5}, {s3}, {s5}, {s2}, {s1}, {s0}},
        vertB,
         "M: insert last column / 1x6 matrix now 1x7");
  } // testVertical()

  /**
   * Test for exceptions in set.
   */
  @Test
  public void testSetException() {
    Matrix<String> matrix = new MatrixV0(7, 3);
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.set(-1, 0, "value");},
        "E: set with negative row");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.set(10, 1, "value");},
        "E: set with much too big row");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.set(3, 2, "value");},
        "E: set with slightly too big row");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.set(0, -4, "value");},
        "E: set with negative column");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.set(0, 14, "value");},
        "E: set with much too big column");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.set(1, 7, "value");},
        "E: set with slightly too big column");
  } // testSetException

  /**
   * Test for exceptions in get.
   */
  @Test
  public void testGetException() {
    Matrix<String> matrix = new MatrixV0(3, 6);
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.get(-1, 0);},
        "E: get with negative row");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.get(10, 1);},
        "E: get with much too big row");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.get(6, 2);},
        "E: get with slightly too big row");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.get(0, -4);},
        "E: get with negative column");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.get(2, 14);},
        "E: get with much too big column");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.get(0, 3);},
        "E: get with slightly too big column");
  } // testGetException

  /**
   * Exception in insertRow.
   */
  @Test
  public void testInsertRowException() {
    Matrix<Integer> matrix = new MatrixV0<Integer>(5, 3);
    Integer four = Integer.valueOf(4);

    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.insertRow(-1);},
        "E: insert negative row");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.insertRow(4);},
        "E: insert first invalid row");

    assertThrows(IndexOutOfBoundsException.class,
        () ->
            {matrix.insertRow(-1,
                new Integer[] {four, four, four, four, four});},
        "E: insert negative row");
    assertThrows(IndexOutOfBoundsException.class,
        () ->
            {matrix.insertRow(4,
                new Integer[] {four, four, four, four, four});},
        "E: insert first invalid row");

    assertThrows(ArraySizeException.class,
        () ->
            {matrix.insertRow(0,
                new Integer[] {four, four, four, four});},
        "E: insert too-small row");
    assertThrows(ArraySizeException.class,
        () ->
            {matrix.insertRow(0,
                new Integer[] {four, four, four, four, four, four});},
        "E: insert too-large row");
  } // testInsertRowException()

  /**
   * Exception in insertCol.
   */
  @Test
  public void testInsertColException() {
    Matrix<String> matrix = new MatrixV0<String>(5, 3);

    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.insertCol(-1);},
        "E: insert negative col");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.insertCol(6);},
        "E: insert first invalid column");

    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.insertCol(-1, new String[] {"a", "b", "c"});},
        "E: insert negative col");
    assertThrows(IndexOutOfBoundsException.class,
        () -> {matrix.insertCol(6, new String[] {"a", "b", "c"});},
        "E: insert first invalid column");

    assertThrows(ArraySizeException.class,
        () -> {matrix.insertCol(0, new String[] {"a"});},
       "E: insert too-small column");
    assertThrows(ArraySizeException.class,
        () -> {matrix.insertCol(0, new String[] {"a", "a", "a", "a"});},
       "E: insert too-large column");
  } // testInsertRowException();

  /**
   * Some assorted tests.
   */
  @Test
  void testAssorted() {
    Matrix<String> strings = new MatrixV0(4, 2, " ");
    assertMatrixEquals(
        new String[][]
            {{" ", " ", " ", " "},
             {" ", " ", " ", " "}},
        strings,
        "M: original matrix");

    strings.fillRegion(0, 0, 2, 4, "a");
    assertMatrixEquals(
        new String[][]
            {{"a", "a", "a", "a"},
             {"a", "a", "a", "a"}},
        strings,
        "E: after filling the whole matrix");

    strings.insertRow(0);
    assertMatrixEquals(
        new String[][]
            {{" ", " ", " ", " "},
             {"a", "a", "a", "a"},
             {"a", "a", "a", "a"}},
        strings,
        "M: after inserting row 0");

    strings.insertRow(3);
    assertMatrixEquals(
        new String[][]
            {{" ", " ", " ", " "},
             {"a", "a", "a", "a"},
             {"a", "a", "a", "a"},
             {" ", " ", " ", " "}},
        strings,
        "M: after inserting last row");

    strings.fillRegion(0, 0, 4, 2, "b");
    assertMatrixEquals(
        new String[][]
            {{"b", "b", " ", " "},
             {"b", "b", "a", "a"},
             {"b", "b", "a", "a"},
             {"b", "b", " ", " "}},
        strings,
        "M: After filling the first two columns");

    strings.insertCol(4);
    assertMatrixEquals(
        new String[][]
            {{"b", "b", " ", " ", " "},
             {"b", "b", "a", "a", " "},
             {"b", "b", "a", "a", " "},
             {"b", "b", " ", " ", " "}},
        strings,
        "M: After inserting the last column");

    strings.fillLine(0, 0, 1, 1, 4, 4, "c");
    assertMatrixEquals(
        new String[][]
            {{"c", "b", " ", " ", " "},
             {"b", "c", "a", "a", " "},
             {"b", "b", "c", "a", " "},
             {"b", "b", " ", "c", " "}},
        strings,
        "E: After inserting diagonal line");

    strings.deleteCol(1);
    assertMatrixEquals(
        new String[][]
            {{"c", " ", " ", " "},
             {"b", "a", "a", " "},
             {"b", "c", "a", " "},
             {"b", " ", "c", " "}},
        strings,
        "E: After deleting column 1");
  } // testAssorted()

  /**
   * Some fun with equality.
   */
  @Test
  public void testEquals() throws ArraySizeException {
    // Build five equivalent matrices in different ways.
    Matrix<String> matrix0 = new MatrixV0<String>(4, 3, "X");

    Matrix<String> matrix1 = new MatrixV0<String>(3, 3, "X");
    matrix1.insertCol(1);

    Matrix<String> matrix2 = new MatrixV0<String>(4, 3);
    matrix2.fillRegion(0, 0, 3, 4, "EX".substring(1));

    Matrix<String> matrix3 = new MatrixV0<String>(4, 2, new String("X"));
    matrix3.insertRow(0, new String[] {"X", "X", "X", "X"});

    Matrix<String> matrix4 = new MatrixV0<String>(5, 4, "X" + "");
    matrix4.deleteRow(0);
    matrix4.deleteCol(1);

    Object[] matrices = 
        new Object[] { matrix0, matrix1, matrix2, matrix3, matrix4 };

    // Check that they are all equal
    for (int i = 0; i < matrices.length; i++) {
      for (int j = 0; j < matrices.length; j++) {
        try {
          assertTrue(matrices[i].equals(matrices[j]),
              String.format("E: matrices[%d].equals(matrices[%d])", i, j));
        } catch (ArrayIndexOutOfBoundsException e) {
          fail(String.format("E: matrices[%d].equals(matrices[%d]): %s", 
              i, j, e.getMessage()));
        } // try/catch
      } // for j
    } // for i

    // Mutate all of the matrices a little bit, but in the same way.
    matrix0.set(0, 0, " ");
    matrix0.set(1, 1, " ");
    matrix0.set(2, 2, " ");
    matrix1.fillLine(0, 0, 1, 1, 3, 3, " ");
    matrix2.fillLine(0, 0, 1, 1, 2, 2, "  ".substring(1));
    matrix2.set(2, 2, " ");
    matrix3.set(0, 0, "X ".substring(1));
    matrix3.fillLine(1, 1, 1, 1, 3, 3, "X ".substring(1));
    matrix4.fillLine(0, 0, 1, 1, 1, 1, " ");
    matrix4.fillLine(1, 1, 1, 1, 2, 2, " ");
    matrix4.fillLine(2, 2, 1, 1, 3, 3, " ");

    // Check that they are all equal
    for (int i = 0; i < matrices.length; i++) {
      for (int j = 0; j < matrices.length; j++) {
        try {
          assertTrue(matrices[i].equals(matrices[j]),
              String.format("E: new matrices[%d].equals(matrices[%d])", i, j));
        } catch (ArrayIndexOutOfBoundsException e) {
          fail(String.format("E: new matrices[%d].equals(matrices[%d]): %s", 
              i, j, e.getMessage()));
        } // try/catch
      } // for j
    } // for i
 
    // Make them all different
    matrix0.set(0, 0, "A");
    matrix1.deleteRow(0);
    matrix2.deleteRow(1);
    matrix3.deleteCol(0);
    matrix4.deleteCol(3);

    // Check that they are no longer equal
    for (int i = 0; i < matrices.length; i++) {
      for (int j = 0; j < matrices.length; j++) {
        if (i != j) {
          try {
            assertFalse(matrices[i].equals(matrices[j]),
                String.format("E: newer matrices[%d].equals(matrices[%d])", 
                    i, j));
          } catch (ArrayIndexOutOfBoundsException e) {
            fail(String.format("E: newer matrices[%d].equals(matrices[%d]): %s",
                i, j, e.getMessage()));
          } // try/catch
        } // if
      } // for j
    } // for i
  } // testEquals()

} // TestMatrix
