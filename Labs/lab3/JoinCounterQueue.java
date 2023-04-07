class JoinCounterQueue extends Event {
  private Customer customer;
  private BankCounter bc;

  public JoinCounterQueue(double time, Customer customer, BankCounter bc) {
    super(time);
    this.customer = customer;
    this.bc = bc;
  }

  public Event[] simulate() {
    if (this.bc.enqCounterCustomer(this.customer)) {
      return new Event[] {};
    } else {
      return new Event[] {new Departure(this.getTime(), this.customer)};
    }
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s joined counter queue (at %s)", 
      this.customer.toString(), this.bc, this.bc.showQueue());
    return super.toString() + str;
  }
}
