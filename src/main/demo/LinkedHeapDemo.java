package demo;

import priorityqueue.LinkedHeap;
import priorityqueue.PriorityQueue;

/** A demo of LinkedHeap: an emergency room calling patients in by triage level. */
public final class LinkedHeapDemo {

  private LinkedHeapDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    PriorityQueue<Integer> waiting = new LinkedHeap<>();

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
  }
}
