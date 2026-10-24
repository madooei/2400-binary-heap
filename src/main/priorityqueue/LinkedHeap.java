package priorityqueue;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A binary heap implementation of the PriorityQueue ADT, built from linked
 * nodes.
 *
 * @param <T> the type of elements in this priority queue
 */
public class LinkedHeap<T extends Comparable<T>> implements PriorityQueue<T> {

  private Node<T> root;
  private int size;
  private Comparator<T> comparator;  // null means use natural ordering

  private static class Node<T> {
    T value;
    Node<T> left;
    Node<T> right;

    Node(T value) {
      this.value = value;
    }
  }

  // Orders the elements by their natural ordering.
  public LinkedHeap() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Orders the elements by the given comparator.
  public LinkedHeap(Comparator<T> comparator) {
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

  // Attaches a new node holding item at the end of path, below node, and
  // swims it up on the way back. index is the next step of path to take.
  // Returns the root of the subtree.
  private Node<T> add(Node<T> node, T item, String path, int index) {
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

  // The directions from the root to the node at position, one character per
  // step: '0' for left and '1' for right. The root is position 1.
  private String pathTo(int position) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Exchanges the values of nodes a and b. The nodes stay where they are.
  private void swapValues(Node<T> a, Node<T> b) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Swaps the values of parent and child if the child outranks the parent.
  private void swimStep(Node<T> parent, Node<T> child) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Detaches the node at the end of path and returns its value.
  // Pre: path has at least one step.
  private T removeLast(String path) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Moves the value in node down until no child outranks it.
  private void sink(Node<T> node) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
