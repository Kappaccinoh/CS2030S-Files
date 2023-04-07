package cs2030s.fp;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

/**
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

public abstract class Maybe<T> {
  private static None n = new None();

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

    public <U extends Object> U orElse(U u) {
      return u;
    }

    public <U extends Object> U orElseGet(Producer<U> p) {
      return new Some<U>(p.produce()).get();
    }

    public void ifPresent(Consumer<? super Object> c) {
      return;
    }
  }

  static final class Some<T> extends Maybe<T> {
    private T content;

    public Some(T t) {
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
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> t) throws NullPointerException {
      if (this.content == null) {
        throw new NullPointerException();
      }
      return new Some<U>(t.transform(this.content));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) {
      /*
      try {
        return new Some<U>(t.transform(this.content).get());
      }
      catch(NoSuchElementException e) {
        return Maybe.<U>none();
      }
      */
      
      if (t.transform(this.content) instanceof None) {
        return Maybe.<U>none();
      }
      return new Some<U>(t.transform(this.content).get());

    }

    public <U extends T> T orElse(U u) {
      if (this.content != null) {
        return this.content;
      } else {
        return new Some<U>(u).get();
      }
    }

    public <U extends T> T orElseGet(Producer<U> p) {
      if (this.content == null) {
        return p.produce();
      }
      return new Some<T>(this.content).get();
    }

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





