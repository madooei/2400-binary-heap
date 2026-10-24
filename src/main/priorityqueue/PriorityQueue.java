package priorityqueue;

import java.util.NoSuchElementException;

/**
 * A PriorityQueue is a collection of elements served in priority order: the
 * element with the highest priority is always the next one removed. Priority is
 * given by the natural ordering of the elements, where a smaller element has the
 * higher priority. Duplicate elements are allowed.
 *
 * @param <T> the type of elements in this priority queue
 */
public interface PriorityQueue<T extends Comparable<T>> {
  /**
   * Adds an item to this priority queue.
   * @param item the item to be added to this priority queue
   * @throws IllegalArgumentException if the item is null
   */
  void add(T item);

  /**
   * Returns, without removing, the highest-priority item in this priority queue.
   * @return the highest-priority item
   * @throws NoSuchElementException if this priority queue is empty
   */
  T peek();

  /**
   * Removes the highest-priority item from this priority queue. To inspect that
   * item, call peek before removing it.
   * @throws NoSuchElementException if this priority queue is empty
   */
  void remove();

  /**
   * Returns the number of items in this priority queue.
   * @return the number of items in this priority queue
   */
  int size();
}
