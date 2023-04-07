/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2022/23
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author Lim Jia Wei
 */
class Point {
	protected double a = 0;
	protected double b = 0;

	public Point(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public double x_coord() {
		return this.a;	
	}

	public double y_coord() {
		return this.b;
	}

	@Override
	public String toString() {
		return "(" + Double.toString(a) + ", " + Double.toString(b) + ")";
	}	
}
