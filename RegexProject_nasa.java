import java.util.TreeMap;
import java.lang.Integer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.*;
/**
 * <h1> Project 2: Regular Expressions </h1>
 * <p>Reads "access_log_Jul95.txt" and analyses it using regular expressions</p>
 * <p>Creates a report, titled "results_nasa.txt" to the same directory as this file</p>
 * @author Nolan Aubuchon 
 * @version October 20, 2016
 */
public class RegexProject_nasa
{
    public static void main(String[] args) throws IOException
    {
        TreeMap<String,Integer> visitors = new TreeMap<>(); //list of visitors and their number of requests
        TreeMap<String,Integer> pages = new TreeMap<>(); //list of pages and their number of views
        int[] days = {0,0,0,0,0,0,0}; //days and their number of visits, [0] == Sunday, [1] == Monday etc.
        /*
         * regular expression goes below, there are 4 captured groups in total, 5 if you count the entire string (group 0)
         * \\S catches any non-whitespace character, \\d catches any digit 
         */
        Pattern p = Pattern.compile("(\\S+) - - \\[(\\d\\d).*\\] \"[A-Z]* ?(.*) ?\" (\\d*) ");
        int errors = 0;
        Matcher m;
        BufferedReader reader;
        FileWriter writer;
        String line; //each line to be analysed.
        try
        {
            reader = new BufferedReader(new FileReader("access_log_Jul95.txt")); //BufferedReader can read a whole line at once, FileReader reads at one character at a time
            writer = new FileWriter("results_nasa.txt");
        }catch(FileNotFoundException e){
            System.out.println("File not found :(");
            return;
        }
        while((line = reader.readLine()) != null) //in a BufferedReader the end of file returns null
        {
            m = p.matcher(line);
            m.find();
            for(int i = 1; i <= m.groupCount(); i++)
            {
                try
                {
                    String group = m.group(i);
                    switch(i) //4 different groups in matched String
                    {
                        case 1:
                        if(visitors.containsKey(group))
                        {
                            Integer oldValue = visitors.get(group);
                            visitors.replace(group, oldValue+1); //increment visitor requests by 1
                        }
                        else
                        {
                            visitors.put(group,1);
                        }
                        break;
                        case 2:
                        int day = Integer.parseInt(group) % 7; //calls a private method defined below
                        days[day]++;
                        break;
                        case 3:
                        if(pages.containsKey(group))
                        {
                            Integer oldValue = pages.get(group);
                            pages.replace(group, oldValue+1); //increment page requests by 1
                        }
                        else
                        {
                            pages.put(group,1);
                        }
                        break;
                        case 4:
                        errors = Integer.parseInt(group) >= 400 ? errors+1 : errors; //calls a private method defined below
                        break;
                        default:
                        break;
                    }
                }catch(IllegalStateException e){
                    System.out.println(e);
                    System.out.println(line);
                }
            }
        }
        
        reader.close();
        
        //REPORT TIME!
        
        writer.write("Nolan Aubuchon\nProject 2 Report\nALGORITHM ANALYSIS\n");
        writer.write("TreeMaps were used to map strings of unique visitors or pages to interger values of each one's total requests.\n" + 
        "A BufferedReader was used to read the source file one line at a time.\n" + 
        "A while loop is used so that each line of text will continue to be read and parsed until there is no more text in the source file.\n" +
        "There are four captured groups in each line of text.\nA switch statement inside a for loop was used to deal with each group individually.");
        writer.write("\nRESULTS\n");
        writer.write(String.format("Total unique visitors: %d\n",visitors.size()));
        writer.write(String.format("Total requests made: %d\n",total(visitors))); //calls a private method defined below
        writer.write(String.format("Total errors: %d\n",errors));
        writer.write("TOP 20 VISITORS\n"); //top 20 pages and IPs calculated and printed in private static methods below
        top_20(visitors,writer);
        writer.write("TOP 20 PAGES\n");
        top_20(pages,writer);
        writer.write("BUISIEST DAYS OF THE WEEK\n");
        top_7(days,writer);
        
        //END REPORT!
        
        writer.close();
    }
    
    /**
     * <p>Writes the total amount of web requests by totaling the amount of requests for each web page</p>
     * @param a TreeMap of web pages and their amount of visits
     * @return void
     */
    private static int total(TreeMap<String,Integer> map)
    {
        int total = 0;
        for(String str : map.keySet())
        {
            total += map.get(str);
        }
        return total;
    }
    
    /**
     * <p>Writes the top 20 most visited pages OR top 20 most active visitors</p>
     */
    private static void top_20(TreeMap<String,Integer> map, FileWriter writer) throws IOException
    {
        for(int i=0; i < 20; i++)
        {
            if(map.size() == 0)
            {
                return;
            }
            String largest = map.firstKey();
            for(String str : map.keySet())
            {
                largest = map.get(str) > map.get(largest) ? str : largest; //conditional operator
            }
            writer.write(String.format("\t%d : %s - %d \n",i+1,largest,map.get(largest)));
            map.remove(largest);
        }
    }
    
    /**
     * <p>Writes the days of the week in decreasing order by amount of traffic</p>
     */
    private static void top_7(int[] days, FileWriter writer) throws IOException
    {
        for(int i=1; i<8; i++)
        {
            int indexOfLargest = 0;
            for(int j=0;j < 7; j++)
            {
                indexOfLargest = days[j] > days[indexOfLargest] ? j : indexOfLargest;
            }
            switch(indexOfLargest)
            {
                case 0:
                writer.write(String.format("\t%d : Sunday - %d views\n",i,days[indexOfLargest]));
                break;
                case 1:
                writer.write(String.format("\t%d : Monday - %d views\n",i,days[indexOfLargest]));
                break;
                case 2:
                writer.write(String.format("\t%d : Tuesday - %d views\n",i,days[indexOfLargest]));
                break;
                case 3:
                writer.write(String.format("\t%d : Wednesday - %d views\n",i,days[indexOfLargest]));
                break;
                case 4:
                writer.write(String.format("\t%d : Thursday - %d views\n",i,days[indexOfLargest]));
                break;
                case 5:
                writer.write(String.format("\t%d : Friday - %d views\n",i,days[indexOfLargest]));
                break;
                case 6:
                writer.write(String.format("\t%d : Saturday - %d views\n",i,days[indexOfLargest]));
                break;
                default:
                writer.write("ERROR");
                break;
            }
            days[indexOfLargest] = -1;
        }
    }
}
