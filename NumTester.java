
/**
 * Write a description of class NumTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NumTester
{
    public static void main(){
        System.out.println(0x7FFFFFFF); //max integer value because loop-around
        System.out.println(0x80000000); //min integer value because loop-around
        System.out.println(0x7FFFFFFF > 0x80000000);
        System.out.println(Integer.decode("0x7FFFFFFF"));
        System.out.println(Integer.toHexString(0x7FFFFFFF));
    }
}
