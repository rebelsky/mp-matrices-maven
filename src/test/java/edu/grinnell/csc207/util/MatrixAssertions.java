package edu.grinnell.csc207.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code Assertions} is a collection of utility methods that support asserting
 * the state of a {@link Matrix} in tests.
 *
 * <p>All methods herein end up calling an assert method from
 * {@link org.junit.jupiter.api.Assertions} therefore, as stated in the
 * {@code Assertions} JavaDoc, "a <em>failed</em> assertion will throw an
 * {@link org.opentest4j.AssertionFailedError} or a subclass thereof."
 *
 * @author Ian Th. Atha '09 <athaian@grinnell.edu>
 * @author Samuel A. Rebelsky
 */
class MatrixAssertions {
  /**
   * Assert that a matrix looks as expected.
   *
   * @param expected
   *   What we expect the matrix to look like. Warning! This depends
   *   a lot on the `Matrix.print` method.
   * @param matrix
   *   The matrix to compare.
   * @param msg
   *   The message to print if the matrix does not look as expected.
   */
  static <T> void assertFigure(String expected, Matrix<T> matrix, String msg) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter pen = new PrintWriter(stringWriter);
    Matrix.print(pen, matrix, false);
    String output = stringWriter.toString();
    try { 
      stringWriter.close();
    } catch (Exception e) {
    } // try/catch
    assertEquals(expected, output, msg);
  } // assertFigure(String, Matrix<T>, String)

  /**
   * Assert that a matrix has the appropriate form.
   *
   * @param expected
   *   The expected form, as a two-dimensional array. Warning! Assumes
   *   this is rectangular.
   * @param actual
   *   The matrix we're comparing.
   * @param message
   *   The message to provide.
   */
  static <T> void assertMatrixEquals(T[][] expected, Matrix<T> actual,
      String message) {
    int height = actual.height();
    int width = actual.width();
    assertEquals(expected.length, height, message + " [height]");
    assertEquals(expected[0].length, width, message + " [width]");
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        T e = expected[row][col];
        T a = actual.get(row, col);
        assertEquals(e, a,
            String.format("%s [at (%d,%d)]", message, row, col));
      } // for col
    } // for row
  } // assertMatrixEquals()

} // class MatrixAssertions
