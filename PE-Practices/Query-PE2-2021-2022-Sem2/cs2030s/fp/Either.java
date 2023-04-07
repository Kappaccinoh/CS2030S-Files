/**
 * CS2030S PE1 Question 1
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */

/**
 * CS2030S MockPE AY 21/22
 * Defines the Either class which is a generic class that
 * has two types, left and a right.
 *
 * @author Lim Jia Wei
 * @version CS2030S AY21/22
 */

package cs2030s.fp;
import java.util.NoSuchElementException;

public abstract class Either<L, R> {

  /** the private static class that holds a value of type L */
  private static class Left<L, R> extends Either<L, R> {
    private L value;

    public Left(L l) {
      this.value = l;
    }
    
    @Override
    public boolean isLeft() {
      return true;
    }

    @Override
    public boolean isRight() {
      return false;
    }

    @Override
    public L getLeft() throws NoSuchElementException {
      return this.value;
    }

    @Override
    public R getRight() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Left) {
        Left l = (Left) obj;
        if (this.value == null && l.value == null) {
          return true;
        }
        if (this.value == l.value) {
          return true;
        }
      }
      return false;
    }
   
    @Override
    public String toString() {
      return "Left[" + this.value + "]";
    }
    
    @Override
    public <U,V> Either<U,V> map(Transformer<? super L, ? extends U> t1, Transformer<? super R, ? extends V> t2) {
      U u = t1.transform(this.value);
      return new Left<U, V>(u); 
    }
    
    @Override
    public <U, V> Either<U,V> flatMap(
      Transformer<? super L, ? extends Either<? extends U, ? extends V>> t1,
      Transformer<? super R, ? extends Either<? extends U, ? extends V>> t2
    ) {
      Either<U,V> e = (Either<U,V>) t1.transform(this.value);
      return e;
    }

    @Override
    public <U> U fold(Transformer<? super L, ? extends U> t1, Transformer<? super R, ? extends U> t2) {
      U u = t1.transform(this.value);
      return u;
    }

    @Override
    public <U,V> Either<U,V> filterOrElse(BooleanCondition<? super R> bc, Transformer<? super R, ? extends U> t) {
      U u = (U) this.value;
      return new Left<U,V>(u);
    }

  }
  
  /** the private static class that contains a value of type R */
  private static class Right<L, R> extends Either<L, R> {
    private R value;

    public Right(R r) {
      this.value = r;
    }

    @Override
    public boolean isRight() {
      return true;
    }
   
    @Override
    public boolean isLeft() {
      return false;
    }

    @Override
    public R getRight() throws NoSuchElementException {
      return this.value;
    }

    @Override
    public L getLeft() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Right) {
        Right r = (Right) obj;
        if (this.value == null && r.value == null) {
          return true;
        }
        if (this.value == r.value) {
          return true;
        }
      }
      return false;
    }

    @Override
    public String toString() {
      return "Right[" + this.value + "]";
    }
    
    @Override
    public <U, V> Either<U,V> map(Transformer<? super L, ? extends U> t1, Transformer<? super R, ? extends V> t2) {
      V v = t2.transform(this.value);
      return new Right<U, V>(v); 
    }

    @Override
    public <U, V> Either<U,V> flatMap(
      Transformer<? super L, ? extends Either<? extends U, ? extends V>> t1,
      Transformer<? super R, ? extends Either<? extends U, ? extends V>> t2
    ) {
      Either<U,V> e = (Either<U,V>)t2.transform(this.value);
      return e;
    }

    @Override
    public <U> U fold(Transformer<? super L, ? extends U> t1, Transformer<? super R, ? extends U> t2) {
      U u = t2.transform(this.value);
      return u;
    }

    @Override
    public <U,V> Either<U,V> filterOrElse(BooleanCondition<? super R> bc, Transformer<? super R, ? extends U> t) {
      if (bc.test(this.value)) {
        V v = (V) this.value;
        return new Right<U,V>(v);
      }
      U u = t.transform(this.value);
      return new Left<U,V>(u);
    }

   
  }
  
  public static <U,V> Either<U, V> left(U u) {
    return new Left<U, V>(u);
  }

  public static <U,V> Either<U, V> right(V v) {
    return new Right<U, V>(v);
  }

  public abstract boolean isLeft();

  public abstract boolean isRight();

  public abstract L getLeft() throws NoSuchElementException;

  public abstract R getRight() throws NoSuchElementException;

  public abstract boolean equals(Object obj);

  public abstract String toString();

  public abstract <U,V> Either<U, V> map(Transformer<? super L, ? extends U> t1, Transformer<? super R, ? extends V> t2);

  public abstract <U, V> Either<U, V> flatMap(
    Transformer<? super L, ? extends Either<? extends U, ? extends V>> t1,
    Transformer<? super R, ? extends Either<? extends U, ? extends V>> t2
  );

  public abstract <U> U fold(Transformer<? super L, ? extends U> t1, Transformer<? super R, ? extends U> t2);

  public abstract <U,V> Either<U,V> filterOrElse(BooleanCondition<? super R> bc, Transformer<? super R, ? extends U> t);



}
