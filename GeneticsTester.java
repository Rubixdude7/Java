import java.util.*;
/**
 * Write a description of class GeneticsTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/*
 *  This programs simulates a simplified version of genetics and inheritance over generations of offspring
 *  The simplifications as follows:
 *      Ignored possibility of gender incompatibility; every offspring is female and only requires another female
 *      Both parents always give birth to four children
 */
public class GeneticsTester
{
    public static void main(String[] args)
    {
        ArrayList<Gene> first, middle, end;
        int[] array = new int[3]; //Stores amount of Homozygous Dominant, Homozygous Recessive, and Heterozygous Dominant      
        Scanner scan = new Scanner(System.in);      
        first = new ArrayList<Gene>();
        middle = new ArrayList<Gene>();
        end = new ArrayList<Gene>();
        for(int i = 0; i < 8; i++)
        {
            first.add(new Gene("big", "small", "big", "small", 1.00)); //leftmost allele is programmed to be the dominant one   
        }
        System.out.print("How many generations? :: ");
        int gen = scan.nextInt();
        for(int i = 0; i < gen; i++)
        {
            for(Gene g : first)
            {
                array[g.getCode()]++;
            }
            System.out.println("Generation " + i + ": Homozygous Dominant: " + array[0] + " || Homozygous Recessive: " + array[1] + " || Heterozygous: " + array[2]);
            array = new int[3];
            while(!first.isEmpty() || first.size() == 1)
            {
                for(int j = 0; j < 2; j++)
                {
                    middle.add(first.remove( (int) Math.random() * first.size())); //removes random element from first and adds it to middle
                }
                for(int j = 0; j < 4; j++)
                {
                    //gets random allele from both parents in middle; repeat for 4 offsping
                    end.add(new Gene(middle.get(0).getRandomAllele(), middle.get(1).getRandomAllele(), middle.get(0).getDominantAllele(), "small", 1.00));
                }
                middle.clear();
            }
            first.clear();
            for(Gene g : end)
            {
                if(!g.checkFatality())
                    first.add(g);
            }
            end.clear();
        }
        for(Gene g : first)
        {
            array[g.getCode()]++;
        }
        System.out.println("Generation " + gen + ": Homozygous Dominant: " + array[0] + " || Homozygous Recessive: " + array[1] + " || Heterozygous: " + array[2]);
    }
}
