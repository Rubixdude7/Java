
/**
 * <p>This program creates a vending machine DFA</p>
 * 
 * @author Nolan Aubchon
 * @version September 24, 2016
 */
import java.util.Scanner;

public class Vending
{
    //these are the possible selections
    private enum Sigma
    {
        quarter,
        dollar,
        select,
        refund
    }
    //accepting states where user has enough money for a soda
    private static final int[] F_select = {5, 6, 7, 8};
    //accepting states where money is refundable
    private static final int[] F_refund = {1, 2, 3, 4, 5, 6, 7, 8};
    
    private static final int q0_00 = 0;
    private static final int q0_25 = 1;
    private static final int q0_50 = 2;
    private static final int q0_75 = 3;
    private static final int q1_00 = 4;
    private static final int q1_25 = 5;
    private static final int q1_50 = 6;
    private static final int q1_75 = 7;
    private static final int q2_00 = 8;
    
    private static int state;
    
    public static void main()
    {
        Scanner scan = new Scanner(System.in);
        int menu = -1;
        state = q0_00; //initial state 
        while(true)
        {
            System.out.println("total cash in machine:\t" + totalCash() + "\n1.\tAdd $0.25\n2.\tAdd $1.00\n3.\tSelect Soda\n4.\tRefund");
            menu = scan.nextInt();
            switch(menu)
            {
                case 1: if(state != delta(state, Sigma.quarter)) //if not a self-loop
                        {
                            System.out.println("$0.25 added to the machine");
                        }
                        else
                        {
                            System.out.println("Sufficient cash already in machine");
                        }
                        state = delta(state, Sigma.quarter);
                        break;
                case 2: if(state != delta(state, Sigma.dollar))
                        {
                            System.out.println("$1.00 added to the machine");
                        }
                        else
                        {
                            System.out.println("Sufficient cash already in machine");
                        }
                        state = delta(state, Sigma.dollar);
                        break;
                case 3: if(contains(state,F_select))
                        {
                            System.out.println("Soda selected");
                            System.exit(0);
                        }
                        else
                        {
                            System.out.println("Insufficient funds!");
                        }
                        break;
                case 4: if(contains(state,F_refund))
                        {
                            System.out.println("Cash refunded.");
                            System.exit(0);
                        }
                        else
                        {
                            System.out.println("No money to refund!");
                        }
                        break;
                default: System.out.println("Not a valid choice");
            }
        }
    }
    
    static private int delta(int i, Sigma s)
    {
        switch (i)
        {
            case q0_00 : switch (s)
            {
                case quarter : return q0_25;
                case dollar  : return q1_00;
                case select  : return i; //not enough money
                case refund  : return i; //no money to refund
            }
            case q0_25 : switch (s)
            {
                case quarter : return q0_50;
                case dollar  : return q1_25;
                case select  : return i; //not enough money
                case refund  : return q0_00;
            }
            case q0_50 : switch (s)
            {
                case quarter : return q0_75;
                case dollar  : return q1_50;
                case select  : return i; //not enough money
                case refund  : return q0_00;
            }
            case q0_75 : switch (s)
            {
                case quarter : return q1_00;
                case dollar  : return q1_75;
                case select  : return i; //not enough money
                case refund  : return q0_00;
            }
            case q1_00 : switch (s)
            {
                case quarter : return q1_25;
                case dollar  : return q2_00;
                case select  : return i; //not enough money
                case refund  : return q0_00; //no money to refund
            }
            case q1_25 : switch (s)
            {
                case quarter : return i; //enough money in machine
                case dollar  : return i; //enough money in machine
                case select  : return i;
                case refund  : return q0_00;
            }
            case q1_50 : switch (s)
            {
                case quarter : return i; //enough money in machine
                case dollar  : return i; //enough money in machine
                case select  : return i;
                case refund  : return q0_00;
            }
            case q1_75 : switch (s)
            {
                case quarter : return i; //enough money in machine
                case dollar  : return i; //enough money in machine
                case select  : return i;
                case refund  : return q0_00;
            }
            case q2_00 : switch (s)
            {
                case quarter : return i; //enough money in machine
                case dollar  : return i; //enough money in machine
                case select  : return i;
                case refund  : return q0_00;
            }
            default: return q0_00;
        }
    }
    
    static private boolean contains(int state, int[] states)
    {
        for(int i : states)
        {
            if(state == i)
            {
                return true;
            }
        }
        return false;
    }
    
    static private String totalCash()
    {
        switch(state)
        {
            case 0: return "$0.00";
            case 1: return "$0.25";
            case 2: return "$0.50";
            case 3: return "$0.75";
            case 4: return "$1.00";
            case 5: return "$1.25";
            case 6: return "$1.50";
            case 7: return "$1.75";
            case 8: return "$2.00";
            default: return "$0.00";
        }
    }
}