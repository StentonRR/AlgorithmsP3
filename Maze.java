/** CMPT_435L_800
 * Project 2 -- Shortest-Path Word-Melt Solver
 * Filename: Maze.java
 * Student Name: Eric Stenton
 * Due Date: February 26, 2020
 * Version 1.0
 *
 * This file contains the functions to define a maze from values given through
 * system input.
 */

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Maze
 *
 * This class defines the maze object to be solved. It provides a tree set
 * of valid locations, the start location, and the end location.
 */
class Maze {
  private Set<Location> validLocations;

  private Location startLocation;
  private Location endLocation;

  /** Maze
   *  parameters: nothing
   *  return value: nothing
   *
   *  This function serves as the constructor for the Maze object. It
   *  requires no input variables; it initializes the start and end location
   *  variables to new instances of location objects and the validLocations
   *  variable to a new tree set for location objects.
   */
  Maze() {
    validLocations = new TreeSet<Location>();
    startLocation = new Location();
    endLocation = new Location();
  }

  /** getStartLocation
   *  parameters: nothing
   *  return value:
   *      Location -- The starting location of the maze.
   *
   *  This function simply returns the location object describing the start
   *  of the maze.
   */
  Location getStartLocation() {
    return this.startLocation;
  }

  /** isValidLocation
   *  parameters:
   *      loc -- The location object to check if it exists in the tree set.
   *  return value:
   *      boolean -- True if the location object describes a valid location and
   *                 false if not.
   *
   *  This function takes advantage of a tree set to determine if the location
   *  object exists in it.
   */
  boolean isValidLocation(Location loc) {
    return this.validLocations.contains(loc);
  }

  /** isEndLocation
   *  parameters:
   *      loc -- The location object to check if it is equal to the ending
   *      location of the maze.
   *  return value:
   *      boolean -- True if the location object describes a location equal
   *                 to the ending location and false if not.
   *
   *  This function simply checks for equality between the specified location
   *  object and the ending location object of the maze.
   */
  boolean isEndLocation(Location loc) {
    return this.endLocation.isEqual(loc);
  }

  /** streamIn
   *  parameters:
   *      input -- A scanner object used to read system input.
   *  return value: nothing
   *
   *  This function obtains the maze definition from system input. The first
   *  integer defines the number of valid locations. The following number of
   *  lines equal to the number of valid locations each has a word (String)
   *  value for a valid location. The last two lines give the start and end
   *  word respectively.
   */
  void streamIn(Scanner input) {

    // Read number of locations
    int validLocationCount = Integer.parseInt(input.next());
    assert(validLocationCount > 0);

    // Read valid locations
    for (int i = 0; i < validLocationCount; i++) {
      Location loc = new Location();
      loc.streamIn(input);
      this.validLocations.add(loc);
    }
    assert(this.validLocations.size() > 0);

    // Read start and end locations
    this.startLocation.streamIn(input);
    this.endLocation.streamIn(input);

    assert(this.startLocation != null);
    assert(this.endLocation != null);

  }
}
