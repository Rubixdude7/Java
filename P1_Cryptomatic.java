/**
 * <b>NOTE: ^ is the bitwise exclusive or (XOR)</b>
 * <b>NOTE: ~ is the bitwise not (NOT)</b>
 * 
 * @author Nolan Aubuchon
 * @version 2/1/2017
 */
import java.util.Scanner;
import java.lang.Short;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class P1_Cryptomatic
{
    public static void main(String[] args) throws IOException
    {
        byte[] key = new byte[2];
        byte[] set = new byte[2];
        int menu = -1;
        String inFile;
        String outFile;
        Scanner scan = new Scanner(System.in);
        FileInputStream fis;
        FileOutputStream fos;
        while(menu != 3)
        {
            System.out.println("1. Encrypt/Decrypt a file with key\n2. Decrypt a file with brute force\n3. QUIT");
            menu = scan.nextInt();
            switch (menu)
            {
                case 1: 
                {
                    System.out.print("Enter name of input file : ");
                    inFile = scan.next();
                    System.out.print("Enter name of output file : ");
                    outFile = scan.next();
                    fis = new FileInputStream(inFile);
                    fos = new FileOutputStream(outFile);
                    System.out.print("Enter a key : ");
                    key = scan.next().getBytes();
                    while((set[0] = (byte)fis.read()) != -1)
                    {
                        if((set[1] = (byte)fis.read()) == -1)
                        {
                            System.err.println("The input file must have an even number of bytes");
                        }
                    }
                }
                break;
                case 2:
                {
                    System.out.print("Enter name of file : ");
                    inFile = scan.next();
                }
                break;
                case 3:
                {
                    System.out.println("Program terminated");
                }
                break;
                default:
                {
                    System.err.println("Invalid choice");
                }
            }
        }
    }
}
