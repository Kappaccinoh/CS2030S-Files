/**
 * CS2030S PE1 Question 2
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */

class ArrayStack<T> implements Stack<T> {
  // lower the index the lower it is in the stack
  private Object[] stack;
  private int maxSize;

  public ArrayStack(int size) {
    this.maxSize = size;
    this.stack = new Object[size];
  }

  public T pop() {
    for (int j = maxSize - 1; j >= 0; j--) {
      if (this.stack[j] != null) {
        @SuppressWarnings("unchecked")
        T item = (T) this.stack[j];
        this.stack[j] = null;
        return item;
      }
    }
    return null;
  }

  public void push(T t) {
    if (this.getStackSize() != this.maxSize) {
      for (int j = 0; j < this.maxSize; j++) {
        if (this.stack[j] == null) {
          this.stack[j] = t;
          break;
        }
      }
    }
  }
  
  public int getStackSize() {
    int i = 0;
    for (int j = 0; i < this.maxSize; j++) {
      if (this.stack[j] != null) {
        i++;
      } else {
        break;
      }
    }
    return i;
  }

  @Override
  public String toString() {
    if (this.getStackSize() == 0) {
      return "Stack: ";
    }
    String str = "Stack: ";
    int i = 0;
    while (i < this.maxSize) {
      if (this.stack[i] != null)
      str += this.stack[i] + " ";
      i++;
    }
    return str;
  }
}
