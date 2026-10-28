package priorityqueue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/** Runs the PriorityQueue contract suite against ArrayHeap. */
public class ArrayHeapTest extends PriorityQueueTest {

  @Override
  protected PriorityQueue<Integer> createQueue() {
    return new ArrayHeap<>();
  }

  @Test
  public void addPlacesFirstItemAtTheRoot() {
    ArrayHeap<Integer> heap = heapOf(5);
    assertEquals(listOf(5), contents(heap));
  }

  @Test
  public void addFillsTheNextOpenSlot() {
    ArrayHeap<Integer> heap = heapOf(5, 8);
    assertEquals(listOf(5, 8), contents(heap));
  }

  @Test
  public void addSwimsSmallerItemUpOneLevel() {
    ArrayHeap<Integer> heap = heapOf(5, 8, 3);
    assertEquals(listOf(3, 8, 5), contents(heap));
  }

  @Test
  public void addSwimsSmallerItemUpTwoLevels() {
    ArrayHeap<Integer> heap = heapOf(5, 8, 3, 1);
    assertEquals(listOf(1, 3, 5, 8), contents(heap));
  }

  @Test
  public void addStopsSwimmingBelowASmallerParent() {
    ArrayHeap<Integer> heap = heapOf(5, 8, 3, 1, 4);
    assertEquals(listOf(1, 3, 5, 8, 4), contents(heap));
  }

  @Test
  public void removeMovesLastItemToRootAndSinksIt() {
    ArrayHeap<Integer> heap = heapOf(5, 8, 3, 1, 4, 7);
    heap.remove();
    assertEquals(listOf(3, 4, 5, 8, 7), contents(heap));
  }

  @Test
  public void removeClearsTheVacatedSlot() {
    ArrayHeap<Integer> heap = heapOf(5, 8, 3, 1, 4, 7);
    heap.remove();
    assertNull(arrayOf(heap)[5]);
  }

  @Test
  public void addAfterRemoveSwimsToTheRoot() {
    ArrayHeap<Integer> heap = heapOf(5, 8, 3, 1, 4, 7);
    heap.remove();
    heap.add(2);
    assertEquals(listOf(2, 4, 3, 8, 7, 5), contents(heap));
  }

  @Test
  public void addToAFullArrayDoublesItsCapacity() {
    ArrayHeap<Integer> heap = new ArrayHeap<>();
    int capacity = arrayOf(heap).length;
    for (int i = 0; i <= capacity; i++) {  // one more than fits
      heap.add(i);
    }
    assertEquals(2 * capacity, arrayOf(heap).length);
  }

  @Test
  public void reverseComparatorServesLargestFirst() {
    Comparator<Integer> largestFirst = new LargestFirstComparator();
    ArrayHeap<Integer> heap = new ArrayHeap<>(largestFirst);
    heap.add(5);
    heap.add(8);
    heap.add(3);
    heap.add(6);
    assertEquals(8, heap.peek());
  }

  @Test
  public void reverseComparatorRemoveRevealsNextLargest() {
    Comparator<Integer> largestFirst = new LargestFirstComparator();
    ArrayHeap<Integer> heap = new ArrayHeap<>(largestFirst);
    heap.add(5);
    heap.add(8);
    heap.add(3);
    heap.add(6);
    heap.remove();
    assertEquals(6, heap.peek());
  }

  // Builds an ArrayHeap by adding each value in order.
  private static ArrayHeap<Integer> heapOf(int... values) {
    ArrayHeap<Integer> heap = new ArrayHeap<>();
    for (int value : values) {
      heap.add(value);
    }
    return heap;
  }

  private static List<Integer> listOf(int... values) {
    List<Integer> list = new ArrayList<>();
    for (int value : values) {
      list.add(value);
    }
    return list;
  }

  // The filled part of the heap's array, in index order.
  private static List<Integer> contents(ArrayHeap<Integer> heap) {
    Object[] array = arrayOf(heap);
    List<Integer> values = new ArrayList<>();
    for (int i = 0; i < heap.size(); i++) {
      values.add((Integer) array[i]);
    }
    return values;
  }

  private static Object[] arrayOf(ArrayHeap<Integer> heap) {
    return (Object[]) readField(heap, "heap");
  }

  // Reads a private field of an object by reflection.
  private static Object readField(Object object, String name) {
    try {
      Field field = object.getClass().getDeclaredField(name);
      field.setAccessible(true);
      return field.get(object);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }
}
