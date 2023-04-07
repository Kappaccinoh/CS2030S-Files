/**
 * This class encapsulates a customer in the bank
 * and all the various attributes a customer might have
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */

class Customer {
  private int id;
  private double arrivalTime;
  private double serviceTime;
  private Task task;

  public Customer(int id, double arrivalTime, double serviceTime, Task task) {
    this.id = id;
    this.arrivalTime = arrivalTime;
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
    return this.task.toString();
  }
}
