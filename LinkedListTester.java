
/**
 * Write a description of class LinkedListTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LinkedListTester
{
    public static void main()
    {
        LinkedList<String> list = new LinkedList<>();
        list.add("A");
        list.add("Certain");
        list.add("Magical");
        list.add("Index");
        System.out.println(list);
        System.out.println("Popping:\t"+list.pop());
        System.out.println(list);
    }
}
