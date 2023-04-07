package cs2030s.fp;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * CS2030S Lab 7
 * AY22/23 Semester 2
 *
 * Defines the InfiniteList which contains a 
 * head which is lazily evaluated, and another InfiniteList that 
 * is also lazily evaluated.
 *
 * @author Lim Jia Wei (Lab 16H)
 * @version CS2030S AY22/23 Sem 2
 */

public class InfiniteList<T> {
  /** The private final field which contains a Lazy of type Maybe of type T */
  private final Lazy<Maybe<T>> head;

  /** The private final field which contains a Lazy of type InfiniteList of type T */
  private final Lazy<InfiniteList<T>> tail;

  /** The private final field which holds a cached Sentinel instance */
  private static final Sentinel SENTINEL = new Sentinel();

  /** A private constructor to initialise an instance of a InfiniteList */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }


  /** The static nested class Sentinel which marks the end of an InfiniteList */
  private static final class Sentinel extends InfiniteList<Object> {

    /** 
     * Returns the string representation of the Sentinel class
     *
     * @return The String representation of the Sentinel class
     */
    @Override
    public String toString() {
      return "-";
    }

    /**
     * Checks if the current object being called on is of type Sentinel
     *
     * @return A boolean value
     */
    @Override
    public boolean isSentinel() {
      return true;      
    }

    /**
     * Returns the head of a sentinel instance
     *
     * @return NoSuchElement Exception
     */
    @Override
    public Object head() throws NoSuchElementException {
      throw new NoSuchElementException();
    }


    /**
     * Returns the tail of the InfiniteList, expected to be another InfiniteList
     *
     * @return An instance of sentinel
     */
    @Override
    public InfiniteList<Object> tail() {
      return InfiniteList.sentinel();
    }

    /**
     * Returns an InfiniteList which is mapped by a particular transformer
     * 
     * @return An instance of sentinel
     */
    @Override 
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.sentinel();
    }

    /**
     * Returns an InfiniteList containing Objects which is
     * filtered by a particular predicate
     *
     * @return An instance of sentinel
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    /**
     * Returns an InfiniteList containing the first n
     * @param n the number of objects to produce
     * @return An instance of sentinel
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.sentinel();
    }
    
    /**
     * Returns the items in an InfiniteList while the predicate parameter is true
     *
     * @param predicate a boolean condition to compare against
     * @return An instance of sentinel
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    /**
     * Returns a single item of type U given the seed and a Combiner
     * 
     * @param identity the starting seed of the reducer
     * @param accumulator the combiner object
     * @return A single item of type U given the seed and a combiner
     */
    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    /**
     * Returns the long count of how many items are produced from the InfiniteList
     *
     * @return A numerical long value
     */
    @Override
    public long count() {
      return 0;
    }
  }

  /**
   * Returns an InfiniteList given a producer which will continually produce for
   * each step of the InfiniteList
   *
   * @param producer The producer which produces each element of the InfiniteList
   * @return An InfiniteList defined by the producer
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<T>(Lazy.of(() -> Maybe.some(producer.produce())), Lazy.of(() -> generate(producer)));
  }

  /**
   * Returns an InfiniteList given an initial starting value and a transformer
   * which transforms that value each iteration of the InfiniteList
   *
   * @param seed The starting seed
   * @param next The transformer to be applied
   * @return An InfiniteList defined by the seed and transformer
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    Maybe<T> m = Maybe.some(seed);
    return new InfiniteList<T>(Lazy.of(m), Lazy.of(() -> iterate(next.transform(seed), next)));
  }

  /**
   * A private factory method which returns an InfiniteList
   *
   * @param head Value of type T
   * @param tail A producer which produces an InfiniteList
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(() -> Maybe.some(head));
    this.tail = Lazy.of(() -> tail.produce());
  }

  /**
   * A private factory method which returns an InfiniteList
   *
   * @param head A Lazy of Maybe of type T
   * @param tail A Lazy of InfiniteList of type T
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Returns the head of a sentinel instance
   *
   * @return A value of type T
   */
  public T head() {
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  
  /**
   * Returns the tail of the InfiniteList, expected to be another InfiniteList
   *
   * @return An instance of an InfiniteList of type T
   */
  public InfiniteList<T> tail() {
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }


  /**
   * Returns an InfiniteList which is mapped by a particular transformer
   * 
   * @return An instance of an InfiniteList of type R determined by the
   * return type of the transformer
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<>(
      Lazy.of(() -> this.head.get().map(mapper)), 
      Lazy.of(() -> this.tail.get().map(mapper))
    );
  }
  
  /**
   * Returns an InfiniteList containing Objects which is
   * filtered by a particular predicate
   *
   * @return An instance of an InfiniteList whos elements have
   * been filtered by the BooleanCondition
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<>(
      Lazy.of(() -> this.head.get().filter(predicate)),
      Lazy.of(() -> this.tail.get().filter(predicate))
    );
  }

  /**
   * Returns a cached instance of sentinel
   *
   * @return A cached instance of sentinel
   */
  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> s = (InfiniteList<T>) InfiniteList.SENTINEL;
    return s;
  }

  /**
   * Returns an InfiniteList containing the first n elements
   * @param n the number of objects to produce
   * @return An InfiniteList containing n elements
   */
  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.sentinel();
    }
    
    return new InfiniteList<>(
      head,
      Lazy.of(() -> this.head.get().map(x -> this.tail.get().limit(n - 1)).orElseGet(() -> this.tail.get().limit(n)))
    );
  }

  /**
   * Returns the items in an InfiniteList while the predicate parameter is true
   *
   * @param predicate a boolean condition to compare against
   * @return An instance of an InfiniteList containing elements that pass the predicate
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> m = Lazy.of(() -> Maybe.some(this.head()).filter(predicate));

    return new InfiniteList<>(
      m,
      Lazy.of(() -> m.get().map(x -> this.tail().takeWhile(predicate)).orElseGet(() -> InfiniteList.sentinel()))
    );
  }

  /**
   * Checks if the object being called on is an instanece of sentinel
   * @return boolean condition
   */
  public boolean isSentinel() {
    return false;
  }

  /**
   * Returns a single item of type U given the seed and a Combiner
   * 
   * @param identity the starting seed of the reducer
   * @param accumulator the combiner object
   * @return A single item of type U given the seed and a combiner
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    return accumulator.combine(this.tail().reduce(identity, accumulator), this.head());
  }

  /**
   * Returns the long count of how many items are produced from the InfiniteList
   *
   * @return A numerical long value
   */
  public long count() {
    return this.head.get().map(x -> 1).orElse(0) + this.tail.get().count();
  }

  /**
   * Returns a List collection of all elements in the InfiniteList
   *
   * @return A list collection of all elements in the InfiniteList
   */
  public List<T> toList() {
    InfiniteList<T> list = this;
    List<T> l = new ArrayList<T>();
    
    while (!list.isSentinel()) {
      list.head.get().consumeWith(x -> l.add(x));
      list = list.tail.get();
    }
    return l;
  }

  /**
   * Returns the string representation of this object
   * 
   * @return The String representation of this object
   */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
