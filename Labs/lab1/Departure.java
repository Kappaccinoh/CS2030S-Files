class Departure extends Event {
	public Customer customer;
	
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
		str = String.format(": Customer %d departed", this.customer.id);
		return super.toString() + str;
	}
}
