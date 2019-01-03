
/**
 * <p>Tests formats on the <i>System.out.printf(...)</i> function</p>
 * 
 * @author Nolan Aubuchon
 * @version (a version number or a date)
 */
public class FormatTester
{
    public static void main()
    {
        double[] stuff = {1.324, 100.236, 10000.2}; // not = [1, 2, 3];
        for(int i = 0; i < stuff.length; i++)
        {
            System.out.printf("Array[%d] : %8.2f\n",i,stuff[i]);
        }
        System.out.println(String.format("number:\t%d",40));
        //testing some other junk
        for(;;){break;} //works. Infinite loop if not for "break;"
        EnumTester.main(); //calling the main Method of another class works! Hooray!
    }
}
