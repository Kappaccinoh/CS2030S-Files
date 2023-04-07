public class Bank {
	public static BankCounter[] bankCounters;
	
	// Initialise 'i' number of bank counters in the array
	public Bank(int i) {
		BankCounter[] bcArray = new BankCounter[i];
		this.bankCounters = bcArray;
		for (int j = 0; j < i; j++) {
			BankCounter bc = new BankCounter(j, true);
			bankCounters[j] = bc;
		}
	}

	static public boolean isAvailable() {
		for (int i = 0; i < bankCounters.length; i++) {
			if (bankCounters[i].status == true) {
			return true;
			}
		}

		return false;	
	}

	static public int availableCounter() {
		int c = 0;
		for (int i = 0; i < bankCounters.length; i++) {
			if (bankCounters[i].status == true) {
				break;
			}
			c++;
		}
		return c;

	}

	static public void setCounterTrue(int i) {
		bankCounters[i].status = true;
	}

	static public void setCounterFalse(int i) {
		bankCounters[i].status = false;
	}
}
