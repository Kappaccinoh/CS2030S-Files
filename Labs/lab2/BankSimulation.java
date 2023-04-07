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
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int queueLength = sc.nextInt();

    Bank b = new Bank(numOfCounters, queueLength);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int task = sc.nextInt();
      Customer c = new Customer(id, serviceTime, task);
      initEvents[id] = new Arrival(arrivalTime, c);
      id += 1;
    }
  }

  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
