import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

   public static void main( String args[] ) {
      //capture multiple groups
      String input = "Obama";
      String pattern = "(O)(b)(ama)";
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(input);
      m.find();
      for(int i = 0; i <= m.groupCount(); i++)
      {
          System.out.println("Group number " + i + ": " + m.group(i) );
      }
   }
}
