
/**
 * Write a description of class Function here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Function
{
    public static Function NONE = new Function(FuncEnum.NONE);
    public static Function EXP = new Function(FuncEnum.EXP);
    public static Function LOG = new Function(FuncEnum.LOG);
    public static Function SIN = new Function(FuncEnum.SIN);
    public static Function COS = new Function(FuncEnum.COS);
    public static Function TAN = new Function(FuncEnum.TAN);
    public static Function CSC = new Function(FuncEnum.CSC);
    public static Function SEC = new Function(FuncEnum.SEC);
    public static Function COT = new Function(FuncEnum.COT);
    
    private FuncEnum func;
    private double power;
    private double coef;
    
    public Function(FuncEnum func)
    {
        this(func, func.getPower(), func.getCoef());
    }
    
    public Function(FuncEnum func, double power, double coef)
    {
        this.func = func;
        this.power = power;
        this.coef = coef;
    }
}
