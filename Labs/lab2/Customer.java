/**
 * This class encapsulates a customer in the bank
 * and all the various attributes a customer might have
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */

public class Customer {
  private int id;
  private double serviceTime;
  private int task;

  public Customer(int id, double serviceTime, int task) {
    this.id = id;
    this.serviceTime = serviceTime;
    this.task = task;
  }

  public double getEndTime(double startTime) {
    return startTime + this.serviceTime;
  }

  public String toString() {
    String str = "";
    str = String.format("C%d", this.id);
    return str;
  }

  public String printTask() {
    if (this.task == 1) {
      return "Withdrawal";
    } else {
      return "Deposit";
    }
  }
}
