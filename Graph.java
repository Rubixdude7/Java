
/**
 * Write a description of class Graph here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graph
{
    public static int[][] getRandomMatrix(int cells)
    {
        return getRandomMatrix(cells,1,9);
    }
    
    public static int[][] getRandomMatrix(int cells, int min, int max)
    {
        return getRandomMatrix(cells,cells,min,max);
    }
    
    public static int[][] getRandomMatrix(int rows, int columns, int min, int max)
    {
        int[][] result = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(i == j)
                {
                    result[i][j] = 0;
                    result[j][i] = 0;
                    continue;
                }
                int num = rand(min,max);
                result[i][j] = num;
                result[j][i] = num;
            }
        }
        
        return result;
    }
    
    public static int round(double a)
    {
        a += .5;
        return (int) a;
    }
    
    public static int rand(int min, int max)
    {
        int diff = max - min;
        double num = Math.random()*diff;
        num += min;
        return round(num);
    }
    
    public static void main()
    {
        final int a = 5;
        int[][] matrix = getRandomMatrix(a);
        for(int i = 0; i < a; i++)
        {
            for(int j = 0; j < a; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
