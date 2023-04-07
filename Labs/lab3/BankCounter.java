/**
 * This class encapsulates a bank counter or a bank teller.
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */

class BankCounter implements Comparable<BankCounter> {
  private int id;
  private boolean available;
  private Queue<Customer> q;

  public BankCounter(int id, boolean available, int maxCounterQueueLength) {
    this.id = id;
    this.available = available;
    this.q = new Queue<Customer>(maxCounterQueueLength);
  }

  public boolean isAvailable() {
    return this.available;
  }

  public void makeAvailable() {
    this.available = true;
  }

  public void makeUnavailable() {
    this.available = false;
  }

  // Queue Related Functions
  public boolean isCounterQueueEmpty() {
    return this.q.isEmpty();
  }

  public boolean isCounterQueueFull() {
    return this.q.isFull();
  }

  public String showQueue() {
    return this.q.toString();
  }

  public Customer deqCounterCustomer() {
    return (Customer) this.q.deq();
  }

  public boolean enqCounterCustomer(Customer c) {
    return this.q.enq(c);
  }
  // End

  @Override
  public int compareTo(BankCounter bc) {
    if (this.q.length() < bc.q.length()) {
      return -1;
    }

    if (this.q.length() == bc.q.length()) {
      if (this.id < bc.id) {
        return -1;
      } 
      if (this.id == 0 && this.isCounterQueueFull()) {
        return 1;
      }
    }
    return 0;
  }

  public String toString() {
    String str = "";
    str = String.format("S%d %s", this.id, this.q.toString());
    return str;
  }
}
