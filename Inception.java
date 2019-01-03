
/**
 * Write a description of class Inception here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inception
{
    private Inception inception;
    private int count;
    
    public Inception()
    {
        this(0);
    }
    
    public Inception(int count)
    {
        inception = count <= 0 ? null : new Inception(count - 1);
        this.count = count;
    }
    
    public int get()
    {
        return count;
    }
    
    public String toString()
    {
        return String.format("Inceptions left: %d",count);
    }
    
    public boolean hasNext()
    {
        return inception != null;
    }
    
    public Inception next()
    {
        return inception;
    }
    
    public static void main()
    {
        Inception ception = new Inception(100);
        while(ception.hasNext())
        {
            System.out.println(ception);
            ception = ception.next();
        }
        System.out.println("done!");
    }
}
