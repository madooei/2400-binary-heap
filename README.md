# Binary Heap

Two binary heap implementations of the `PriorityQueue` ADT. `LinkedHeap` builds the heap from linked nodes, and `ArrayHeap` stores it in an array in level order. A JUnit suite checks both against the `PriorityQueue` contract, then checks the shape of each heap after every kind of swim and sink.

## Prerequisites

- JDK 17+
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      priorityqueue/
        PriorityQueue.java              # the PriorityQueue ADT contract
        LinkedHeap.java                 # binary heap built from linked nodes
        ArrayHeap.java                  # binary heap stored in an array
        LargestFirstComparator.java     # orders integers from largest to smallest
        Main.java                       # demo entry point (the emergency room)
    test/
      priorityqueue/
        PriorityQueueTest.java          # abstract: the PriorityQueue contract suite
        LinkedHeapTest.java             # runs the suite against LinkedHeap, plus shape tests
        ArrayHeapTest.java              # runs the suite against ArrayHeap, plus shape tests
  scripts/
    run.sh                              # compile and run the demo (priorityqueue.Main)
    test.sh                             # compile and run every JUnit test
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh priorityqueue.LinkedHeapTest` — compiles everything and runs only that test class.
- `scripts/run.sh` — compiles everything and runs the `Main` demo.

## What's here

- `priorityqueue.PriorityQueue<T>` — the PriorityQueue contract from the priority queue chapter: `add`, `peek`, `remove`, and `size`. The smallest element has the highest priority.
- `priorityqueue.LinkedHeap<T>` — the heap built from nodes with `left` and `right` references. It finds the next open slot and the last node by writing the position in binary. `add` is recursive and swims the new value up on the way back, so the nodes need no `parent` reference. `sink` is recursive too.
- `priorityqueue.ArrayHeap<T>` — the heap stored in an array in level order. A node's parent and children are found by index arithmetic, and `swim` and `sink` are loops. The array doubles when it fills.
- Both heaps take an optional `Comparator` in a second constructor. A comparator that reverses the order turns the min-heap into a max-heap.
- `priorityqueue.LargestFirstComparator` — a `Comparator<Integer>` that reverses the natural ordering. The demo and the tests pass it to a heap to make a max-heap.
- `priorityqueue.Main` — a runnable demo of the emergency room example, run on both heaps, and a max-heap made with a reversed comparator.
- `priorityqueue.PriorityQueueTest` — the abstract contract suite.
- `priorityqueue.LinkedHeapTest` and `priorityqueue.ArrayHeapTest` — each runs the contract suite against one heap, then checks the comparator constructor and, by reading the heap's private fields, the shape of the heap after each add and remove.
