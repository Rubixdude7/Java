import java.util.ArrayList;
/**
 * Write a description of class Term here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Term
{
    private Function func;
    private ArrayList<Term> args; //for the recursive case
    private ArrayList<Part> product; //for the base case  || the term x^2 * y^2 is composed of 2 parts -- x^2 & y^2
    
    public Term derive()
    {
        return this;
    }
}
