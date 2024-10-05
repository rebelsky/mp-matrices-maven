package edu.grinnell.csc207.util;

import java.util.Arrays;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The default value. */
  T defValue;

  /** The number of rows in the matrix. */
  int numRows;

  /** The number of columns in the matrix. */
  int numCols;

  /** The contents of the matrix. */
  T[][] contents;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  @SuppressWarnings({"unchecked"})
  public MatrixV0(int width, int height, T def) {
    numCols = width;
    numRows = height;
    defValue = def;

    contents = (T[][]) new Object[height][];
    for (int row = 0; row < height; row++) {
      contents[row] = (T[]) new Object[width];
      for (int col = 0; col < width; col++) {
        contents[row][col] = def;
      } // for col
    } // for row
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Determine if a column is valid. If not, throw an exception.
   *
   * @param col
   *   The column to check.
   *
   * @throws IndexOutOfBoundException
   *   If the column is out of bounds.
   */
  void checkColumn(int col) {
    if ((col < 0) || (col > this.numCols)) {
      throw new IndexOutOfBoundsException(String.format(
          "Invalid column %d in %d-wide and %d-high matrix",
          col, this.numCols, this.numRows));
    } // if
  } // checkColumn(int)

  /**
   * Determine if a row is valid. If not, throw an exception.
   *
   * @param row
   *   The row to check.
   *
   * @throws IndexOutOfBoundException
   *   If the row is out of bounds.
   */
  void checkRow(int row) {
    if ((row < 0) || (row > this.numCols)) {
      throw new IndexOutOfBoundsException(String.format(
          "Invalid row %d in %d-wide and %d-high matrix",
          row, this.numCols, this.numRows));
    } // if
  } // checkRow(int)

  /**
   * Insert one value in one column.
   *
   * @param oldRow
   *   The original row.
   * @param col
   *   The column to insert.
   * @param val
   *   The value to insert.
   *
   * @return
   *   The new array.
   */
  T[] insertCol(T[] oldRow, int col, T val) {
    T[] newRow = Arrays.copyOf(oldRow, oldRow.length+1);
    for (int i = col+1; i < oldRow.length; i++) {
      newRow[i+1] = oldRow[i];
    } // for
    newRow[col] = val;
    return newRow;
  } // insertCol()

  /**
   * Insert an already-allocated new row.
   *
   * @param row
   *   The number of the row to insert.
   * @param newRow
   *   The contents of the new row.
   */
  @SuppressWarnings({"unchecked"})
  void insertRowCommon(int row, T[] newRow) {
    T[][] newContents = (T[][]) new Object[this.numRows + 1][];
    // Copy the elements before the new row.
    for (int i = 0; i < row; i++) {
      newContents[i] = this.contents[i];
    } // for
    // Copy the elements after the new row.
    for (int i = row; i < this.numRows; i++) {
      newContents[i+1] = this.contents[i];
    } // for
    newContents[row] = newRow;
    this.contents = newContents;
    ++this.numRows;
  } // insertRowCommon(int)

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    return this.contents[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    this.contents[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.numRows;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.numCols;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  @SuppressWarnings({"unchecked"})
  public void insertRow(int row) {
    checkRow(row);
    T[] newRow = (T[]) new Object[this.numCols];
    for (int col = 0; col < this.numCols; col++) {
      newRow[col] = defValue;
    } // for
    this.insertRowCommon(row, newRow);
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    checkRow(row);
    if (vals.length != this.numCols) {
      throw new ArraySizeException(String.format(
          "Incorrect width row. Expected %d, but row was %d",
          this.numCols, vals.length));
    } // if
    insertRowCommon(row, Arrays.copyOf(vals, this.numCols));
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    checkColumn(col);
    for (int row = 0; row < numRows; row++) {
      this.contents[row] = insertCol(this.contents[row], col, defValue);
    } // for row
    ++this.numCols;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    checkColumn(col);
    if (vals.length != this.numRows) {
      throw new ArraySizeException(String.format(
          "Incorrect size column. Expected %d, but size was %d",
          this.numRows, vals.length));
    } // if
    for (int row = 0; row < numRows; row++) {
      this.contents[row] = insertCol(this.contents[row], col, vals[row]);
    } // for row
    ++this.numCols;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if ((row < 0) || (row > this.numRows)) {
      throw new ArrayIndexOutOfBoundsException(String.format(
        "Invalid row (%d) for %d-wide and %d-high matrix",
        row, numCols, numRows));
    } // if
    T[][] newContents = (T[][]) new Object[this.numRows - 1][];
    // Copy the elements before the deleted row
    for (int i = 0; i < row; i++) {
      newContents[i] = this.contents[i];
    } // for
    // Copy the elements after the new row.
    for (int i = row+1; i < this.numRows; i++) {
      newContents[i-1] = this.contents[i];
    } // for
    this.contents = newContents;
    --this.numRows;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    checkColumn(col);
    for (int row = 0; row < this.numRows; row++) {
      T[] oldRow = this.contents[row];
      T[] newRow = Arrays.copyOf(oldRow, this.numCols - 1);
      for (int c = col+1; c < this.numCols; c++) {
        newRow[c-1] = oldRow[c];
      } // for
      this.contents[row] = newRow;
    } // for row
    --this.numCols;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    for (int row = startRow; row < endRow; row++) {
      for (int col = startCol; col < endCol; col++) {
        this.set(row, col, val);
      } // for col
    } // for row
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    int row = startRow;
    int col = startCol;
    while ((row < endRow) && (col < endCol)) {
      this.set(row, col, val);
      row += deltaRow;
      col += deltaCol;
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {
    Matrix<T> result = new MatrixV0(this.width(), this.height(), this.defValue);
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        result.set(row, col, this.get(row, col));
      } // for
    } // for
    return result;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    return this == other;       // STUB
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
