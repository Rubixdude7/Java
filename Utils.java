import static java.lang.System.out;
import java.util.Scanner;
/**
 * Write a description of class Utils here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Utils
{
    public static String read()
    {
        Scanner in = new Scanner(System.in);
        return in.next();
    }
    
    public static String read(String str)
    {
        System.out.print(str);
        return read();
    }
    
    public static int readInt()
    {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    
    public static int readInt(String str)
    {
        System.out.print(str);
        return readInt();
    }
    
    public static void main()
    {
        String myString = read("Enter a string: ");
        int myInt = readInt("Enter an int: ");
        out.println(myString + myInt);
    }
}
