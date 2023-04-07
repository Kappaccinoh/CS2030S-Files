class ServiceEnd extends Event {
  private Customer customer;
  private BankCounter bc;
  private Bank bank;

  public ServiceEnd(double time, Customer customer, BankCounter bc, Bank bank) {
    super(time);
    this.customer = customer;
    this.bc = bc;
    this.bank = bank;
  }

  public Event[] simulate() {
    this.bank.makeCounterAvailable(this.bc);

    if (this.bc.isCounterQueueEmpty() && this.bank.isQueueEmpty()) {
      return new Event[] {new Departure(this.getTime(), this.customer)};
    }
    
    if (this.bc.isCounterQueueEmpty() && !this.bank.isQueueEmpty()) {
      Customer bankCustomer = this.bank.deqCustomer();

      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), bankCustomer, this.bc, this.bank)};
    }

    Customer c = this.bc.deqCounterCustomer();

    if (this.bank.isQueueEmpty()) {
      return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(this.getTime(), c, this.bc, this.bank)};
    } else {
      Customer bankCustomer = this.bank.deqCustomer();
      return new Event[] {
        new Departure(this.getTime(), this.customer),
        new ServiceBegin(this.getTime(), c, this.bc, this.bank),
        new JoinCounterQueue(this.getTime(), bankCustomer, this.bc)};
    }
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s %s done (by %s)", 
      this.customer.toString(), this.customer.printTask(), this.bc.toString());
    return super.toString() + str;
  }
}
