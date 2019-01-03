import java.io.*;
import java.util.*;
/**
 * <p><b>Using the Scanner class isn't the only way to get input in Java</b></p>
 * <p>the System Console cannot be used in an IDE, so a BufferedReader is used instead</p>
 * <p>A BufferedReader uses another Reader as its parameter, which allows it to read entire lines of text instead of one character at a time</p>
 * @author Nolan Aubuchon
 * @version 2.0
 * @since May 15, 2016
 */
public class ConsoleInput
{
    public static void main() throws IOException{
        Console c = System.console();
        if(c == null){
            System.out.println("Cannot be done in an IDE");
            BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a float: ");
            String num = d.readLine();
            System.out.print("Enter a format: ");
            String format = d.readLine();
            System.out.printf(format,Float.parseFloat(num));
        }
        else{
            String num = c.readLine("Enter a float: ");
            String format = c.readLine("Enter a format: "); //Examples: "%.2f" prints two decimal places
            System.out.printf(format,Float.parseFloat(num));
        }   
    }
}
