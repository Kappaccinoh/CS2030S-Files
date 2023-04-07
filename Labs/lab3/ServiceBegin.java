class ServiceBegin extends Event {
  private Customer customer;
  private BankCounter bc;
  private Bank bank;

  public ServiceBegin(double time, Customer customer, BankCounter bc, Bank bank) {
    super(time);
    this.customer = customer;
    this.bc = bc;
    this.bank = bank;
  }

  public Event[] simulate() {
    this.bank.makeCounterUnavailable(this.bc);
    double endTime = this.customer.getEndTime(this.getTime());
    return new Event[] {new ServiceEnd(endTime, this.customer, this.bc, this.bank)};
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s %s begin (by %s)", 
      this.customer.toString(), this.customer.printTask(), this.bc.toString());
    return super.toString() + str;
  }

}
