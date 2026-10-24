package priorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The contract test suite for the PriorityQueue ADT, written against the
 * PriorityQueue interface alone. A concrete subclass supplies createQueue() to
 * pick the implementation.
 */
public abstract class PriorityQueueTest {

  private PriorityQueue<Integer> queue;

  protected abstract PriorityQueue<Integer> createQueue();

  @BeforeEach
  public void setup() {
    queue = createQueue();
  }

  @Test
  public void newQueueIsEmpty() {
    assertEquals(0, queue.size());
  }

  @Test
  public void peekOnEmptyQueueThrows() {
    try {
      queue.peek();
      fail("expected NoSuchElementException when peeking an empty queue");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  @Test
  public void removeOnEmptyQueueThrows() {
    try {
      queue.remove();
      fail("expected NoSuchElementException when removing from an empty queue");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  @Test
  public void addGrowsSize() {
    queue.add(7);
    assertEquals(1, queue.size());
  }

  @Test
  public void peekReturnsOnlyItem() {
    queue.add(7);
    assertEquals(7, queue.peek());
  }

  @Test
  public void peekDoesNotChangeSize() {
    queue.add(7);
    queue.peek();
    assertEquals(1, queue.size());
  }

  @Test
  public void removeOnlyItemLeavesQueueEmpty() {
    queue.add(7);
    queue.remove();
    assertEquals(0, queue.size());
  }

  @Test
  public void peekOnEmptiedQueueThrows() {
    queue.add(7);
    queue.remove();
    try {
      queue.peek();
      fail("expected NoSuchElementException when peeking a queue whose last item was removed");
    } catch (NoSuchElementException e) {
      return;
    }
  }

  @Test
  public void peekReturnsSmallestItem() {
    queue.add(5);
    queue.add(8);
    queue.add(3);
    queue.add(6);
    assertEquals(3, queue.peek());
  }

  @Test
  public void peekReturnsSmallestItemAddedFirst() {
    queue.add(3);
    queue.add(5);
    queue.add(8);
    queue.add(6);
    assertEquals(3, queue.peek());
  }

  @Test
  public void peekReturnsSmallestItemAddedLast() {
    queue.add(5);
    queue.add(8);
    queue.add(6);
    queue.add(3);
    assertEquals(3, queue.peek());
  }

  @Test
  public void removeShrinksSize() {
    queue.add(5);
    queue.add(8);
    queue.add(3);
    queue.remove();
    assertEquals(2, queue.size());
  }

  @Test
  public void removeRevealsNextSmallestItem() {
    queue.add(5);
    queue.add(8);
    queue.add(3);
    queue.add(6);
    queue.remove();
    assertEquals(5, queue.peek());
  }

  @Test
  public void addAfterRemoveCanBecomeSmallest() {
    queue.add(5);
    queue.add(8);
    queue.add(3);
    queue.remove();
    queue.add(1);
    assertEquals(1, queue.peek());
  }

  @Test
  public void duplicatesAreKept() {
    queue.add(4);
    queue.add(4);
    assertEquals(2, queue.size());
  }

  @Test
  public void removeOneDuplicateLeavesTheOther() {
    queue.add(4);
    queue.add(4);
    queue.add(5);
    queue.remove();
    assertEquals(4, queue.peek());
  }

  @Test
  public void removingEveryItemServesThemInOrder() {
    queue.add(5);
    queue.add(8);
    queue.add(3);
    queue.add(1);
    queue.add(4);
    queue.add(7);
    assertEquals(listOf(1, 3, 4, 5, 7, 8), drain(queue));
  }

  @Test
  public void manyItemsAreServedInOrder() {
    List<Integer> expected = new ArrayList<>();
    for (int i = 0; i < 101; i++) {
      queue.add((i * 37) % 101);  // each of 0 to 100 once, scrambled
      expected.add(i);
    }
    assertEquals(expected, drain(queue));
  }

  @Test
  public void addNullThrows() {
    try {
      queue.add(null);
      fail("expected IllegalArgumentException when adding null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  // Removes every item from queue and returns them in the order they came out.
  private static List<Integer> drain(PriorityQueue<Integer> queue) {
    List<Integer> items = new ArrayList<>();
    while (queue.size() > 0) {
      items.add(queue.peek());
      queue.remove();
    }
    return items;
  }

  private static List<Integer> listOf(int... values) {
    List<Integer> list = new ArrayList<>();
    for (int value : values) {
      list.add(value);
    }
    return list;
  }
}
