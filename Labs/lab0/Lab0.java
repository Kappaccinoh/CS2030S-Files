import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 2, 2022/23
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author Lim Jia Wei 
 */

class Lab0 {

  public static double estimatePi(int numOfPoints, int seed) {
	int num_within = 0;
	Point p = new Point(0.5,0.5);
	Circle c = new Circle(p, 0.5);

	RandomPoint.setSeed(seed);
	
	for (int i = 0; i < numOfPoints; i++) {
		if (c.contains(new RandomPoint(0,1,0,1))) {
			num_within++;
		}	
	}	

	return (double) 4.0 * num_within / numOfPoints;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
