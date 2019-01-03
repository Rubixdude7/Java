
/**
 * Write a description of class NumSystem here.
 * 
 * @author Nolan Aubuchon 
 * @version (a version number or a date)
 */
public class NumSystem
{
    private final int BASE;
    private int[] nums;
    
    public NumSystem(int base, int numOfNums){
        BASE = base;
        nums = new int[numOfNums];
    }
    
    public boolean isValid(){
        for(int i : nums)
            if( i >= BASE)
                return false;
        return true;
    }
    
    public void increment(){
        increment(nums.length-1);
    }
    
    public void increment(int ref) throws ArrayIndexOutOfBoundsException{
        if (nums[ref] == BASE - 1){
            nums[ref] = 0;
            increment(ref - 1);
        }
        else{
            nums[ref]++;
        }
    }
    
    public String toString(){
        String str = "{";
        for(int i : nums)
            str = str + i + ", ";
        return str.substring(0,str.length()-2) + "}";
    }
    
    public int getInt(int ref) throws ArrayIndexOutOfBoundsException{
        return nums[ref];
    }
    
    public boolean containsZero(){
        for(int i : nums)
            if(i == 0)
                return true;
        return false;
    }
}
