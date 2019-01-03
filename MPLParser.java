/**
 * Compiles a MPL (Marshall Programming Language) into a Java Program using Java
 * the String.trim() method was used to remove excess whitespace
 * test2.mpl is the same as test.mpl except test2.mpl has proper spacing and indentation
 * 
 * Complete BNF:
 * <MPL> :: BEGIN\n<stmts>\nEND
 * <stmts> :: <stmt> | <stmt>\n<stmts>
 * <stmt> :: <math> | <if_stmt> | <while_stmt> | <do_stmt> | <var_decl> | <print> | <read>
 * <math> :: <var_name> = <math_expr> | <var_name> = <term>
 * <math_expr> :: <term> <math_op> <term>
 * <math_op> :: + | - |  * | /
 * <term> :: <var_name> | <const>
 * <const> :: <digit><const> | <digit>
 * <digit> :: 0 | 1 | 2 | ... | 8 | 9
 * <if_stmt> :: if(<logical_expr>)<stmts>endif | if(<logical_expr>)<stmts>else<stmts>endif
 * <logical_expr> :: <term> <logical_op> <term>
 * <logical_op> :: <> | = | > | >= | <= | <
 * <print> :: print <print_stmt>
 * <print_stmt> :: <var_name> | "<string>" | <var_name> & <print_stmt> | "<string>" & <print_stmt>
 * <string> ::
 * <var_decl> :: var <var_list>
 * <var_list> :: <var_name> | <var_name> , <var_list>
 * <var_name> :: a | b | c | ... | z | A | B | C | ... | Z
 * <read> :: read <var_name>
 * <while> :: while(<logical_expr>)\n<stmts>\nendwhile
 * <do_whilst> :: do\n<stmts>\nwhilst(<logical_expr>)
 * 
 * @author Nolan Aubuchon 
 * @version December 4, 2016
 */
