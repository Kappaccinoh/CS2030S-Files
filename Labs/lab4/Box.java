/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lim Jia Wei (Lab 16H)
 */

class Box<T> {
  private final T content;

  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T t) {
    this.content = t;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Box) {
      Box<?> b = (Box<?>) obj;
      if (this.content == b.content) {
        return true;
      } else if (this.content == null || b.content == null) {
        return false;
      } else {
        return this.content.equals(b.content);
      }
    }
    return false;
  }

  @Override
  public String toString() {
    if (this.content == null) {
      return "[]";
    }
    return "[" + this.content + "]";
  }

  public static <U> Box<U> of(U u) {
    if (u == null) {
      return null;
    }
    return new Box<U>(u);
  }

  public static <U> Box<U> empty() {
    @SuppressWarnings("unchecked")
    Box<U> u = (Box<U>) Box.EMPTY_BOX;
    return u;
  }

  public boolean isPresent() {
    return this.content != null;
  }

  public static <U> Box<U> ofNullable(U u) {
    if (u == null) {
      return Box.empty();
    }
    return new Box<U>(u);
  }

  public Box<T> filter(BooleanCondition<? super T> bc) {
    if (this.isPresent() && bc.test(this.content)) {
      return this;
    }
    return Box.empty();
  }

  public <U> Box<U> map(Transformer<? super T, U> t) {
    if (!this.isPresent()) {
      return Box.empty();
    }
    Box<U> newBox = new Box<>(t.transform(this.content));
    return newBox;
  }  


}
