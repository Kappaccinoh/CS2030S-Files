/**
 * CS2030S Lab 0: Circle.java
 * Semester 2, 2022/23
 *
 * <p>The Circle class represents a circle with a center 
 * and a radius.
 *
 * @author Lim Jia Wei 
 */
class Circle {
  /** The center of the circle. */
  private Point c;

  /** The radius of the circle (assume positive). */
  private double r;

  /**
   * Constructor for a circle.  Takes in a center c and a 
   * radius r (assume to be positive). 
   *
   * @param c The center of the new circle.
   * @param r The radius of the new circle.
   */
  public Circle(Point c, double r) {
    this.c = c;
    this.r = r;
  }

  /**
   * Checks if a given point p is contained within the circle.
   *
   * @param p The point to test.
   * @return true if p is within this circle; false otherwise.
   */
  public boolean contains(Point p) {
	  double x_val = p.x_coord();
	  double y_val = p.y_coord();

	  double x_bar_sq = Math.pow((x_val - this.c.x_coord()),2);
	  double y_bar_sq = Math.pow((y_val - this.c.y_coord()),2);
	  double abs_distance = Math.sqrt(x_bar_sq + y_bar_sq);

	  // System.out.println(abs_distance);

	  if (abs_distance >= this.r) {
	  	return false;
	  }
	  return true;
  }

  /**
   * Return the string representation of this circle.
   *
   * @return The string representing of this circle.
   */
  public String toString() {
    return "{ center: " + this.c + ", radius: " + this.r + " }";
  }
}
