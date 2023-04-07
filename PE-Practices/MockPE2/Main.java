import cs2030s.fp.*;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

class Main {
  public static String reverse(String s) {
    Utilities u = new Utilities();

    Stream<Character> sc = u.split(s);
    return sc.map(c -> Character.toString(c)).reduce("", (x,y) -> y + x);
  }

  public static Stream<String> palindrome(Stream<String> stream) {
    // String s1 = u.split(s).map(c -> Character.toString(c)).reduce("", (x,y) -> x + y);
    // String s2 = u.split(s).map(c -> Character.toString(c)).reduce("", (x,y) -> y + x);
    
    // for each string
    return stream.filter(string -> reverseString(string));
  }

  public static boolean reverseString(String s) {
    String nstr = "";
    char ch;
    for (int i = 0; i < s.length(); i++) {
      ch = s.charAt(i); //extracts each character
      nstr = ch + nstr; //adds each character in front of the existing string
    }
    return s.equals(nstr);
  }

}
