/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

class LongerThan implements BooleanCondition<String> {
  private static int limit;

  public LongerThan(int i) {
    LongerThan.limit = i;
  }

  public boolean test(String s) {
    return s.length() > LongerThan.limit;
  }
}
