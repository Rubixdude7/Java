import java.lang.Math;
/**
 * Complex Numbers in Java
 *
 * @author Nolan Aubuchon
 * @version April 26, 2020
 */
public class Complex
{
    private double real; //the pure real part
    private double complex; //the pure complex part
    
    public Complex()
    {
        real = 0;
        complex = 0;
    }
    
    public Complex(double real)
    {
        this.real = real;
        complex = 0;
    }
    
    public Complex(double real, double complex)
    {
        this.real = real;
        this.complex = complex;
    }
    
    public double real()
    {
        return real;
    }
    
    public double real(double real)
    {
        return (this.real = real);
    }
    
    public double complex()
    {
        return complex;
    }
    
    public double complex(double complex)
    {
        return (this.complex = complex);
    }
    
    public Complex add(Complex c)
    {
        return new Complex(real + c.real(), complex + c.complex());
    }
    
    public static Complex add(Complex c, Complex d)
    {
        return c.add(d);
        //return new Complex(c.real() + d.real(),c.complex() + d.complex());
    }
    
    public Complex sub(Complex c)
    {
        return new Complex(real - c.real(), complex - c.complex());
    }
    
    public static Complex sub(Complex c, Complex d)
    {
        return c.sub(d);
        //return new Complex(c.real() - d.real(),c.complex() - d.complex());
    }
    
    /**
     *  The multiplication operator for complex numbers
     *  (a + bi) * (c + di) = ac - bd + adi + bci
     */
    public Complex mult(Complex c)
    {
        return new Complex(real * c.real() - complex * c.complex(),real * c.complex() + complex * c.real());
    }
    
    public static Complex mult(Complex c, Complex d)
    {
        return c.mult(d);
        //return new Complex(c.real() * d.real() - c.complex() * d.complex(),c.real() * d.complex() + c.complex() * d.real());
    }
    
    /**
     *  The division operator for complex numbers
     *  (a + bi) / (c + di) = a / (c + di) + bi / (c + di)
     *  
     *  a / (c + di) = a(c - di) / (c + di)(c - di) = (ac - adi) / (c^2 + d^2) = ac / (c^2 + b^2) - [i] ad / (c^2 + d^2)
     *  
     *  bi / (c + di) = bd / (c^2 + d^2) + [i] bc / (c^2 + d^2)
     *  
     *  (a + bi) / (c + di) = (ac + bd) / (c^2 + d^2) + [i] (bc - ad) / (c^2 + d^2)
     */
    public Complex div(Complex c)
    {
        return new Complex(  (real * c.real() + complex * c.complex()) / (Math.pow(c.real(),2) + Math.pow(c.complex(),2)),
        
        (complex * c.real() - real * c.complex()) / (Math.pow(c.real(),2) + Math.pow(c.complex(),2)));
    }
    
    public static Complex div(Complex c, Complex d)
    {
        return c.div(d);
        /*return new Complex(  (c.real() * d.real() + c.complex() * d.complex()) / (Math.pow(d.real(),2) + Math.pow(d.complex(),2)),
        
        (c.complex() * d.real() - c.real() * d.complex()) / (Math.pow(d.real(),2) + Math.pow(d.complex(),2)));*/
    }
    
    /**
     *  returns the square of the complex number
     */
    public Complex sqr()
    {
        return mult(this);
    }
    
    public static Complex sqr(Complex c)
    {
        return c.sqr();
    }
    
    /**
     *  The exponent operator, but only for integer powers
     */
    public Complex pow(int i)
    {
        if(i < 0) return powinv(-i);
        else if(i == 0) return new Complex(1);
        else if(i == 1) return this;
        else return Complex.mult(this,pow(i - 1));
    }
    
    /**
     *  The inverse exponent operator
     */
    private Complex powinv(int i)
    {
        return Complex.div(new Complex(1),Complex.pow(this,i));
    }
    
    public static Complex pow(Complex c, int i)
    {
        return c.pow(i);
    }
    
    /**
     *  returns the absolute value (distance from zero in the complex plane)
     */
    public double abs()
    {
        return Math.sqrt(Math.pow(real,2) + Math.pow(complex,2));
    }
    
    public static double abs(Complex c)
    {
        return c.abs();
    }
    
    /**
     *  The exponential function for complex numbers
     *  e ^ (a + bi) = (e ^ a) * (e ^ bi) = (e ^ a) * [cos b + i*sin b]
     */
    public static Complex exp(Complex c)
    {
        return c.exp();
        //return mult(new Complex(Math.exp(c.real())),    new Complex(Math.cos(c.complex()),Math.sin(c.complex())));
    }
    
    public Complex exp()
    {
        return mult(new Complex(Math.exp(real)),    new Complex(Math.cos(complex),Math.sin(complex)));
    }
    
    public Complex sin()
    {
        return new Complex(Math.sin(real) * Math.cosh(complex),Math.cos(real) * Math.sinh(complex));
    }
    
    public static Complex sin(Complex c)
    {
        return c.sin();
        //return new Complex(Math.sin(c.real()) * Math.cosh(c.complex()),Math.cos(c.real()) * Math.sinh(c.complex()));
    }
    
    public Complex cos()
    {
        return new Complex(Math.cos(real) * Math.cosh(complex), (-1) * Math.sin(real) * Math.sinh(complex));
    }
    
    public static Complex cos(Complex c)
    {
        return c.cos();
    }
    
    public Complex tan()
    {
        return Complex.div(new Complex(Math.tan(real),Math.tanh(complex)),
                            new Complex(1,(-1) * Math.tan(real) * Math.tanh(complex)));
    }
    
    public static Complex tan(Complex c)
    {
        return c.tan();
    }
    
    public Complex sinh()
    {
        return new Complex(Math.sinh(real) * Math.cos(complex),Math.cosh(real) * Math.sin(complex));
    }
    
    public static Complex sinh(Complex c)
    {
        return c.sinh();
    }
    
    public Complex cosh()
    {
        return new Complex(Math.cosh(real) * Math.cos(complex),Math.sinh(real) * Math.sin(complex));
    }
    
    public static Complex cosh(Complex c)
    {
        return c.cosh();
    }
    
    public Complex tanh()
    {
        return Complex.div(new Complex(Math.tanh(real),Math.tan(complex)),
                            new Complex(1,Math.tanh(real) * Math.tan(complex)));
    }
    
    public static Complex tanh(Complex c)
    {
        return c.tanh();
    }
    
    public String toString()
    {
        return complex >= 0 ? real + " + " + complex + "i" : real + " - " + (-complex) + "i";
        //return real + " + " + complex + "i";
    }
}
