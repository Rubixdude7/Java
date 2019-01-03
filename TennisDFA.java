
/**
 * Write a description of class TennisDFA here.
 * 
 * @author Nolan Aubuchon
 * @version September 10, 2016
 */

import java.util.Scanner;

public class TennisDFA
{
    private static final int q0_0 = 0;
    private static final int q0_15 = 1;
    private static final int q0_30 = 2;
    private static final int q0_40 = 3;
    private static final int q15_0 = 4;
    private static final int q15_15 = 5;
    private static final int q15_30 = 6;
    private static final int q15_40 = 7;
    private static final int q30_0 = 8;
    private static final int q30_15 = 9;
    private static final int q30_30 = 10;
    private static final int q30_40 = 11;
    private static final int q40_0 = 12;
    private static final int q40_15 = 13;
    private static final int q40_30 = 14;
    private static final int q40_40 = 15;
    private static final int qAdvantageS = 16;
    private static final int qAdvantageO = 17;
    private static final int qSWin = 18;
    private static final int qOWin = 19;
    
    private static int state;
    
    public static void main()
    {
        System.out.println("bob");
        Scanner scan = new Scanner(System.in);
        int menu = -1;
        String input;
        while(menu != 2)
        {
            System.out.println("1.\tEnter a string to recognize\n2.\tQUIT\n");
            menu = scan.nextInt();
            if(menu == 1)
            {
                System.out.print("Please enter a String:\t");
                input = scan.next();
                process(input);
            }
            else if(menu != 2)
            {
                System.err.println("Not a valid choice!");
            }
            
        }
    }
    
    static private int delta(int s, char c)
    {
        switch (s) 
        {
            case q0_0 : switch (c)
            {
                case 'S' : return q15_0;
                case 'O' : return q0_15;
                default : return q0_0;
            }
            case q0_15 : switch (c)
            {
                case 'S' : return q15_15;
                case 'O' : return q0_30;
                default : return q0_0;
            }
            case q0_30 : switch (c)
            {
                case 'S' : return q15_30;
                case 'O' : return q0_40;
                default : return q0_0;
            }
            case q0_40 : switch (c)
            {
                case 'S' : return q15_40;
                case 'O' : return qOWin;
                default : return q0_0;
            }
            case q15_0 : switch (c)
            {
                case 'S' : return q30_0;
                case 'O' : return q15_15;
                default : return q0_0;
            }
            case q15_15 : switch (c)
            {
                case 'S' : return q30_15;
                case 'O' : return q15_30;
                default : return q0_0;
            }
            case q15_30 : switch (c)
            {
                case 'S' : return q30_30;
                case 'O' : return q15_40;
                default : return q0_0;
            }
            case q15_40 : switch (c)
            {
                case 'S' : return q30_40;
                case 'O' : return qOWin;
                default : return q0_0;
            }
            case q30_0 : switch (c)
            {
                case 'S' : return q40_0;
                case 'O' : return q30_15;
                default : return q0_0;
            }
            case q30_15 : switch (c)
            {
                case 'S' : return q40_15;
                case 'O' : return q30_30;
                default : return q0_0;
            }
            case q30_30 : switch (c)
            {
                case 'S' : return q40_30;
                case 'O' : return q30_40;
                default : return q0_0;
            }
            case q30_40 : switch (c)
            {
                case 'S' : return q40_40;
                case 'O' : return qOWin;
                default : return q0_0;
            }
            case q40_0 : switch (c)
            {
                case 'S' : return qSWin;
                case 'O' : return q40_15;
                default : return q0_0;
            }
            case q40_15 : switch (c)
            {
                case 'S' : return qSWin;
                case 'O' : return q40_30;
                default : return q0_0;
            }
            case q40_30 : switch (c)
            {
                case 'S' : return qSWin;
                case 'O' : return q40_40;
                default : return q0_0;
            }
            case q40_40 : switch (c)
            {
                case 'S' : return qAdvantageS;
                case 'O' : return qAdvantageO;
                default : return q0_0;
            }
            case qAdvantageS : switch (c)
            {
                case 'S' : return qSWin;
                case 'O' : return q40_40;
                default : return q0_0;
            }
            case qAdvantageO : switch (c)
            {
                case 'S' : return q40_40;
                case 'O' : return qOWin;
                default : return q0_0;
            }
            case qSWin : return qSWin;
            case qOWin : return qOWin;
            default : return q0_0;
        }
    }
    
    public static void process(String in)
    {
        for(int i = 0; i < in.length(); i++)
        {
            char c = in.charAt(i);
            state = delta(state, c);
        }
    }
    
    public boolean accepted()
    {
        return (state == qSWin || state == qOWin);
    }
    
    private String decideWinner()
    {
        if(state == qSWin)
            return "The server wins!";
        else if(state == qOWin)
            return "The opponet wins!";
        else return "No winner...";
    }
}
