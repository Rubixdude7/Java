import java.util.ArrayList;
/**
 * Write a description of class MathString here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MathString
{
    private ArrayList<Term> terms;
    private Function func;
    
    public MathString()
    {
        func = Function.NONE;
    }
    
    public MathString(Term... terms)
    {
        this(Function.NONE, terms);
    }
    
    public MathString(Function func,Term... terms)
    {
        this.func = func;
        for(Term term : terms)
        {
           this.terms.add(term);
        }
    }
    
    public MathString(ArrayList<Term> list)
    {
        this(Function.NONE, list);
    }
    
    public MathString(Function func,ArrayList<Term> list)
    {
        this.func = func;
        for(Term term : list)
        {
           terms.add(term);
        }
    }
    
    public MathString derive()
    {
        ArrayList<Term> gPrime = new ArrayList<>();
        Function f;
        for(Term term : terms) //derivative of a sum == sum of the derivatives
        {
            gPrime.add(term.derive());
        }
        return this;
    }
}
