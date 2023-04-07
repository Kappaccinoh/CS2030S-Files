/**
 * The Array<T> for CS2030S 
 *
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  Array(int size) {
    @SuppressWarnings("unchecked")
    T[] arr = (T[]) new Comparable<?>[size];
    this.array = arr;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() { // return the smallest T based on compareTo
    T minimum = (T) array[0];

    for (int i = 0; i < array.length; i++) {
      T curr = (T) array[i];
      if (curr.compareTo(minimum) == -1) {
        minimum = curr;
      }
    }

    // if all counters are full, expect minimum to have id == 0 and queue is full
    if (minimum.compareTo(minimum) == 1) {
      return null;
    } else {
      return minimum;
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
