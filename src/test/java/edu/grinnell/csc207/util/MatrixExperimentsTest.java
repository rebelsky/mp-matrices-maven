package edu.grinnell.csc207.util;

import org.junit.jupiter.api.Test;

import static edu.grinnell.csc207.util.MatrixAssertions.assertFigure;

/**
 * Automated tests based on {@link edu.grinnell.csc207.experiments.MatrixExperiments}.
 *
 * <p>This test suite was created by implementing stubs in {@link MatrixV0},
 * executing {@code MatrixExperiments} (written by SamR), and directly using
 * the console output to form assertions.</p>
 *
 * <p>In my coding environment, I used a paid GitHub Copilot subscription,
 * which provided auto-suggestions for some of the stub methods in {@link MatrixV0}.
 * After review, I incorporated some of these suggestions. Note: {@link MatrixV0}
 * is not included here, as it is part of an active assignment for SamR’s
 * CSC207 course, Fall 2024.</p>
 * 
 * <p>After writing this test suite, I asked ChatGPT-o1-preview (the "advanced
 * reasoning skills" model) to double-check my work. The prompt I used was
 * "<COPY-PASTED CODE HERE> Can you double check that this all makes sense?
 * Have I made any mistakes? Be extra thorough.". ChatGPT's response indiciated
 * thoroughness (it didn't skip or hallucinate anything), and it's conclusion
 * was "[...] I did not find any mistakes in your code. [...]"</p>
 *
 * @author Ian Th. Atha '09 <athaian@grinnell.edu>
 * @author Samuel A. Rebelsky
 */
