
/**
 * <p>Additional Math Functions. They are static, so MathFunctions doesn't have to be instantiated</p>
 * 
 * @author Nolan Aubuhon
 * @version 1.0
 * @since May 15, 2015
 */
public class MathFunctions
{
    /**
     * <p>A tetration, or superexponential function</p>
     * <p>for example, hyper4(3.0,3) returns Math.pow(3,Math.pow(3,3)) or 3^(3^3)
     */
    public static double hyper4(double base, int iters)
    {
        if(iters < 0)
            throw new ArithmeticException();
        else if(iters == 0)
            return 1.0;
        else
            return Math.pow(base,hyper4(base,iters-1));
    }
    
    public static void main()
    {
        System.out.println(hyper4(3.0,3));
    }
}
