package cs2030s.fp;

/**
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

public interface Transformer<T, U> {
  public abstract U transform(T t);
}
