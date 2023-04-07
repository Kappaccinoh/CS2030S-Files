/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

class DivisibleBy implements BooleanCondition<Integer> {
  private static int divisor;

  public DivisibleBy(int i) {
    DivisibleBy.divisor = i;
  }
  
  public boolean test(Integer t) {
    int dividend = t.intValue();
    return dividend % DivisibleBy.divisor == 0;
  }
}

// Box.of("hello").filter(new DivisibleBy(10));