class MatrixExperimentsTest {
  @Test
  void matrixExperimentsAsTest() throws ArraySizeException {
    Matrix<String> sample = new MatrixV0<String>(5, 6, "O");
    for (int row = 0; row < 6; row++) {
      for (int col = 0; col < 5; col++) {
        sample.set(row, col, "X");
      } // for col
    } // for row

    assertFigure("""
      +---+---+---+---+---+
      | X | X | X | X | X |
      +---+---+---+---+---+
      | X | X | X | X | X |
      +---+---+---+---+---+
      | X | X | X | X | X |
      +---+---+---+---+---+
      | X | X | X | X | X |
      +---+---+---+---+---+
      | X | X | X | X | X |
      +---+---+---+---+---+
      | X | X | X | X | X |
      +---+---+---+---+---+
      """, sample, "Matrix.set");

    sample.insertCol(2);
    assertFigure("""
      +---+---+---+---+---+---+
      | X | X | O | X | X | X |
      +---+---+---+---+---+---+
      | X | X | O | X | X | X |
      +---+---+---+---+---+---+
      | X | X | O | X | X | X |
      +---+---+---+---+---+---+
      | X | X | O | X | X | X |
      +---+---+---+---+---+---+
      | X | X | O | X | X | X |
      +---+---+---+---+---+---+
      | X | X | O | X | X | X |
      +---+---+---+---+---+---+
      """, sample, "Matrix.insertCol in the middle");

    sample.insertCol(6, new String[] { "A", "B", "C", "D", "E", "F" });
    assertFigure("""
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | A |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | B |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | C |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | D |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | E |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | F |
      +---+---+---+---+---+---+---+
      """, sample, "Matrix.insertCol at the edge");

    sample.insertRow(0, new String[] { "P", "Q", "R", "S", "T", "U", "V" });
    assertFigure("""
      +---+---+---+---+---+---+---+
      | P | Q | R | S | T | U | V |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | A |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | B |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | C |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | D |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | E |
      +---+---+---+---+---+---+---+
      | X | X | O | X | X | X | F |
      +---+---+---+---+---+---+---+
      """, sample, "Matrix.insertRow at the top");

    sample.deleteCol(1);
    assertFigure("""
      +---+---+---+---+---+---+
      | P | R | S | T | U | V |
      +---+---+---+---+---+---+
      | X | O | X | X | X | A |
      +---+---+---+---+---+---+
      | X | O | X | X | X | B |
      +---+---+---+---+---+---+
      | X | O | X | X | X | C |
      +---+---+---+---+---+---+
      | X | O | X | X | X | D |
      +---+---+---+---+---+---+
      | X | O | X | X | X | E |
      +---+---+---+---+---+---+
      | X | O | X | X | X | F |
      +---+---+---+---+---+---+
      """, sample, "Matrix.deleteCol in the middle");

    sample.deleteRow(2);
    assertFigure("""
      +---+---+---+---+---+---+
      | P | R | S | T | U | V |
      +---+---+---+---+---+---+
      | X | O | X | X | X | A |
      +---+---+---+---+---+---+
      | X | O | X | X | X | C |
      +---+---+---+---+---+---+
      | X | O | X | X | X | D |
      +---+---+---+---+---+---+
      | X | O | X | X | X | E |
      +---+---+---+---+---+---+
      | X | O | X | X | X | F |
      +---+---+---+---+---+---+
      """, sample, "Matrix.deleteRow in the middle");

    sample.fillLine(0, 0, 1, 1, 4, 4, "Z");
    assertFigure("""
      +---+---+---+---+---+---+
      | Z | R | S | T | U | V |
      +---+---+---+---+---+---+
      | X | Z | X | X | X | A |
      +---+---+---+---+---+---+
      | X | O | Z | X | X | C |
      +---+---+---+---+---+---+
      | X | O | X | Z | X | D |
      +---+---+---+---+---+---+
      | X | O | X | X | X | E |
      +---+---+---+---+---+---+
      | X | O | X | X | X | F |
      +---+---+---+---+---+---+
      """, sample, "Matrix.fillLine with Z");

    sample.fillLine(0, 1, 2, 1, 6, 6, " ");
    assertFigure("""
      +---+---+---+---+---+---+
      | Z |   | S | T | U | V |
      +---+---+---+---+---+---+
      | X | Z | X | X | X | A |
      +---+---+---+---+---+---+
      | X | O |   | X | X | C |
      +---+---+---+---+---+---+
      | X | O | X | Z | X | D |
      +---+---+---+---+---+---+
      | X | O | X |   | X | E |
      +---+---+---+---+---+---+
      | X | O | X | X | X | F |
      +---+---+---+---+---+---+
      """, sample, "Matrix.fillLine with spac3");

    sample.fillLine(3, 0, 0, 1, 6, 6, "-");
    assertFigure("""
      +---+---+---+---+---+---+
      | Z |   | S | T | U | V |
      +---+---+---+---+---+---+
      | X | Z | X | X | X | A |
      +---+---+---+---+---+---+
      | X | O |   | X | X | C |
      +---+---+---+---+---+---+
      | - | - | - | - | - | - |
      +---+---+---+---+---+---+
      | X | O | X |   | X | E |
      +---+---+---+---+---+---+
      | X | O | X | X | X | F |
      +---+---+---+---+---+---+
      """, sample, "Matrix.fillLine horizontal");

    sample.fillLine(1, 4, 1, 0, 5, 6, "?");
    assertFigure("""
      +---+---+---+---+---+---+
      | Z |   | S | T | U | V |
      +---+---+---+---+---+---+
      | X | Z | X | X | ? | A |
      +---+---+---+---+---+---+
      | X | O |   | X | ? | C |
      +---+---+---+---+---+---+
      | - | - | - | - | ? | - |
      +---+---+---+---+---+---+
      | X | O | X |   | ? | E |
      +---+---+---+---+---+---+
      | X | O | X | X | X | F |
      +---+---+---+---+---+---+
      """, sample, "Matrix.fillLine vertical");

    sample.fillRegion(1, 1, 5, 4, "@");
    assertFigure("""
      +---+---+---+---+---+---+
      | Z |   | S | T | U | V |
      +---+---+---+---+---+---+
      | X | @ | @ | @ | ? | A |
      +---+---+---+---+---+---+
      | X | @ | @ | @ | ? | C |
      +---+---+---+---+---+---+
      | - | @ | @ | @ | ? | - |
      +---+---+---+---+---+---+
      | X | @ | @ | @ | ? | E |
      +---+---+---+---+---+---+
      | X | O | X | X | X | F |
      +---+---+---+---+---+---+
      """, sample, "Matrix.fillRegion in middle");

    sample.fillRegion(4, 2, 6, 6, ".");
    assertFigure("""
      +---+---+---+---+---+---+
      | Z |   | S | T | U | V |
      +---+---+---+---+---+---+
      | X | @ | @ | @ | ? | A |
      +---+---+---+---+---+---+
      | X | @ | @ | @ | ? | C |
      +---+---+---+---+---+---+
      | - | @ | @ | @ | ? | - |
      +---+---+---+---+---+---+
      | X | @ | . | . | . | . |
      +---+---+---+---+---+---+
      | X | O | . | . | . | . |
      +---+---+---+---+---+---+
      """, sample, "Matrix.fillRegion in corner");

    sample.fillRegion(0, 0, 6, 6, ".");
    assertFigure("""
      +---+---+---+---+---+---+
      | . | . | . | . | . | . |
      +---+---+---+---+---+---+
      | . | . | . | . | . | . |
      +---+---+---+---+---+---+
      | . | . | . | . | . | . |
      +---+---+---+---+---+---+
      | . | . | . | . | . | . |
      +---+---+---+---+---+---+
      | . | . | . | . | . | . |
      +---+---+---+---+---+---+
      | . | . | . | . | . | . |
      +---+---+---+---+---+---+
      """, sample, "Matrix.fillRegion");
  } // matrixExperimentsAsTest()
} // MatrixExperimentsTest
