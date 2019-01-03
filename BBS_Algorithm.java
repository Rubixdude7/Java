/**
 * The Source algorithm used is the Blum Blum Shub Algorithm
 * @see https://www.cs.indiana.edu/~kapadia/project2/node11.html, https://en.wikipedia.org/wiki/Blum_Blum_Shub
 * 
 * I have omitted my name because you asked me to
 * 
 * @version 3/12/17
 */
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;
public class BBS_Algorithm
{
    private static int p=127, q=223; //seed=25; //default seed
    
    /**
     * This method is used only in debugging to make sure the RNG is working properly
     * prints the random numbers to the System.out stream
     * @param iter: number of random numbers, seed: the RNG seed (should be an integer greater than 1)
     * @return void
     */
    public static void nextNumber(int iter, int seed)
    {
        if(iter == 0) {return;}
        System.out.println(String.format("%d\t%d",seed,seed%2));
        nextNumber(iter - 1, (int)Math.pow(seed,2) % (p * q));
    }
    
    /**
     * recurse algorithm that returns a string of random digits
     * @param iter: the number of random digits, seed: the RNG seed (should be an integer greater than 1)
     * @return a String of random bits
     */
    private static String getBinaryDigit(int iter, int seed)
    {
        if(iter == 0) {return "";}
        String result = seed % 2 == 0 ? "0" : "1";
        return result + getBinaryDigit(iter - 1, (int)Math.pow(seed,2) % (p * q));
    }
    
    /**
     * Writes a 512-bit file of random digits in the same directory titled output_<seed>.txt
     * @param seed: the RNG seed (should be an integer greater than 1)
     * @return void
     */
    public static void main(int seed) throws IOException
    {
        FileWriter writ = new FileWriter("output_" + seed + ".txt");
        String output = getBinaryDigit(512, seed);
        writ.write(output);
        writ.close();
    }
    
    /**
     * In the windows command line, type "Java BBS_Algorithm <seed>" (without quotes) replace <seed> with an integer greater than 1.
     * It will be the seed of the Random Number Generator
     */
    public static void main(String[] args) throws IOException
    {
        main(Integer.parseInt(args[0]));
    }
}
