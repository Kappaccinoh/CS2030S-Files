class Arrival extends Event {
  private Customer customer;

  public Arrival(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  public Event[] simulate() {
    Customer c = this.customer;
    if (!Bank.isAvailable()) {
      if (Bank.isQueueFull()) {
        return new Event[] {new Departure(this.getTime(), c)};
      }
      return new Event[] {new JoinQueue(this.getTime(), c)};
    }

    return new Event[] {new ServiceBegin(this.getTime(), c, Bank.availableCounter())};
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s arrived %s", this.customer.toString(), Bank.showQueue());
    return super.toString() + str;
  }
}
