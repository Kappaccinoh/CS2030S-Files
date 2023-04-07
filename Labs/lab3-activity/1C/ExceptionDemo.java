// TODO: Create your own exception called MyOwnException
class ExceptionDemo {
  
  public class MyOwnException extends Exception {

  }

  public static void foo() {
	  throw new MyOwnException();
  }
  public static void main(String[] args) {
	foo();
  }
}
