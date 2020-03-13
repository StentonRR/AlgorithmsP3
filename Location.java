/** CMPT_435L_800
 * Project 2 -- Shortest-Path Word-Melt Solver
 * Filename: Location.java
 * Student Name: Eric Stenton
 * Due Date: February 26, 2020
 * Version 1.0
 *
 * This file contains functions pertaining to the creation of a location object.
 * Such objects represent words within a word-melt 'maze'.
 */

import java.util.Scanner;

/**
 * Location
 *
 * This class defines a location object which provides an iterator for its
 * neighbors using index and letter variables.
 */
class Location implements Comparable<Location>{
  final int CHANGE_LETTER = 0;
  final int INSERT_LETTER = 1;
  final int DELETE_LETTER = 2;
  final int DONE          = 3;

  String word;
  int iterationMode;
  int indexToChange;
  char nextLetter;

  /** Location
   *  parameters: nothing
   *  return value: nothing
   *
   *  This function serves as the constructor for the Location object. It
   *  requires no input variables; it initializes the word to an empty string,
   *  the iterationMode to DONE (3), indexToChange to 0, and nextLetter to 'a'.
   */
  Location() {
    this.word = "";
    this.iterationMode = this.DONE;
    this.indexToChange = 0;
    this.nextLetter = 'a';
  }

  /** start
   *  parameters: nothing
   *  return value: nothing
   *
   *  This function simply changes the value of the iterationMode variable to
   *  CHANGE_LETTER in order to start the process of determining the correct
   *  neighboring word from this location.
   */
  void start() {
    this.iterationMode = this.CHANGE_LETTER;
  }

  /** nextNeighbor
   *  parameters: nothing
   *  return value:
   *      Location -- The location object of the next neighboring location.
   *
   *  This function returns a location object originating as a copy of the
   *  location object instance. The copy's word value is modified
   *  to determine if it is valid in the maze and should be evaluated.
   */
  Location nextNeighbor() {

    // Create a copy
    Location w = new Location();
    w.word = this.word;

    // Modify copy
    if ( this.iterationMode == this.CHANGE_LETTER ) {

      // Skip 'a' if already present in beginning of word
      if ( this.word.charAt(this.indexToChange) == this.nextLetter ) {
        this.nextLetter++;
      }

      w.word = w.word.substring(0, this.indexToChange)
              + this.nextLetter
              + w.word.substring(this.indexToChange + 1);

      this.nextLetter++;

      // Skip letter if already present in word at index
      if ( this.word.charAt(this.indexToChange) == this.nextLetter ) {
        this.nextLetter++;
      }

    } else if ( this.iterationMode == this.INSERT_LETTER ) {
      w.word = w.word.substring(0, this.indexToChange)
              + this.nextLetter
              + w.word.substring(this.indexToChange);

      this.nextLetter++;

    } else if ( this.iterationMode == this.DELETE_LETTER ) {
      w.word = w.word.substring(0, this.indexToChange)
              + w.word.substring(this.indexToChange+1);

      this.indexToChange++;
    }

    // Ensure next letter is in bounds -- 97 to 122 -- else go to next index
    if (this.nextLetter > 122) {
      this.indexToChange++;
      this.nextLetter = 'a';
    }

    // Go to next iteration mode if character index out of range.
    // Ranges are word.length() - 1, word.length(), and word.length() - 1
    // for each of the iteration modes respectively
    if ( this.indexToChange >
            (this.iterationMode == CHANGE_LETTER ?
                    this.word.length() - 1 :
                    (this.iterationMode == this.INSERT_LETTER ?
                    this.word.length() : this.word.length() - 1) ) ) {
      this.iterationMode++;
      this.indexToChange = 0;
    }

    return w;
  }

  /** isDone
   *  parameters: nothing
   *  return value:
   *      boolean -- True if the iterationMode variable is equal to DONE and
   *                 false if not.
   *
   *  This function simply checks if there are any more iteration modes to
   *  evaluate for the current location. If not, it returns true and this
   *  location is 'done'.
   */
  boolean isDone() {  
    return this.iterationMode == this.DONE;
  }

  /** isEqual
   *  parameters:
   *      loc -- The location object to test its equality with the current one.
   *  return value:
   *      boolean -- True if the provided location object is equal to the
   *                 current one and false if it is not.
   *
   *  This function checks the equality of two location objects by determining
   *  if their word values are the same.
   */
  boolean isEqual(Location loc) {  
    return this.word.equals(loc.word);
  }

  /** streamOut
   *  parameters: nothing
   *  return value: nothing
   *
   *  This function prints the location object's word value.
   */
  void streamOut() {
    System.out.print(this.word);
  }

  /** streamIn
   *  parameters:
   *      input -- A scanner object used to read system input.
   *  return value: nothing
   *
   *  This function obtains the location's word value using the provided
   *  scanner object.
   */
  void streamIn(Scanner input) {
    assert(input.hasNext());
    this.word = input.next();
    assert(this.word.length() > 0);
  }

  /** isLess
   *  parameters:
   *      loc -- A location object to compare to this instance.
   *  return value: nothing
   *
   *  This function compares a given location object with this instance and
   *  returns true if the location's word is lexicographically less than this
   *  instance's word.
   */
  boolean isLess(Location loc) {
    return 0 > this.word.compareTo(loc.word);
  }

  /** compareTo
   *  parameters:
   *      loc -- A location object to compare to this instance.
   *  return value: nothing
   *
   *  This function compares a given location object with this instance and
   *  returns a positive number if the instance's word is bigger, a negative if
   *  it is smaller, and zero if they are the same.
   */
  @Override
  public int compareTo(Location loc) {
    int output = 0;
    if ( this.isLess(loc) ) {
      output = -1;
    } else if ( this.word.compareTo(loc.word) > 0 ) {
      output = 1;
    }

    return this.word.compareTo(loc.word);
  }

}
