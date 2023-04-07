import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */ 
class BankSimulation extends Simulation {
  public Event[] initEvents;

  public BankSimulation(Scanner sc) {
    this.initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int counterQueueLength = sc.nextInt();
    int bankQueueLength = sc.nextInt();

    Bank b = new Bank(numOfCounters, counterQueueLength, bankQueueLength);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int task = sc.nextInt();
      if (task == 0) {
        Deposit t = new Deposit();
        Customer c = new Customer(id, arrivalTime, serviceTime, t);
        initEvents[id] = new Arrival(arrivalTime, c, b);
        id += 1;
      } else if (task == 1) {
        Withdrawal t = new Withdrawal();
        Customer c = new Customer(id, arrivalTime, serviceTime, t);
        initEvents[id] = new Arrival(arrivalTime, c, b);
        id += 1;
      } else {
        OpenAccount t = new OpenAccount();
        Customer c = new Customer(id, arrivalTime, serviceTime, t);
        initEvents[id] = new Arrival(arrivalTime, c, b);
        id += 1;
      }
    }
  }

  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
