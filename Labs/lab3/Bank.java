class Bank {
  private Array<BankCounter> bankCounters;
  private Queue<Customer> q;
  private int numOfCounters;
  private int maxCounterQueueLength;
  private int maxBankQueueLength;

  public Bank(int numOfCounters, int maxCounterQueueLength, int maxBankQueueLength) {
    Array<BankCounter> arr = new Array<>(numOfCounters);
    this.bankCounters = arr;

    for (int index = 0; index < numOfCounters; index++) {
      BankCounter bc = new BankCounter(index, true, maxCounterQueueLength);
      this.bankCounters.set(index, bc);
    }
    this.q = new Queue<Customer>(maxBankQueueLength);

    this.numOfCounters = numOfCounters;
    this.maxCounterQueueLength = maxCounterQueueLength;
    this.maxBankQueueLength = maxBankQueueLength;
  }

  // Functions for bankCounters Array;

  public boolean isCountersAvailable() {
    for (int i = 0; i < numOfCounters; i++) {
      if (this.bankCounters.get(i).isAvailable()) {
        return true;
      }
    }
    return false;
  }

  public BankCounter availableCounter() {
    for (int i = 0; i < numOfCounters; i++) {
      if (this.bankCounters.get(i).isAvailable()) {
        return bankCounters.get(i);
      }
    }
    return null;
  }

  public void makeCounterAvailable(BankCounter bc) {
    bc.makeAvailable();
  }

  public void makeCounterUnavailable(BankCounter bc) {
    bc.makeUnavailable();
  }

  // Returns null if all BankCounter queues are full, if not,
  // Returns the BankCounter with the shortest queue.
  public BankCounter min() {
    return this.bankCounters.min();  
  }

  // End

  // Functions for Queue Class
  public boolean isQueueEmpty() {
    return this.q.isEmpty();
  }

  public boolean isQueueFull() {
    return this.q.isFull();
  }

  public String showQueue() {
    return this.q.toString();
  }

  public Customer deqCustomer() {
    return (Customer) this.q.deq();
  }

  public boolean enqCustomer(Customer c) {
    return this.q.enq(c);
  }

  // End

}
