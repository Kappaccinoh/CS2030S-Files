import java.util.Random;

class RandomPoint extends Point {
	
	public static Random rand = new Random(1);

	public RandomPoint(int minX, int maxX, int minY, int maxY) {
		super(0,0);
		double x_val = rand.nextDouble() * (maxX - minX) + minX;
		double y_val = rand.nextDouble() * (maxY - minY) + minY;
		this.a = x_val;
		this.b = y_val;
	}	

	public static void setSeed(int seed) {
		rand.setSeed(seed);
	}	
}
