class Departure extends Event {
  private Customer customer;

  public Departure(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  public Event[] simulate() {
    return new Event[] {};
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s departed", this.customer.toString());
    return super.toString() + str;
  }
}
