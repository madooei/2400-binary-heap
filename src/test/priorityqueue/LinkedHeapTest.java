package priorityqueue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/** Runs the PriorityQueue contract suite against LinkedHeap. */
public class LinkedHeapTest extends PriorityQueueTest {

  @Override
  protected PriorityQueue<Integer> createQueue() {
    return new LinkedHeap<>();
  }

  @Test
  public void addPlacesFirstItemAtTheRoot() {
    LinkedHeap<Integer> heap = heapOf(5);
    assertEquals(listOf(5), valuesByPosition(heap));
  }

  @Test
  public void removeOnlyItemClearsTheRoot() {
    LinkedHeap<Integer> heap = heapOf(5);
    heap.remove();
    assertNull(rootOf(heap));
  }

  @Test
  public void addFillsTheNextOpenSlot() {
    LinkedHeap<Integer> heap = heapOf(5, 8);
    assertEquals(listOf(5, 8), valuesByPosition(heap));
  }

  @Test
  public void addSwimsSmallerItemUpOneLevel() {
    LinkedHeap<Integer> heap = heapOf(5, 8, 3);
    assertEquals(listOf(3, 8, 5), valuesByPosition(heap));
  }

  @Test
  public void addSwimsSmallerItemUpTwoLevels() {
    LinkedHeap<Integer> heap = heapOf(5, 8, 3, 1);
    assertEquals(listOf(1, 3, 5, 8), valuesByPosition(heap));
  }

  @Test
  public void addStopsSwimmingBelowASmallerParent() {
    LinkedHeap<Integer> heap = heapOf(5, 8, 3, 1, 4);
    assertEquals(listOf(1, 3, 5, 8, 4), valuesByPosition(heap));
  }

  @Test
  public void addKeepsTheTreeComplete() {
    LinkedHeap<Integer> heap = heapOf(5, 8, 3, 1, 4, 7);
    assertEquals(6, countNodes(rootOf(heap)));
  }

  @Test
  public void removeMovesLastItemToRootAndSinksIt() {
    LinkedHeap<Integer> heap = heapOf(5, 8, 3, 1, 4, 7);
    heap.remove();
    assertEquals(listOf(3, 4, 5, 8, 7), valuesByPosition(heap));
  }

  @Test
  public void removeDetachesTheLastNode() {
    LinkedHeap<Integer> heap = heapOf(5, 8, 3, 1, 4, 7);
    heap.remove();
    assertEquals(5, countNodes(rootOf(heap)));
  }

  @Test
  public void addAfterRemoveSwimsToTheRoot() {
    LinkedHeap<Integer> heap = heapOf(5, 8, 3, 1, 4, 7);
    heap.remove();
    heap.add(2);
    assertEquals(listOf(2, 4, 3, 8, 7, 5), valuesByPosition(heap));
  }

  @Test
  public void manyAddsKeepTheTreeComplete() {
    LinkedHeap<Integer> heap = new LinkedHeap<>();
    for (int i = 0; i < 101; i++) {
      heap.add((i * 37) % 101);
    }
    assertEquals(101, valuesByPosition(heap).size());
  }

  @Test
  public void reverseComparatorServesLargestFirst() {
    Comparator<Integer> largestFirst = new LargestFirstComparator();
    LinkedHeap<Integer> heap = new LinkedHeap<>(largestFirst);
    heap.add(5);
    heap.add(8);
    heap.add(3);
    heap.add(6);
    assertEquals(8, heap.peek());
  }

  @Test
  public void reverseComparatorRemoveRevealsNextLargest() {
    Comparator<Integer> largestFirst = new LargestFirstComparator();
    LinkedHeap<Integer> heap = new LinkedHeap<>(largestFirst);
    heap.add(5);
    heap.add(8);
    heap.add(3);
    heap.add(6);
    heap.remove();
    assertEquals(6, heap.peek());
  }

  // Builds a LinkedHeap by adding each value in order.
  private static LinkedHeap<Integer> heapOf(int... values) {
    LinkedHeap<Integer> heap = new LinkedHeap<>();
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

  // The values at positions 1 to size, in order, where the root is position 1
  // and the children of position p are 2p and 2p + 1. Fails with a
  // NullPointerException if a position is missing, so the list also checks
  // that the tree is complete up to size.
  private static List<Integer> valuesByPosition(LinkedHeap<Integer> heap) {
    List<Integer> values = new ArrayList<>();
    for (int position = 1; position <= heap.size(); position++) {
      values.add((Integer) readField(nodeAt(rootOf(heap), position), "value"));
    }
    return values;
  }

  // The node at position, found by walking down from the parent's position.
  private static Object nodeAt(Object root, int position) {
    if (position == 1) {
      return root;
    }
    Object parent = nodeAt(root, position / 2);
    if (position % 2 == 0) {
      return readField(parent, "left");
    }
    return readField(parent, "right");
  }

  private static int countNodes(Object node) {
    if (node == null) {
      return 0;
    }
    return 1 + countNodes(readField(node, "left")) + countNodes(readField(node, "right"));
  }

  private static Object rootOf(LinkedHeap<Integer> heap) {
    return readField(heap, "root");
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
