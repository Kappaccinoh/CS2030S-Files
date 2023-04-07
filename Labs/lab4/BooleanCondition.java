/**
 * A conditional statement that returns either true of false.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

interface BooleanCondition<T> {
  public abstract boolean test(T t);
}
