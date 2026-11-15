package priorityqueue;

import java.util.Comparator;

/** Orders integers from largest to smallest, the reverse of their natural ordering. */
public class LargestFirstComparator implements Comparator<Integer> {

  @Override
  public int compare(Integer first, Integer second) {
    return Integer.compare(second, first);
  }
}
