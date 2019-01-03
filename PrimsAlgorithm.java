
/**
 * Java application of Prim's algorithm
 * 
 * @author Nolan Aubuchon
 * @version 1.0
 */
public class PrimsAlgorithm
{
    public static void main()
    {
        main(Graph.getRandomMatrix(10));
    }
    
    public static void main(int[][] matrix)
    {
        System.out.println("Adjacency Matrix: ");
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        int min = Integer.MAX_VALUE;
        int total = 0;
        int x1 = -1, x2 = -1;
        final int ignore = Integer.MAX_VALUE;
        boolean[] flags = new boolean[matrix.length];
        boolean firstSearch = true;
        for(int i = 0; i < flags.length; i++)
        {
            flags[i] = false;
        }
        while(!isSpanningTree(flags))
        {
            for(int i = 0; i < matrix.length; i++)
            {
                for(int j = 0; j < matrix[i].length; j++)
                {
                    if(matrix[i][j] < min)
                    {
                        if(i == j)
                        {
                            continue;
                        }
                        if(firstSearch || flags[i] != flags[j])
                        {
                            min = matrix[i][j];
                            x1 = i;
                            x2 = j;
                        }
                    }
                }
            }
            
            if(firstSearch)
            {
                total += min;
                min = Integer.MAX_VALUE;
                flags[x1] = true;
                flags[x2] = true;
                x1 = -1;
                x2 = -1;
            }
            else if(flags[x1] && flags[x2])
            {
                matrix[x1][x2] = ignore;
                matrix[x2][x1] = ignore;
                min = Integer.MAX_VALUE;
            }
            else
            {
                total += min;
                min = Integer.MAX_VALUE;
                flags[x1] = true;
                flags[x2] = true;
                x1 = -1;
                x2 = -1;
            }
            firstSearch = false;
        }
        System.out.printf("Minimum Cost: %d",total);
    }
    
    public static boolean isSpanningTree(boolean[] flags)
    {
        for(int i = 0; i < flags.length; i++)
        {
            if(flags[i] == false)
                return false;
        }
        return true;
    }
}
