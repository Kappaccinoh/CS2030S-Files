class ServiceEnd extends Event {
  private Customer customer;
  private int counter;

  public ServiceEnd(double time, Customer customer, int counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  public Event[] simulate() {
    Bank.makeCounterAvailable(this.counter);

    if (Bank.isQueueEmpty()) {
      return new Event[] {new Departure(this.getTime(), this.customer)};
    }

    Customer c = (Customer) Bank.deqCustomer();
    // deq and return [departure, servicebegin]
    return new Event[] {
      new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), c, this.counter)
    };
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s %s done (by S%d)", 
      this.customer.toString(), this.customer.printTask(), this.counter);
    return super.toString() + str;
  }
}
