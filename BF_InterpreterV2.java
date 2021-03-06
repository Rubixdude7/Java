import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.lang.IllegalArgumentException;
/**
 * Runs BF code BF codes have the .bf file extension
 * V2 version doesn't require the additional BivalvicInputStream class
 * @author Nolan Aubuchon
 * @version (a version number or a date)
 */
public class BF_InterpreterV2
{
    private static char[] chars = new char[30000];
    private static int ptr = 0;
    private static String filename;
    private static Scanner scan = new Scanner(System.in);
    private static FileInputStream in;
    private static char c;    
    public static void main(String[] args) throws IOException
    {
        if(args.length == 0) {filename = findFileName();}
        else                 {filename = args[0];}
        in = new FileInputStream(filename);
        while((c = (char) in.read()) != (char)(-1))
        {
            switch(c)
            {
                case '>': ptr++;                                                        break;
                case '<': ptr--;                                                        break;
                case '+': chars[ptr]++;                                                 break;
                case '-': chars[ptr]--;                                                 break;
                case '.': System.out.print(chars[ptr]);                                 break;
                case ',': chars[ptr] = (char) scan.nextInt();                           break;
                case '[': {if(chars[ptr] == '\u0000') {findRightBracket();}}            break;
                case ']': {if(chars[ptr] != '\u0000') {findLeftBracket(); }}            break;
                default :                                                               break;
            }
        }
        in.close();
    }
    
    private static String findFileName()
    {
        System.out.print("Enter file name:\t");
        filename = scan.next();
        return filename;
    }
    
    private static void findRightBracket() throws IOException, IllegalArgumentException
    {
        int bracket = 0;
        do
        {
            if(c == '[')      {bracket++;}
            else if(c == ']') {bracket--;}
        }
        while
        (
                (c = (char) in.read()) != (char)(-1)        &&      !(c == ']' && bracket == 1)
        );
        if(c == -1) {throw new IllegalArgumentException();}       
    }
    
    private static void findLeftBracket() throws IOException, IllegalArgumentException
    {
        int bracket = 0;
        do
        {
            if(c == ']')        {bracket++;}
            else if(c == '[')   {bracket--;}
        }
        while
        (
            (c = (char) readBack()) != (char)(-1)            &&          !(c == '[' && bracket == 1)
        );
        if (c == -1) {throw new IllegalArgumentException();}
    }
    
    private static int readBack() throws IOException
    {
        try
        {
            in.getChannel().position(in.getChannel().position() - 2);
            return in.read();
        }
        catch(IllegalArgumentException e) //if you start to read backwards from the first byte of a file
        {
            return -1;
        }
    }
}

