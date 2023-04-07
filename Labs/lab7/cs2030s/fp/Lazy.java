package cs2030s.fp;

/**
 * CS2030S Lab 6
 * AY22/23 Semester 2
 *
 * Defines the Lazy Class which contains either
 * a value which has already been calculated,
 * or a producer which will produce the value
 * when needed.
 *
 * @author Lim Jia Wei (Lab 16H)
 * @version CS2030S AY 22/23 Sem 2
 */

public class Lazy<T> {
  /** The producer which produces the value of Lazy. */
  private Producer<? extends T> producer;

  /** The value of type T of Lazy. */
  private Maybe<T> value;

  /**
   * A private constructor to initialise Lazy when
   * only a producer is provided and the value not
   * computed yet.
   *
   * @param p A producer of type that extends T.
   */
  private Lazy(Producer<? extends T> p) {
    this.producer = p;
    this.value = Maybe.none();
  }

  /**
   * A private constructor to initialise Lazy when
   * a known value is provided.
   *
   * @param t A defined value which is of type T.
   */
  private Lazy(T t) {
    this.value = Maybe.some(t);
  }

  /**
   * Returns an instance of Lazy which contains a 
   * known value.
   *
   * @param <U> The value's type.
   * @param v The value itself.
   * @return An instance of Lazy which contains v.
   */
  public static <U> Lazy<U> of(U v) {
    return new Lazy<U>(v);
  }

  /**
   * Returns an instance of Lazy which contains an
   * unknown value, but has a producer able to provide it.
   *
   * @param <U> The type that the Producer will produce.
   * @param s The producer of type that extends U.
   * @return An instance of Lazy containing a producer.
   */
  public static <U> Lazy<U> of(Producer<? extends U> s) {
    return new Lazy<U>(s);
  }

  /**
   * Returns the value inside of Lazy, if the value is known the
   * value is returned simply, if the value is unknown, the
   * producer is called to compute the value and return it (Note
   * that the instance of Lazy will store this computed value).
   * 
   * @return The value inside of Lazy.
   */
  public T get() {
    T t = this.value.orElseGet(this.producer);
    this.value = Maybe.some(t);
    return t;
  }

  /**
   * Returns the String representation of the value inside Lazy,
   * regardless of the original type of Lazy, if the value is unknown,
   * "?" is returned instead.
   *
   * @return The string representation of the value inside Lazy.
   */
  @Override
  public String toString() {
    Transformer<T, String> t = x -> String.valueOf(x);
    Maybe<String> m = this.value.map(t);
    return m.orElse("?");
  }

  /**
   * Returns an instance of Lazy which has its value applied by the map
   * function, but the value itself has not been computed and will only
   * compute upon calling of the Lazy::get method.
   *
   * @param <U> The return type of map.
   * @param t A transformer which transforms from type T to type U.
   * @return An instance of Lazy with map applied to its value.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> t) {
    return Lazy.of(() -> t.transform(this.get()));
  }

  /**
   * Returns an instance of Lazy which has its value applied by the flatmap
   * function, but the value itself has not been computed and will only
   * compute upon calling of the Lazy::get method.
   *
   * @param <U> The return type of flatMap.
   * @param t A transformer which transforms from type T to Lazy which
   * type U.
   * @return An instanece of Lazy with flatMap applied to its value. 
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> t) {
    return Lazy.of(() -> t.transform(this.get()).get());
  }

  /**
   * Returns an instance of Lazy of Boolean type which takes in a BooleanCondition
   * which is evaluated when the Lazy::get method is called.
   *
   * @param bc A BooleanCondition of type T.
   * @return An instance of Lazy of Boolean type.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> bc) {
    return Lazy.of(() -> bc.test(this.get()));
  }

  /**
   * Returns a boolean condition dependent on whether the object
   * passed into the Lazy::equals method is of instance Lazy
   * and whether the value inside Lazy is equal to the value
   * of the instance of Lazy that the method is being called on.
   *
   * @param obj The object which is being compared to.
   * @return True or False
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Lazy<?>)) {
      return false;
    }

    Lazy<?> lz = (Lazy<?>) obj;
    if (lz.get() == null) {
      return this.get() == null;
    }
    return lz.get().equals(this.get());
  }

  /**
   * Returns an instance of Lazy of type R with its value computed
   * only on the calling of the Lazy::get method.
   *
   * @param <U> The type of element being combined with the instance of Lazy that
   * the method is being invoked upon.
   * @param <R> The return type of Lazy.
   * @param lazy An instance of Lazy being combined with the instance of Lazy that this
   * method is being invoked upon.
   * @param combiner A Combiner that takes in parameter types T, U and R.
   * @return An instance of Lazy which has Combiner applied to.
   */
  public <U, R> Lazy<R> combine(Lazy<U> lazy, Combiner<? super T, ? super U,? extends R> combiner) {
    return Lazy.of(() -> combiner.combine(this.get(), lazy.get()));    
  }
}
