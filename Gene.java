
/**
 * Write a description of class Gene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gene
{
    private String allele_1;
    private String allele_2;
    private String dominantAllele;
    private int code; // 0 -- Homozygous Dominant || 1 -- Homozygous Recessive || 2 - Heterozygous
    private boolean[] badAllele; //true yields the bad gene; [0] represents Homozygous Dominant, [2] represents Heterozygous
    private double fatalityChance; //chance of a bad gene killing or sterilizing an organism
    
    public Gene(String allele_1, String allele_2)
    {
        this(allele_1, allele_2, allele_1);
    }
    
    public Gene(String allele_1, String allele_2, String dominantAllele)
    {
        this(allele_1, allele_2, dominantAllele, new boolean[] {false, false, false}, 0.0); //no bad allele here, just a dummy variable
    }
    
    public Gene(String allele_1, String allele_2, String dominantAllele, boolean[] badAllele, double fatalityChance)
    {
        this.allele_1 = allele_1;
        this.allele_2 = allele_2;
        this.dominantAllele = dominantAllele;
        this.badAllele = badAllele;
        this.fatalityChance = fatalityChance;
        if(allele_1.equals(allele_2) && allele_1.equals(dominantAllele))
            code = 0;        
        else if(allele_1.equals(allele_2))
            code = 1;
        else 
            code = 2;
    }
    
    public Gene(String allele_1, String allele_2, String dominantAllele, String badAllele, double fatalityChance)
    {
        this.allele_1 = allele_1;
        this.allele_2 = allele_2;
        this.dominantAllele = dominantAllele;
        this.fatalityChance = fatalityChance;
        if(allele_1.equals(allele_2) && allele_1.equals(dominantAllele))
            code = 0;        
        else if(allele_1.equals(allele_2))
            code = 1;
        else 
            code = 2;
        if(dominantAllele.equals(badAllele))
          this.badAllele = new boolean[] {true, false, true};
        else
          this.badAllele = new boolean[] {false, true, false};
    }
    
    public String getDominantAllele()
    {
        return dominantAllele;
    }
    
    public String getPaternalAllele()
    {
        return allele_1;
    }
    
    public String getMaternalAllele()
    {
        return allele_2;
    }
    
    public String getRandomAllele()
    {
        int num = (int) (Math.random() + 0.5);
        if(num == 0)
            return allele_1;
        return allele_2;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public double getFatalityChance()
    {
        return fatalityChance;
    }
    
    public boolean checkFatality()
    {
        return fatalityChance > Math.random() && code == 2;
    }
    
    public String toString()
    {
        if(allele_1.equals(allele_2) && allele_1.equals(dominantAllele))
            return "Homozygous " + dominantAllele;          
        else if(allele_1.equals(allele_2))
            return "Homozygous " + allele_1;
        return "Heterozygous " + dominantAllele;
    }
}
