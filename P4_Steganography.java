import java.lang.Boolean;
import java.util.Stack;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
/**
 * Uses the ascii control characters '\u001E' and '\u001F' to encode a secret message within a .txt file
 * embedded message can be any format
 * '\u001E' is TRUE
 * '\u001F' is FALSE
 * 8 Control Characters equals 1 byte of data.
 * Those characters should appear as zero-width whitespace on windows notepad.
 * 
 * @author Nolan Aubuchon 
 * @version April 5, 2017
 */
public class P4_Steganography
{
    public static void main(String[] args) throws IOException
    {
        File inFile;
        File inMessage;
        File outFile;
        Stack<Boolean> stack = new Stack<>();
        Scanner scan = new Scanner(System.in);
        int c, d;
        int choice = 0;
        while(choice != 3)
        {
            System.out.println("\n1.\tEmbed File\n2.\tExtract File\n3.\tQuit");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.print("Name of file to embed with:\t");
                    inFile = new File(scan.next());
                    System.out.print("Name of file(message) to embed:\t");
                    inMessage = new File(scan.next());
                    System.out.print("Name of output file:\t");
                    outFile = new File(scan.next());
                    if(inMessage.length() * 8 > inFile.length())
                    {
                        System.out.println("Insufficient space to embed file!");
                        return;
                    }
                    FileInputStream fis = new FileInputStream(inFile);
                    FileInputStream mis = new FileInputStream(inMessage);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    while((c = mis.read()) != -1)
                    {
                        stack = getStack(c);
                        while(!stack.empty())
                        {
                            d = fis.read();
                            fos.write(d);
                            /*
                             * Making sure carriage return is still always followed by line feed
                             * may mess up the appearance of plaintext if done otherwise
                             */
                            if(d == 13) //carriage return
                            {
                                d = fis.read();
                                assert d == 10; //line feed
                                fos.write(d);
                            }
                            d = getCharacter(stack.pop());
                            fos.write(d);
                        }
                    }
                    while((d = fis.read()) != -1)
                    {
                        fos.write(d);
                    }
                    fis.close();
                    mis.close();
                    fos.close();
                }
                break;
                case 2:
                {
                    System.out.print("Name of embedded file:\t");
                    inFile = new File(scan.next());
                    System.out.print("Name of output file:\t");
                    outFile = new File(scan.next());
                    FileInputStream fis = new FileInputStream(inFile);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    while((c = fis.read()) != -1)
                    {
                        if(c == '\u001E') stack.push(true);
                        else if(c == '\u001F') stack.push(false);
                        if(stack.size() == 8)
                        {
                            stack = reverse(stack);
                            fos.write(getCharacter(stack));
                            stack.clear();
                        }
                    }
                    fis.close();
                    fos.close();
                }
                break;
                case 3: {System.out.println("Program Terminated");} break;
                default: {System.out.println("Invalid Choice!");} break;
            }
        }
    }
    
    /**
     * @param a stack of 8 bits
     * @return its corresponding integer value
     */
    public static int getCharacter(Stack<Boolean> stack)
    {
        int result = 0;
        result += stack.get(0) ? 1 : 0;
        result += stack.get(1) ? 2 : 0;
        result += stack.get(2) ? 4 : 0;
        result += stack.get(3) ? 8 : 0;
        result += stack.get(4) ? 16 : 0;
        result += stack.get(5) ? 32 : 0;
        result += stack.get(6) ? 64 : 0;
        result += stack.get(7) ? 128 : 0;
        return (char) result;
    }
    
    /**
     * @param a boolean value
     * @return its corresponding integer value
     * TRUE = '\u001E'
     * FALSE = '\u001F'
     */
    public static int getCharacter(boolean bool)
    {
        return bool ? '\u001E' : '\u001F';
    }
    
    /**
     * @param an integer value
     * @return its corresponding stack of 8 bits
     */
    public static Stack<Boolean> getStack(int c)
    {
        Stack<Boolean> stack = new Stack<>();
        if(c % 2 != 0)
            {
                stack.push(true);
                c -= c % 2;
            }else{stack.push(false);}
        if(c % 4 != 0)
            {
                stack.push(true);
                c -= c % 4;
            }else{stack.push(false);}
        if(c % 8 != 0)
            {
                stack.push(true);
                c -= c % 8;
            }else{stack.push(false);}
        if(c % 16 != 0)
            {
                stack.push(true);
                c -= c % 16;
            }else{stack.push(false);}
        if(c % 32 != 0)
            {
                stack.push(true);
                c -= c % 32;
            }else{stack.push(false);}
        if(c % 64 != 0)
            {
                stack.push(true);
                c -= c % 64;
            }else{stack.push(false);}
        if(c % 128 != 0)
            {
                stack.push(true);
                c -= c % 128;
            }else{stack.push(false);}
        if(c % 256 != 0)
            {
                stack.push(true);
                c -= c % 256;
            }else{stack.push(false);}
        return stack;
    }
    
    /**
     * method used to debug, please ignore
     */
    public static Stack<Boolean> tester(int c) //works
    {
        return reverse(getStack('D'));
    }
    
    /**
     * Reverses the order of a stack
     * 
     * @param a stack of 8 bits
     * @return the stack with reversed order
     */
    public static Stack<Boolean> reverse(Stack<Boolean> instack)
    {
        Stack<Boolean> outstack = new Stack<>();
        while(!instack.empty())
        {
            outstack.push(instack.pop());
        }
        return outstack;
    }
}
