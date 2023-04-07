
/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

interface Transformer<T, U> {
  public abstract U transform(T t);
}



