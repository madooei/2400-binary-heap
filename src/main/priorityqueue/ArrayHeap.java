package priorityqueue;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A binary heap implementation of the PriorityQueue ADT, stored in an array
 * in level order.
 *
 * @param <T> the type of elements in this priority queue
 */
public class ArrayHeap<T extends Comparable<T>> implements PriorityQueue<T> {

  private T[] heap;                  // the complete tree, stored in level order
  private int size;
  private Comparator<T> comparator;  // null means use natural ordering

  // Orders the elements by their natural ordering.
  @SuppressWarnings("unchecked")
  public ArrayHeap() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Orders the elements by the given comparator.
  @SuppressWarnings("unchecked")
  public ArrayHeap(Comparator<T> comparator) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public int size() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public T peek() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public void add(T item) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public void remove() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Returns a negative number if a has higher priority than b, zero if they
  // tie, and a positive number if a has lower priority.
  private int compare(T a, T b) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // The parent of the node at index i. Pre: i > 0.
  private int parent(int i) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // The left child of the node at index i.
  private int leftChild(int i) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // The right child of the node at index i.
  private int rightChild(int i) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Exchanges the elements at indices i and j.
  private void swap(int i, int j) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Moves the element at index i up until it does not outrank its parent.
  private void swim(int i) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Moves the element at index i down until no child outranks it.
  private void sink(int i) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Doubles the capacity.
  @SuppressWarnings("unchecked")
  private void grow() {
    T[] bigger = (T[]) new Comparable[heap.length * 2];
    for (int i = 0; i < size; i++) {
      bigger[i] = heap[i];
    }
    heap = bigger;
  }
}
