/** CMPT_435L_800
 * Project 2 -- Shortest-Path Word-Melt Solver
 * Filename: ArrayQueue.java
 * Student Name: Eric Stenton
 * Due Date: February 26, 2020
 * Version 1.0
 *
 * This file contains functions pertaining to the creation of a queue that
 * holds location objects that is simulated by a circular array. The queue will
 * hold location objects that have yet to be evaluated.
 */


/**
 * ArrayQueue
 *
 * This class defines a queue that holds location objects. It provides standard
 * queue functions such as add, remove, and others. The front variable is a
 * reference to the first location object stored in the queue. The capacity
 * variable defines how large the underlying array is, and the length variable
 * defines the number of location objects in the queue.
 */
class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;

  /** doubleCapacity
   *  parameters: nothing
   *  return value: nothing
   *
   *  This function increases the capacity of the underlying array by double
   *  its original capacity. This is only done when attempting to add to the
   *  queue when it is full.
   */
  private void doubleCapacity() {

    // Create new array of double capacity
    Location[] largerArray = new Location[this.capacity*2];

    // Fill new array
    int iterator = this.length;
    for (int i = 0; i < iterator; i++) {
      largerArray[i] = this.getFront();
      this.remove();
    }

    // Update attributes
    this.data = largerArray;
    this.length = iterator;
    this.capacity *= 2;
    this.front = 0;
  }

  /** LocationQueue
   *  parameters: nothing
   *  return value: nothing
   *
   *  This function serves as the constructor for the ArrayQueue object.
   *  It requires no input variables; it initializes the length and front to 0,
   *  the capacity to 10, and data variable to a new location array that has
   *  a size equal to the capacity variable.
   */
  ArrayQueue() {
    this.length = 0;
    this.capacity = 10;
    this.front = 0;
    this.data = new Location[this.capacity];
  }

  /** ArrayQueue
   *  parameters:
   *      q -- ArrayQueue object to copy.
   *  return value: nothing
   *
   *  This function serves as the copy constructor for the ArrayQueue object.
   *  It requires an ArrayQueue object as input; it calls the copyFrom function
   *  in order to copy the incoming ArrayObject's attributes to this instance.
   */
  ArrayQueue(ArrayQueue q) {
    copyFrom(q);
  }

  /** add
   *  parameters:
   *      loc -- Location object to add to the queue.
   *  return value: nothing
   *
   *  This function adds a location object to the queue. It also increases the
   *  length to reflect the new object's addition.
   */
  void add(Location loc) {
    assert(loc != null);

    // Increase capacity if needed
    if (this.capacity == this.length) {
      doubleCapacity();
    }

    // Add location to next place
    this.data[ (this.length + this.front) % this.capacity ] = loc;

    // Update length
    this.length++;
  }

  /** remove
   *  parameters: nothing
   *  return value: nothing
   *
   *  This function removes the location object at the front of the queue.
   *  It does this simply by increasing the front variable by one.
   */
  void remove() {
    this.front = (this.front + 1) % this.capacity;
    this.length--;
  }

  /** getFront
   *  parameters: nothing
   *  return value:
   *      Location -- The location that is currently at the front of the queue.
   *
   *  This function retrieves the location at the front of the queue and
   *  returns it.
   */
  Location getFront() {
    assert(this.data[this.front] != null);
    return this.data[this.front];
  }

  /** getLength
   *  parameters: nothing
   *  return value:
   *      int -- The length of the queue.
   *
   *  This function returns the value of the length variable.
   */
  int getLength()  {
    return this.length;
  }

  /** copyFrom
   *  parameters:
   *      q -- ArrayQueue object to copy.
   *  return value:
   *      ArrayQueue -- This ArrayQueue instance.
   *
   *  This function deep copies the incoming ArrayObject's attributes to this
   *  instance. If the instance and incoming object are equal, then this
   *  instance is simply returned.
   */
  ArrayQueue copyFrom(ArrayQueue q) {

    // Check if same array queue
    if ( !q.equals(this) ) {

      // Deep copy array queue
      this.capacity = q.getLength();
      data = new Location[this.capacity];
      for (int i = 0; i < this.capacity; i++) {
        add(q.getFront());
        q.remove();
      }

    }

    return this;
  }
}

