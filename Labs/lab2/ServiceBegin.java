class ServiceBegin extends Event {
  private Customer customer;
  private int counter;

  public ServiceBegin(double time, Customer customer, int counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  public Event[] simulate() {
    Customer c = this.customer;
    Bank.makeCounterUnavailable(this.counter);

    double endTime = c.getEndTime(this.getTime());
    return new Event[] {new ServiceEnd(endTime, c, this.counter)};
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s %s begin (by S%d)", 
      this.customer.toString(), this.customer.printTask(), this.counter);
    return super.toString() + str;
  }

}
