/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  
  private int length;

  public LastDigitsOfHashCode(int i) {
    this.length = i;
  }

  public Integer transform(Object obj) {
    int hashCode = obj.hashCode();
    int power = 1;
    for (int i = 0; i < this.length; i++) {
      power *= 10;
    }
    int ans = hashCode % power;
    if (ans < 0) {
      return ans * -1;
    } else {
      return ans;
    }
  }
}
