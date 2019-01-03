import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * <p>Tests the hyper4 function</p>
 * 
 * @author Nolan Aubuchon
 * @version (a version number or a date)
 * @since May 15, 2015
 */
public class Hyper4Tester
{
    public static void main() throws IOException
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter # of iterations:\t");
        int iters = scan.nextInt();
        System.out.print("Enter # of precise digits:\t");
        int digits = scan.nextInt();
        String fileName = "hyper4_"+iters+"_"+digits+".txt";
        FileWriter writ = new FileWriter(fileName);
        for(double base = 0 + 1.0 / Math.pow(10,digits); base <= 1.0; base += 1.0 / Math.pow(10,digits))
        {
            try
            {
                String out = String.format("base:\t%."+digits+"f\t::\t",base);
                writ.write(out+MathFunctions.hyper4(base,iters)+"\n");
            }
            catch(ArithmeticException e)
            {
                continue;
            }
        }
        writ.close();
    }
}
