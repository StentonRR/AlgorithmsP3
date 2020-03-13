/** CMPT_435L_800
 * Project 2 -- Shortest-Path Word-Melt Solver
 * Filename: Driver_prj2.java
 * Student Name: Eric Stenton
 * Due Date: February 26, 2020
 * Version 1.0
 *
 * This file contains the main function for the Shortest-Path Word-Melt Solver
 * project. It uses a breadth-first search in order to find a path from the
 * start location to the end location. In this project, the locations are
 * words.
 */


import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * Driver_prj2
 *
 * This class implements Location, ArrayQueue, and Maze objects to
 * find the solution to a given word-melt 'maze' in system input.
 */
public class Driver_prj2 {

    /** main
     *  parameters:
     *      args -- the array of command line argument values
     *  return value: nothing
     *
     * This function uses a breadth-first search to determine whether a
     * solution exists for a given word-melt 'maze' in system input.
     * It prints its results.
     */
    public static void main(String[] args) {

        // Initialize scanner variable
        Scanner input = new Scanner(System.in);

        // Create maze object and read maze from system input
        Maze maze = new Maze();
        maze.streamIn(input);

        // Initialize array queue
        ArrayQueue locationQueue = new ArrayQueue();

        // Initialize map
        Map<String, String> map = new HashMap<String, String>();

        // Initialize starting location
        Location startLocation = maze.getStartLocation();
        locationQueue.add(startLocation);
        map.put(startLocation.word, startLocation.word);

        // Check if only start location
        if ( maze.isEndLocation(startLocation) ) {
            System.out.println("Solution found:");
            startLocation.streamOut();
            return;
        }



        // Explore the maze until end location is found or queue is empty
        Location neighbor;
        Location currentLocation;
        while ( locationQueue.getLength() != 0  ){

            // Get next location
            currentLocation = locationQueue.getFront();
            locationQueue.remove();
            assert(currentLocation != null);
            currentLocation.start();

            while ( !currentLocation.isDone() ) {

                // Get possible next neighbor
                neighbor = currentLocation.nextNeighbor();
                assert(neighbor != null);

                // Add location to the queue if it is valid and its shortest
                // path hasn't been found
                if ( maze.isValidLocation(neighbor) &&
                        !map.containsKey(neighbor.word) ) {

                    locationQueue.add(neighbor);
                    map.put(neighbor.word, currentLocation.word);

                    // Check if neighbor is the end word
                    if ( maze.isEndLocation(neighbor) ) {

                        System.out.println("Solution found:");

                        // Create path using a stack
                        String currentWord = neighbor.word;
                        Stack<String> outputStack = new Stack<String>();
                        outputStack.push(currentWord);
                        while ( !currentWord.equals(startLocation.word) ) {
                            currentWord = map.get(currentWord);
                            outputStack.push(currentWord);
                        }

                        // Print the path
                        while ( !outputStack.empty() ) {
                            System.out.println( outputStack.pop() );
                        }

                        return;

                    }

                }
            }

        }

        // No solution was found
        System.out.println("No solution found");
        return;
    }
}