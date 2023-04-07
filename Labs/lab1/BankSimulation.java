import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Lim Jia Wei
 * @version CS2030S AY22/23 Semester 2
 */ 
class BankSimulation extends Simulation {
  /** 
   * The array of bankcounter objects in the bank. 
   */
  // public BankCounter[] bankCounters;

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  public Event[] initEvents;

  /** 
   * Constructor for a bank simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public BankSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();

    Bank b = new Bank(numOfCounters);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer c = new Customer(id);
      c.serviceTime = serviceTime;
      initEvents[id] = new Arrival(arrivalTime, c);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
