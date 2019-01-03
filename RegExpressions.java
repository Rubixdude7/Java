import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/** 
 * @author Nolan Aubuchon
 * @version 1.0
 * @since September 30, 2016
 * @return void
 * @param a text file (.txt)
 */

public class RegExpressions {
   public static void main(String filename) throws IOException{
      final String key = "\\b\\w{3}\\b"; //finds all words consisting of 3 letters
      String text = "";
      FileInputStream in = null;
      int c;
      try
      {
          in = new FileInputStream(filename);
          while((c = in.read()) != -1)
          {
              text += (char)c;
          }
      }catch(IOException e){}
      finally
      {
          in.close();
      }
      Pattern p = Pattern.compile(key);
      Matcher m = p.matcher(text);
      for(int i=1;m.find();i++)
      {
         System.out.printf("Match number %3d:\t%s\n",i,text.substring(m.start(),m.end()));
      }
   }
   public static void main() throws IOException
   {
       main("funny.txt");
   }
}
