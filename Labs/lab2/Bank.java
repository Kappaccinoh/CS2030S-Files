public class Bank {
  private static BankCounter[] bankCounters;
  private static Queue q;

  // Initialise 'i' number of bank counters in the array
  // Initialise 'm' number of max length of queue

  public Bank(int i, int m) {
    this.bankCounters = new BankCounter[i];

    for (int j = 0; j < i; j++) {
      BankCounter bc = new BankCounter(j, true);
      bankCounters[j] = bc;
    }

    q = new Queue(m);
  }

  // Functions for BankCounter Class

  public static boolean isAvailable() {
    for (int i = 0; i < bankCounters.length; i++) {
      if (bankCounters[i].isAvailable()) {
        return true;
      }
    }

    return false;
  }

  public static int availableCounter() {
    for (int i = 0; i < bankCounters.length; i++) {
      if (bankCounters[i].isAvailable()) {
        return bankCounters[i].getId();
      }
    }
    return -1;


  }

  public static void makeCounterAvailable(int i) {
    bankCounters[i].makeAvailable();
  }

  public static void makeCounterUnavailable(int i) {
    bankCounters[i].makeUnavailable();
  }

  // End

  // Functions for Queue Class
  public static boolean isQueueEmpty() {
    return q.isEmpty();
  }

  public static boolean isQueueFull() {
    return q.isFull();
  }

  public static String showQueue() {
    return q.toString();
  }

  public static Object deqCustomer() {
    return q.deq();
  }

  public static boolean enqCustomer(Customer c) {
    return q.enq(c);
  }

  // End

}
