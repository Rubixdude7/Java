
/**
 * Write a description of class CharPrinter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class IntegerPrinter
{
    public static void main() throws IOException
    {
        BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        while(num != -1)
        {
            System.out.print("\nEnter an int:\t");
            num =  Integer.parseInt(c.readLine());
            System.out.print(num);
        }
    }
}
