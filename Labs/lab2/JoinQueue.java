class JoinQueue extends Event {
  private Customer customer;

  public JoinQueue(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  public Event[] simulate() {
    Customer c = this.customer;

    if (Bank.enqCustomer(c)) {
      return new Event[] {};
    } else { // Queue is full, exit bank
      return new Event[] {new Departure(this.getTime(), c)};
    }
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s joined queue %s", this.customer.toString(), Bank.showQueue());
    return super.toString() + str;
  }
}
