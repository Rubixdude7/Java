
/**
 * Write a description of class MapTester here.
 * 
 * @author Nolan Aubuchon
 * @version (a version number or a date)
 */
import java.util.*;
public class MapTester
{
    public static void main()
    {
        Map<Integer,String> map = new TreeMap<>();
        map.put(1,"unus");
        map.put(2,"duo");
        map.put(3,"tres");
        map.put(4,"quattuor");
        map.put(5,"quinque");
        map.put(6,"sex");
        map.put(7,"septem");
        map.put(8,"octo");
        map.put(9,"novem");
        map.put(10,"decem");
        for(int i = 1; i <= 10; i++)
        {
            System.out.printf("%2d:\t%8s\n",i,map.get(i));
        }
        
    }
}
