import java.util.Scanner;
public class Collatz
{
    public static void main(String[] args) throws InterruptedException
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Type a number: ");
        int num = scan.nextInt();
        while(num != 1)
        {
            if(num % 2 == 0)
              num = num / 2;
            else
              num = (3 * num) + 1;
            System.out.println(num);
            Thread.sleep(100);
        }
        System.out.println("done");
    }
}