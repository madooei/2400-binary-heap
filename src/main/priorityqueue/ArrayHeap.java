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
    heap = (T[]) new Comparable[10];
    size = 0;
    comparator = null;
  }

  // Orders the elements by the given comparator.
  @SuppressWarnings("unchecked")
  public ArrayHeap(Comparator<T> comparator) {
    heap = (T[]) new Comparable[10];
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
    return heap[0];  // the highest-priority element is always at the root
  }

  @Override
  public void add(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    if (size == heap.length) {
      grow();
    }
    heap[size] = item;  // the next open slot
    size++;
    swim(size - 1);
  }

  @Override
  public void remove() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    heap[0] = heap[size - 1];  // move the last element into the root
    heap[size - 1] = null;     // clear the vacated slot
    size--;
    if (size > 0) {
      sink(0);
    }
  }

  // Returns a negative number if a has higher priority than b, zero if they
  // tie, and a positive number if a has lower priority.
  private int compare(T a, T b) {
    if (comparator != null) {
      return comparator.compare(a, b);
    }
    return a.compareTo(b);
  }

  // The parent of the node at index i. Pre: i > 0.
  private int parent(int i) {
    return (i - 1) / 2;
  }

  // The left child of the node at index i.
  private int leftChild(int i) {
    return 2 * i + 1;
  }

  // The right child of the node at index i.
  private int rightChild(int i) {
    return 2 * i + 2;
  }

  // Exchanges the elements at indices i and j.
  private void swap(int i, int j) {
    T temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

  // Moves the element at index i up until it does not outrank its parent.
  private void swim(int i) {
    while (i > 0 && compare(heap[i], heap[parent(i)]) < 0) {
      swap(i, parent(i));
      i = parent(i);
    }
  }

  // Moves the element at index i down until no child outranks it.
  private void sink(int i) {
    while (leftChild(i) < size) {  // stop at a leaf
      int best = leftChild(i);
      int right = rightChild(i);
      if (right < size && compare(heap[right], heap[best]) < 0) {
        best = right;
      }
      if (compare(heap[best], heap[i]) >= 0) {
        break;  // no child outranks the element
      }
      swap(i, best);
      i = best;
    }
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
