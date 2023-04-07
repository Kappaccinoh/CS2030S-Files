import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Main {
  
  public static Stream<Point> pointStream(Point point, Function<Point, Point> f) {
    return Stream.iterate(point, x -> f.apply(x));
  }

  public static Stream<Point> generateGrid(Point point, int n) {
    // return Stream.iterate(point.getX(), x -> x < n, x -> x + 1).flatMap(x -> Stream.iterate(point.getX(), y -> y < n, y -> y + 1).map(y -> new Point(x,y)));
  
    return Stream.iterate(point, p -> p.getY() < n, p -> new Point(p.getX(), p.getY() + 1)).flatMap(h -> Stream.iterate(point, h -> h.getX() < n, h -> new Point(p.getX() + 1, h.getY())));
  }

  

  public static Stream<Circle> concentricCircles(Circle circle, Function<Double, Double> f) {
    return null;
  }

  public static Stream<Point> pointStreamFromCircle(Stream<Circle> circles) {
    return null;
  }
}
