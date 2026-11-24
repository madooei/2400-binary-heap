package demo;

import priorityqueue.ArrayHeap;
import priorityqueue.LargestFirstComparator;
import priorityqueue.PriorityQueue;

/** A demo of ArrayHeap: an emergency room calling patients in by triage level. */
public final class ArrayHeapDemo {

  private ArrayHeapDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    PriorityQueue<Integer> waiting = new ArrayHeap<>();

    // Five patients arrive, in this order of triage levels. Level 1 is the
    // most urgent and level 5 the least.
    waiting.add(3);
    waiting.add(1);  // chest pain
    waiting.add(4);
    waiting.add(2);
    waiting.add(5);  // sprained wrist

    // Expect 5 patients, and level 1 as the most urgent.
    System.out.println("patients waiting: " + waiting.size());
    System.out.println("most urgent: level " + waiting.peek());

    // Expect the patients called in by urgency: 1 2 3 4 5.
    System.out.print("called in:");
    while (waiting.size() > 0) {
      System.out.print(" " + waiting.peek());
      waiting.remove();
    }
    System.out.println();

    // A comparator that reverses the order turns the min-heap into a max-heap.
    PriorityQueue<Integer> maxHeap = new ArrayHeap<>(new LargestFirstComparator());
    maxHeap.add(3);
    maxHeap.add(1);
    maxHeap.add(4);
    maxHeap.add(2);
    maxHeap.add(5);

    // Expect the largest first: 5 4 3 2 1.
    System.out.print("max-heap, largest first:");
    while (maxHeap.size() > 0) {
      System.out.print(" " + maxHeap.peek());
      maxHeap.remove();
    }
    System.out.println();
  }
}
