class Arrival extends Event {
  private Customer customer;
  private Bank bank;

  public Arrival(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  public Event[] simulate() {
    if (this.bank.isCountersAvailable()) {
      return new Event[] {new ServiceBegin(this.getTime(), 
        this.customer, this.bank.availableCounter(), this.bank)};
    } else {
      // Counter Queues are not full
      if (this.bank.min() != null) {
        return new Event[] {new JoinCounterQueue(this.getTime(), this.customer, this.bank.min())};
      } else {
        // Bank Queue is not full
        if (!this.bank.isQueueFull()) {
          return new Event[] {new JoinQueue(this.getTime(), this.customer, this.bank)};
        } else {
          // All Queues are full - depart bank
          return new Event[] {new Departure(this.getTime(), this.customer)};
        }
      }
    }
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s arrived %s", this.customer.toString(), this.bank.showQueue());
    return super.toString() + str;
  }
}