import java.lang.Character;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
public class MPLParser
{
    private static final String prefix = "import java.util.Scanner;public class Output{public static void main(String[] args){";
    private static final String suffix = "}}";
    static int line;
    private enum Statement
    {
        MPL,
        STMTS,
        STMT,
        MATH,
        MATH_EXPR,
        MATH_OP,
        TERM,
        CONST,
        DIGIT,
        IF_STMT,
        LOGICAL_EXPR,
        LOGICAL_OP,
        PRINT,
        PRINT_STMT,
        STRING,
        VAR_DECL,
        VAR_LIST,
        VAR_NAME,
        READ,
        WHILE,
        DO_WHILST;
    }
    private static boolean Parse(String text)
    {
        line = 0;
        return Parse(text, Statement.MPL);
    }
    private static boolean Parse(String text, Statement stmt)
    {
        switch (stmt)
        {
            case MPL:
            {
                if(text.startsWith("BEGIN") && text.endsWith("END"))
                {
                    line++;
                    boolean program = Parse(text.substring(5,text.length()-3).trim(), Statement.STMTS);
                    return program;
                }
                else 
                {
                    System.err.println("MPL Error: line " + line);
                    return false;
                }
            }
            case STMTS:
            {
                int i = text.indexOf('\n');
                if(text.startsWith("while")) 
                {
                    int j = text.indexOf("endwhile");
                    return Parse(text.substring(0,j+8), Statement.WHILE);
                }
                if(text.startsWith("if")) 
                {
                    int j = text.indexOf("endif");
                    return Parse(text.substring(0,j+5), Statement.IF_STMT);
                }
                if(text.startsWith("do")) 
                {
                } 
                if(i != -1)
                {
                    boolean out_a = Parse(text.substring(0,i).trim(), Statement.STMT);
                    boolean out_b = Parse(text.substring(i+1).trim(), Statement.STMTS);
                    return out_a && out_b;
                }
                else {return Parse(text.trim(), Statement.STMT);}
            }
            case STMT:
            {
                line++;
                if(text.startsWith("if")) {return Parse(text, Statement.IF_STMT);}
                if(text.startsWith("while")) {return Parse(text, Statement.WHILE);}
                if(text.startsWith("do")) {return Parse(text, Statement.DO_WHILST);}
                if(text.startsWith("var")) {return Parse(text, Statement.VAR_DECL);}
                if(text.startsWith("print")) {return Parse(text, Statement.PRINT);}
                if(text.startsWith("read")) {return Parse(text, Statement.READ);}
                else {return Parse(text, Statement.MATH);}
            }
            case MATH:
            {
                int e = text.indexOf('=');
                if(e == -1) {return false;}
                else
                {
                    boolean a = Parse(text.substring(0,e).trim(), Statement.VAR_NAME);
                    int w = text.indexOf('+'), x = text.indexOf('-'), y = text.indexOf('*'), z = text.indexOf('/');
                    w = w > x ? w : x;
                    w = w > y ? w : y;
                    w = w > z ? w : z;
                    boolean b = w == -1 ? Parse(text.substring(e+1).trim(), Statement.TERM) : Parse(text.substring(e+1).trim(), Statement.MATH_EXPR);
                    return a && b;
                }
            }
            case MATH_EXPR:
            {
                int a = text.indexOf('+'), b = text.indexOf('-'), c = text.indexOf('*'), d = text.indexOf('/');
                a = a > b ? a : b;
                a = a > c ? a : c;
                a = a > d ? a : d;
                if(a != -1) 
                {
                    return Parse(text.substring(0,a).trim(), Statement.TERM) && 
                    Parse(text.substring(a,a+1), Statement.MATH_OP) && 
                    Parse(text.substring(a+1).trim(), Statement.TERM);
                }
                else 
                {
                    System.err.println("MATH_EXPR Error: line " + line);
                    return false;
                }
            }
            case MATH_OP:
            {
                if( text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/") ) {return true;}
                else 
                {
                    System.err.println("MATH_OP Error: line " + line);
                    return false;
                }
            }
            case TERM:
            {
                char c = text.charAt(0);
                if(Character.isDigit(c)) {return Parse(text, Statement.CONST);}
                else if(Character.isLetter(c)) {return Parse(text, Statement.VAR_NAME);}
                else
                {
                    System.err.println("TERM Error: line " + line);
                    return false;
                }
            }
            case CONST:
            {
                if(text.length() > 1) {return Parse(text.substring(0,1), Statement.DIGIT) && Parse(text.substring(1), Statement.CONST);}
                else {return Parse(text, Statement.DIGIT);}
            }
            case DIGIT:
            {
                if(text.length() == 1)
                {
                    char c = text.charAt(0);
                    if(Character.isDigit(c)) {return true;}
                    else
                    {
                        System.err.println("DIGIT Error: line " + line); 
                        return false;
                    }
                }
                else
                {
                    System.err.println("DIGIT Error : line " + line);
                    return false;
                }
            }
            case IF_STMT:
            {
                int i = text.indexOf(')');
                if(text.startsWith("if(") && text.endsWith("endif") && (i != -1))
                {
                    boolean a = Parse(text.substring(3,i).trim(), Statement.LOGICAL_EXPR);
                    boolean b = Parse(text.substring(i+1,text.length()-5).trim(), Statement.STMTS);
                    return a && b;
                }
                else
                {
                    System.err.println("IF Error: line " + line);
                    return false;
                }
            }
            case LOGICAL_EXPR:
            {
                int a = text.indexOf("<>"), b = text.indexOf(">="), c = text.indexOf("<=");
                int x = text.indexOf('='), y = text.indexOf('<'), z = text.indexOf('>');
                a = a > b ? a : b; a = a > c ? a : c;
                x = x > y ? x : y; x = x > z ? x : z;
                if(a != -1) {return Parse(text.substring(0,a).trim(), Statement.TERM) && Parse(text.substring(a+2).trim(), Statement.TERM);}
                if(x != -1) {return Parse(text.substring(0,x).trim(), Statement.TERM) && Parse(text.substring(x+1).trim(), Statement.TERM);}
                else 
                {
                    System.err.println("LOGICAL_EXPR Error: line " + line);
                    return false;
                }
            }
            case LOGICAL_OP:
            {
                if(text.equals("<>")) {return true;}
                else if(text.equals("=")) {return true;}
                else if(text.equals(">")) {return true;}
                else if(text.equals(">=")) {return true;}
                else if(text.equals("<=")) {return true;}
                else if(text.equals("<")) {return true;}
                else
                {
                    System.err.println("LOGICAL_OP Error: line " + line); 
                    return false;
                }
            }
            case PRINT:
            {
                if(text.startsWith("print ")) {return Parse(text.substring(6).trim(),Statement.PRINT_STMT);}
                else
                {
                    System.err.println("PRINT Error: line " + line);
                    return false;
                }
            }
            case PRINT_STMT:
            {
                int i = text.indexOf('\"'), j = text.lastIndexOf('\"'), k = text.indexOf('&');
                boolean a, b;
                if(k != -1)
                {
                    if(i != -1 && j != i)
                    {
                        a = Parse(text.substring(i+1,j), Statement.STRING); //no trim() function required here because its a string
                        b = Parse(text.substring(k+1).trim(), Statement.PRINT_STMT);
                        return a && b;
                    }
                    else {return Parse(text.substring(0,k).trim(), Statement.VAR_NAME);}
                }
                else
                {
                    if(i != -1 && j != i) {return Parse(text.substring(i+1,j), Statement.STRING);}
                    else {return Parse(text.trim(), Statement.VAR_NAME);}
                }
            }
            case STRING:
            {
                return true;
            }
            case VAR_DECL:
            {
                if(text.startsWith("var ")) {return Parse(text.substring(4),Statement.VAR_LIST);}
                else
                {
                    System.err.println("VAR_DECL Error: line " + line);
                    return false;
                }
            }
            case VAR_LIST:
            {
                int i = text.indexOf(',');
                if(i != -1)
                {
                    boolean out_a = Parse(text.substring(0,i).trim(), Statement.VAR_NAME);
                    boolean out_b = Parse(text.substring(i+1).trim(), Statement.VAR_LIST);
                    return out_a && out_b;
                }
                else {return Parse(text, Statement.VAR_NAME);}
            }
            case VAR_NAME:
            {
                if(text.length() == 1)
                {
                    char c = text.charAt(0);
                    if(Character.isLetter(c)) {return true;}
                    else 
                    {
                        System.err.println("VAR_NAME Error: line " + line);
                        return false;
                    }
                }
                else 
                {
                    System.err.println("VAR_NAME Error: line " + line); 
                    return false;
                }
            }
            case READ:
            {
                if(text.startsWith("read ")) {return Parse(text.substring(5).trim(),Statement.VAR_NAME);}
                else
                {
                    System.err.println("READ Error: line " + line);
                    return false;
                }
            }
            case WHILE:
            {
                int i = text.indexOf(')');
                if(text.startsWith("while(") && text.endsWith("endwhile") && (i != -1))
                {
                    boolean a = Parse(text.substring(6,i).trim(), Statement.LOGICAL_EXPR);
                    boolean b = Parse(text.substring(i+1,text.length()-8).trim(), Statement.STMTS);
                    return a && b;
                }
                else
                {
                    System.err.println("WHILE Error: line " + line);
                    return false;
                }
            }
            case DO_WHILST:
            {
                assert text.startsWith("do");
                int i = text.lastIndexOf("whilst("), j = text.lastIndexOf(')');
                if(i != -1 && j != 1) 
                {
                    boolean a = Parse(text.substring(2,i).trim(), Statement.STMTS);
                    boolean b = Parse(text.substring(i+7,j).trim(), Statement.LOGICAL_EXPR);
                    return a && b;
                }
                else
                {
                    System.err.println("DO_WHILST Error: line " + line);
                    return false;
                }
            }
            default:
            {
                System.out.println("DEFAULT Error: line " + line);
                return false;
            }
        }
    }
    public static void main(String[] args) 
    {
        String text = "";
        String fileName;
        int choice = -1;
        Scanner scan = new Scanner(System.in);
        while(choice != 2)
        {
            System.out.println("1. Input MPL file\n2. Exit");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.print("Enter name of file:\t");
                    fileName = scan.next();
                    try
                    {
                        int c;
                        FileInputStream in = new FileInputStream(fileName);
                        while((c = in.read()) != -1)
                        {
                            text += (char)c;
                        }
                        System.out.println(text);
                        String message = Parse(text) ? "Succesfully parsed" : "Could not parse!";
                        System.out.println(message);
                        in.close();  
                    }
                    catch(IOException e)
                    {
                        System.out.println("IOException caught");
                        return;
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Exiting program...");
                    return;
                }
                default:
                {
                    System.out.println("Invalid choice");
                }
            }
        }
    }
}