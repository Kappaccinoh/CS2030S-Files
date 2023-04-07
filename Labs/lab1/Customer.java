/**
 * This class encapsulates a customer in the bank
 * and all the various attributes a customer might have
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */

public class Customer {
	// I would have made these private but you cant create copies of objects in java,
	// as a result I'll need to access the previous object to create a new one with the
	// same parameters less changes to some attributes such as customerStatus.
	
	int id;
	double serviceTime;
	int counterNumber;

	public Customer(int id) {
		this.id = id;
	}

	public double getEndTime(double startTime) {
		return startTime + this.serviceTime;
	}

}
