
/**
 * Enumeration class FuncEnum - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum FuncEnum
{
    NONE(1.0),
    EXP(Math.E),
    LOG(Math.E),
    SIN(1.0),
    COS(1.0),
    TAN(1.0),
    CSC(1.0),
    SEC(1.0),
    COT(1.0);
    
    private double power;
    private double coef;
    
    FuncEnum(double power)
    {
        this(power, 1.0);
    }
    
    FuncEnum(double power, double coef)
    {
        this.power = power;
        this.coef = coef;
    }
    
    public double getPower()
    {
        return power;
    }
    
    public double getCoef()
    {
        return coef;
    }
}
