/**
 * This class encapsulates a bank counter or a bank teller.
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */

public class BankCounter {
  private int id;
  private boolean available;

  public BankCounter(int id, boolean available) {
    this.id = id;
    this.available = available;
  }

  public boolean isAvailable() {
    return this.available;
  }

  public int getId() {
    return this.id;
  }

  public void makeAvailable() {
    this.available = true;
  }

  public void makeUnavailable() {
    this.available = false;
  }
}
