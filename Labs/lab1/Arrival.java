class Arrival extends Event {
	public Customer customer;
	
	public Arrival(double time, Customer customer) {
		super(time);
		this.customer = customer;
	}

	public Event[] simulate() {
		Customer c = new Customer(this.customer.id);
		c.serviceTime = this.customer.serviceTime;
	
		if (!Bank.isAvailable()) {
			return new Event[] {new Departure(this.getTime(), c)};
		}
		
		c.counterNumber = Bank.availableCounter();
		return new Event[] {new ServiceBegin(this.getTime(), c)};	
	}

	@Override
	public String toString() {
		String str = "";
		str = String.format(": Customer %d arrives", this.customer.id);
		return super.toString() + str;
	}	
}

