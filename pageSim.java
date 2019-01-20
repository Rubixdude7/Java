
/**
 * Virtual Memory Simulator; decides what pages to add to the virtual memory; 3 different algorithms: Least Recently Used, First-in First-out, and Random
 * 
 * @author Nolan Aubuchon and 2 other team members, made for a college project
 * @version October 28, 2015
 */
import java.io.*;
import java.util.*;
public class pageSim
{
    public static class Frame
    {
        private String frame;
        private String action;

        public Frame(String frame, String action)
        {
            this.frame = frame;
            this.action = action;
        }

        public String getFrame()
        {
            return frame;
        }

        public String getAction()
        {
            return action;
        }
    }

    // args == {int numerOfFrames, String methodType, String fileURL
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<Frame> pagesIn = new ArrayList<Frame>();
        FileReader myReader;
        int nframes = Integer.parseInt(args[0]); //Number of Frames, int
        String method = args[1];                 //Method Type, String
        File myFile = new File(args[2]);         //File URL, String

        myReader = new FileReader(myFile);
        Scanner scan = new Scanner(myReader);

        int pageFault = 0;
        int write = 0;

        while(scan.hasNextLine())
        {
            String str = scan.next();
            System.out.println(str);
            pagesIn.add(new Frame(str.substring(0,8), str.substring(9)));
        }

        myReader.close();
        for (int i=0; i < pagesIn.size(); i++)
        {
            System.out.println("frame: " + pagesIn.get(i).getFrame());
            System.out.println("action: " + pagesIn.get(i).getAction());
        }

        ArrayList<String> pagesOut = new ArrayList<String>();

        if(method.equalsIgnoreCase("lru"))
        {
            for(int i = 0; i < pagesIn.size(); i++)
            {
                if(pagesOut.size() < nframes && !pagesOut.contains(pagesIn.get(i))) //add first pageSize frames if possible
                {
                    System.out.println("adding " + pagesIn.get(i).getFrame());
                    pagesOut.add(pagesIn.get(i).getFrame());
                }
                if(pagesOut.size() == nframes && !pagesOut.contains(pagesIn.get(i))) //then, do the algorithm
                {
                    System.out.println("removing " + pagesIn.get(0));
                    pagesOut.remove(pagesIn.get(i).getFrame());
                    System.out.println("adding " + pagesIn.get(i).getFrame());
                    pagesOut.add(pagesIn.get(i).getFrame());
                    pageFault++;
                }
                if (pagesOut.size() <= nframes && pagesIn.get(i).getAction().equalsIgnoreCase("w"))
                {
                    write++;
                }
                else
                {

                }
            }
        }

        if(method.equalsIgnoreCase("fifo"))
        {
            for(int i = 0; i < pagesIn.size(); i++)
            {
                if(pagesOut.size() < nframes && !pagesOut.contains(pagesIn.get(i))) //add first pageSize frames if possible
                {
                    System.out.println("adding " + pagesIn.get(i).getFrame());
                    pagesOut.add(pagesIn.get(i).getFrame());
                }
                if(pagesOut.size() == nframes && !pagesOut.contains(pagesIn.get(i))) //then, do the algorithm
                {
                    System.out.println("removing " + pagesIn.get(0));
                    pagesOut.remove(0);
                    System.out.println("adding " + pagesIn.get(i).getFrame());
                    pagesOut.add(pagesIn.get(i).getFrame());
                    pageFault++;
                }
                if (pagesOut.size() <= nframes && pagesIn.get(i).getAction().equalsIgnoreCase("w"))
                {
                    write++;
                }
                else
                {

                }
            }
        }

        if(method.equalsIgnoreCase("random"))
        {
            for(int i = 0; i < pagesIn.size(); i++)
            {
                if(pagesOut.size() < nframes && !pagesOut.contains(pagesIn.get(i))) //add first pageSize frames if possible
                {
                    //System.out.println("adding " + pagesIn.get(i).getFrame());
                    pagesOut.add(pagesIn.get(i).getFrame());
                }
                if(pagesOut.size() == nframes && !pagesOut.contains(pagesIn.get(i))) //then, do the algorithm
                {
                    int random = (int)(Math.random() * (nframes));
                    //System.out.println("removing " + pagesIn.get(random));
                    pagesOut.remove(random);
                    //System.out.println("adding " + pagesIn.get(i).getFrame());
                    pagesOut.add(random, pagesIn.get(i).getFrame());
                    pageFault++;
                }
                if (pagesOut.size() <= nframes && pagesIn.get(i).getAction().equalsIgnoreCase("w"))
                {
                    write++;
                }
                else
                {

                }
            }
        }

        System.out.println("# Page Faults: " + pageFault);
        System.out.println("# Writes: " + write);

    }
}
