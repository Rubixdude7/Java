import java.io.*;
/**
 * <p>Tests the NumSystem class by printing out Pythagorean triples/p>
 * <p>the the parameter <i>MAX_NUM</i> is an int value which tells Java how far to look for Pythagorean triplets</p>
 * @author Nolan Aubuchon
 * @version 1.1
 * @since May 15, 2015
 */

public class PythangoreanTripletFinder
{
    //MAX_NUM is how far to search for triples e.g. if MAX_NUM == 50, java will search for triples up to 50
    public static void main(int MAX_NUM) throws IOException{
        FileWriter writ = new FileWriter("PythagoreanTriplesto" + MAX_NUM + ".txt");;
        try{
            if(MAX_NUM < 1)
                throw new IllegalArgumentException();
            //sys = {0, 0, 0}
            NumSystem sys = new NumSystem(MAX_NUM, 3);
            while(true){
                sys.increment();
                if(!sys.containsZero() && Math.pow(sys.getInt(0),2) + Math.pow(sys.getInt(1),2) == Math.pow(sys.getInt(2),2)){
                        writ.write(sys.toString()+"\n ");
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Exception caught\nDone!");
        }catch(IllegalArgumentException e){
            System.err.println("Must enter a number 1 or greater!");
        }finally{
            writ.close();
        }
    }
}
