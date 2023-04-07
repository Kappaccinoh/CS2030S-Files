/**
 * This class encapsulates a bank counter or a bank teller,
 * in the context of this question it doesn't have many
 * functions but if we look to expand the functionality of
 * this code then the logic would be put in here.
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */

public class BankCounter {
	// Technically it doesnt need an id because the id is already described by the index of the array, but this is just foresight if we choose to do away with the array.
	int id;
	boolean status;

	public BankCounter(int id, boolean status) {
		this.id = id;
		this.status = status;
	}
}
