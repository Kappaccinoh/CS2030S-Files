class ServiceEnd extends Event {
	public Customer customer;

	public ServiceEnd(double time, Customer customer) {
		super(time);
		this.customer = customer;
	}

	public Event[] simulate() {
		Customer c = new Customer(this.customer.id);
		c.serviceTime = this.customer.serviceTime;
		int counter = this.customer.counterNumber;
		Bank.setCounterTrue(counter);	
		return new Event[] {new Departure(this.getTime(), c)};
	}

	@Override
	public String toString() {
		String str = "";
		str = String.format(": Customer %d service done (by Counter %d)", this.customer.id, this.customer.counterNumber);
		return super.toString() + str;
	}
	
}
