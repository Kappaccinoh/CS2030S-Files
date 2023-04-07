class JoinQueue extends Event {
  private Customer customer;
  private Bank bank;

  public JoinQueue(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  public Event[] simulate() {
    if (this.bank.enqCustomer(this.customer)) {
      return new Event[] {};
    } else { // Queue is full, exit bank
      return new Event[] {new Departure(this.getTime(), this.customer)};
    }
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s joined bank queue %s", 
      this.customer.toString(), this.bank.showQueue());
    return super.toString() + str;
  }
}
