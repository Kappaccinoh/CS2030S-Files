package cs2030s.fp;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

/**
 * CS2030S Lab 6
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

public abstract class Maybe<T> {
  private static final None n = new None();

  static class None extends Maybe<Object> {
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      return obj instanceof None;
    }

    @Override
    public Object get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> bc) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> t) {
      return Maybe.<U>none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> t) {
      return Maybe.<U>none();
    }
    
    @Override
    public <U extends Object> U orElse(U u) {
      return u;
    }

    @Override
    public <U extends Object> U orElseGet(Producer<U> p) {
      return p.produce();
    }

    @Override
    public void ifPresent(Consumer<? super Object> c) {
      return;
    }
  }

  static final class Some<T> extends Maybe<T> {
    private T content;

    private Some(T t) {
      this.content = t;
    }

    public String toString() {
      return "[" + this.content + "]";  
    }
    
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj instanceof Some) {
        Some<?> s = (Some<?>) obj;
        if (this.content == s.content) {
          return true;
        } else if (this.content == null || s.content == null) {
          return false;
        } else {
          return this.content.equals(s.content);
        }
      }
      return false;
    }

    @Override
    public T get() {
      return this.content;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> bc) {
      if (this.content == null) {
        return new Some<T>(null);
      } else if (!bc.test(this.content)) {
        return Maybe.<T>none();
      } else {
        return this;
      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> t) {
      return new Some<U>(t.transform(this.content));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) {
      return new Some<U>(t.transform(this.content).get());

    }

    @Override
    public <U extends T> T orElse(U u) {
      return this.content;
    }

    @Override
    public <U extends T> T orElseGet(Producer<U> p) {
      return this.content;
    }

    @Override
    public void ifPresent(Consumer<? super T> c) {
      if (this.content == null) {
        return;
      }
      c.consume(this.content);
      return;
    }
  }

  public static <U> Maybe<U> none() {
    @SuppressWarnings("unchecked")
    Maybe<U> m = (Maybe<U>) Maybe.n;
    return m;
  }

  public static <U> Maybe<U> some(U u) {
    return new Some<U>(u);
  }

  public abstract boolean equals(Object obj);

  public static <U> Maybe<U> of(U u) {
    if (u == null) {
      return Maybe.<U>none();
    }
    return Maybe.<U>some(u);
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> bc);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> t);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t);

  public abstract <U extends T> T orElse(U u);
  
  public abstract <U extends T> T orElseGet(Producer<U> p);

  public abstract void ifPresent(Consumer<? super T> c);
}

