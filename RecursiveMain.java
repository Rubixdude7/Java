
/**
 * Write a description of class RecursiveMain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecursiveMain
{
    public static void main(int num)
    {
        if(num <= 0)    return;
        System.out.println(num);
        main(num-1);
    }
}
