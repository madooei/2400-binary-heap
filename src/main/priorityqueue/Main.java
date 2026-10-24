package priorityqueue;

import java.util.Comparator;

/**
 * A demo of the two heaps: an emergency room calling patients in by triage
 * level, where 1 is the most urgent and 5 the least.
 */
public final class Main {

  private Main() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    System.out.println("LinkedHeap:");
    triage(new LinkedHeap<Integer>());
    System.out.println("ArrayHeap:");
    triage(new ArrayHeap<Integer>());

    // A comparator that reverses the order turns the min-heap into a max-heap.
    Comparator<Integer> largestFirst = new LargestFirstComparator();
    PriorityQueue<Integer> maxHeap = new ArrayHeap<>(largestFirst);
    maxHeap.add(3);
    maxHeap.add(1);
    maxHeap.add(4);
    maxHeap.add(2);
    maxHeap.add(5);
    System.out.print("max-heap, largest first:");
    while (maxHeap.size() > 0) {
      System.out.print(" " + maxHeap.peek());
      maxHeap.remove();
    }
    System.out.println();
  }

  // Adds five patients' triage levels in arrival order, then calls them in by
  // urgency.
  private static void triage(PriorityQueue<Integer> waiting) {
    waiting.add(3);
    waiting.add(1);  // chest pain
    waiting.add(4);
    waiting.add(2);
    waiting.add(5);  // sprained wrist
    System.out.println("  patients waiting: " + waiting.size());
    System.out.println("  most urgent: level " + waiting.peek());
    System.out.print("  called in:");
    while (waiting.size() > 0) {
      System.out.print(" " + waiting.peek());
      waiting.remove();
    }
    System.out.println();
  }
}
