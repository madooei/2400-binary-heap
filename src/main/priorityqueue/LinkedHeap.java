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
    this(null); // calls the other constructor with a null comparator
  }

  // Orders the elements by the given comparator.
  public LinkedHeap(Comparator<T> comparator) {
    root = null;
    size = 0;
    this.comparator = comparator;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public T peek() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    return root.value;  // the highest-priority element is always at the root
  }

  @Override
  public void add(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    size++;
    root = add(root, item, pathTo(size), 0);
  }

  // Attaches a new node holding item at the end of path, below node, and
  // swims it up on the way back. index is the next step of path to take.
  // Returns the root of the subtree.
  private Node<T> add(Node<T> node, T item, String path, int index) {
    if (index == path.length()) {
      return new Node<>(item);  // the open slot
    }
    if (path.charAt(index) == '0') {
      node.left = add(node.left, item, path, index + 1);
      swimStep(node, node.left);
    } else {
      node.right = add(node.right, item, path, index + 1);
      swimStep(node, node.right);
    }
    return node;
  }

  @Override
  public void remove() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    if (size == 1) {
      root = null;
      size = 0;
      return;
    }
    root.value = removeLast(pathTo(size));
    size--;
    sink(root);
  }

  // Returns a negative number if a has higher priority than b, zero if they
  // tie, and a positive number if a has lower priority.
  private int compare(T a, T b) {
    if (comparator != null) {
      return comparator.compare(a, b);
    }
    return a.compareTo(b);
  }

  // The directions from the root to the node at position, one character per
  // step: '0' for left and '1' for right. The root is position 1.
  private String pathTo(int position) {
    String binary = Integer.toBinaryString(position);
    return binary.substring(1);  // drop the leading 1, which is the root
  }

  // Exchanges the values of nodes a and b. The nodes stay where they are.
  private void swapValues(Node<T> a, Node<T> b) {
    T temp = a.value;
    a.value = b.value;
    b.value = temp;
  }

  // Swaps the values of parent and child if the child outranks the parent.
  private void swimStep(Node<T> parent, Node<T> child) {
    if (compare(child.value, parent.value) < 0) {
      swapValues(parent, child);
    }
  }

  // Detaches the node at the end of path and returns its value.
  // Pre: path has at least one step.
  private T removeLast(String path) {
    Node<T> parent = root;
    for (int i = 0; i < path.length() - 1; i++) {
      if (path.charAt(i) == '0') {
        parent = parent.left;
      } else {
        parent = parent.right;
      }
    }
    T value;
    if (path.charAt(path.length() - 1) == '0') {
      value = parent.left.value;
      parent.left = null;
    } else {
      value = parent.right.value;
      parent.right = null;
    }
    return value;
  }

  // Moves the value in node down until no child outranks it.
  private void sink(Node<T> node) {
    Node<T> best = node.left;
    if (best == null) {
      return;  // a leaf
    }
    if (node.right != null && compare(node.right.value, best.value) < 0) {
      best = node.right;
    }
    if (compare(best.value, node.value) < 0) {
      swapValues(node, best);
      sink(best);
    }
  }
}
