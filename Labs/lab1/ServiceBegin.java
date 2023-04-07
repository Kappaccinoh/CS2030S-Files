class ServiceBegin extends Event {
	public Customer customer;

	public ServiceBegin(double time, Customer customer) {
		super(time);
		this.customer = customer;
	}

	public Event[] simulate() {
		Customer c = new Customer(this.customer.id);
		c.serviceTime = this.customer.serviceTime;
		int counter = this.customer.counterNumber;
		Bank.setCounterFalse(counter);
		c.counterNumber = this.customer.counterNumber;
		
		double endTime = this.customer.getEndTime(this.getTime());
		return new Event[] {new ServiceEnd(endTime, c)};
	}

	@Override
	public String toString() {
		String str = "";
		str = String.format(": Customer %d service begin (by Counter %d)", this.customer.id, this.customer.counterNumber);
		return super.toString() + str;
	}
	
}
