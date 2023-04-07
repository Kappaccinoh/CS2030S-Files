import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Main {
  
  public static Stream<Point> pointStream(Point point, Function<Point, Point> f) {
    return Stream.iterate(point, x -> f.apply(x));
  }

  public static Stream<Point> generateGrid(Point point, int n) {  
    return Main.pointStream(point, p -> new Point(p.getX() + 1, p.getY())).limit(n)
      .flatMap(newPoint -> pointStream(newPoint, p -> new Point(p.getX(), p.getY() + 1)).limit(n));
  }

  public static Stream<Circle> concentricCircles(Circle circle, Function<Double, Double> f) {
    return Stream.iterate(circle, x -> new Circle(x.getCenter(), f.apply(x.getRadius())));
  }

  public static Stream<Point> pointStreamFromCircle(Stream<Circle> circles) {
    return circles.map(circle -> circle.getCenter());
  }
}
