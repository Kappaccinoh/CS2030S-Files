/**
 * @author A0000000X
 */

package cs2030s.fp;
import java.util.NoSuchElementException;
import java.util.Arrays;

public abstract class Reader<T> {
  private static final Empty n = new Empty();

  public static <T> Reader<T> of() {
    return Reader.<T>empty();
  }

  @SafeVarargs
  public static <T> Reader<T> of(T... t) {
    return Reader.<T>notEmpty(t);
  }

  public static <U> Reader<U> empty() {
    @SuppressWarnings("unchecked")
    Reader<U> m = (Reader<U>) Reader.n;
    return m;
  }

  @SafeVarargs
  public static <U> Reader<U> notEmpty(U... u) {
    return new NotEmpty<U>(u);
  }

  public abstract T read();
  public abstract boolean hasNext();
  public abstract String toString();
  public abstract Reader<T> consume();
  public abstract boolean equals(Object obj);
  public abstract <U> Reader<U> map(Immutator<? extends U, ? super T> i);
  public abstract <U> Reader<U> flatMap(Immutator<? extends Reader<? extends U>, ? super T> i);
 
  public static class Empty extends Reader<Object> {
    
    @Override
    public Object read() throws NoSuchElementException {
      throw new NoSuchElementException();
    }
    
    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public String toString() {
      return "EOF";
    }

    @Override
    public Reader<Object> consume() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    public boolean equals(Object obj) {
      return obj instanceof Empty;
    }

    @Override
    public <U> Reader<U> map(Immutator<? extends U, ? super Object> i) {
      return Reader.<U>empty();
    }

    @Override
    public <U> Reader<U> flatMap(Immutator<? extends Reader<? extends U>, ? super Object> i) {
      return Reader.<U>empty();
    }
  }

  public static class NotEmpty<T> extends Reader<T> {
    private Object[] values;
 
    @SafeVarargs
    public NotEmpty(T... t) {
      this.values = new Object[t.length];
      int index = 0;
      for (T i : t) {
        this.values[index] = i;
        index++;
      }
    }

    @Override
    public T read() {
      @SuppressWarnings("unchecked")
      T t = (T) this.values[0];
      return t;
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public String toString() {
      return "Reader";
    }

    @Override
    public Reader<T> consume() throws NoSuchElementException {
      if (this.values.length == 1) {
        return Reader.empty();
      }
      Object[] arr = Arrays.copyOfRange(this.values, 1, this.values.length);
      @SuppressWarnings("unchecked")
      NotEmpty<T> nE = new NotEmpty<T>();
      nE.values = arr;
      return nE;
    }

    public boolean equals(Object obj) {
      return obj instanceof NotEmpty;
    }

    @Override
    public <U> Reader<U> map(Immutator<? extends U, ? super T> i) {
      Object[] objarr = new Object[this.values.length];

      for (int j = 0; j < this.values.length; j++) {
        T item = (T) this.values[j];
        objarr[j] = i.invoke(item);
      }
      @SuppressWarnings("unchecked")
      NotEmpty<U> nE = new NotEmpty<U>();
      nE.values = objarr;
      return nE;
    }

    @Override
    public <U> Reader<U> flatMap(Immutator<? extends Reader<? extends U>, ? super T> i) {
      T item = (T) this.values[0];
      NotEmpty<U> r = (NotEmpty<U>) i.invoke(item);
      Object[] objarr = new Object[this.values.length + r.values.length - 1];

      Object[] arr1 = r.values;
      Object[] arr2 = Arrays.copyOfRange(this.values, 1, this.values.length);
      System.arraycopy(arr1, 0, objarr, 0, arr1.length);
      System.arraycopy(arr2, 0, objarr, arr1.length, arr2.length);

      @SuppressWarnings("unchecked")
      NotEmpty<U> nE = new NotEmpty<U>();
      nE.values = objarr;
      return nE;
    }
  }
}






