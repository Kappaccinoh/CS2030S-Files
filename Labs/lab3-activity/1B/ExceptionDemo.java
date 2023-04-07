import java.io.File;
import java.util.Scanner;

class ExceptionDemo {
  public static Scanner openFile(String filename) {
    File f = new File(filename);
    return new Scanner(f);
  }
  public static void main(String[] args) {
    Scanner sc = openFile("hello.txt");
  }
}
