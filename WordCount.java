import java.io.*;

public class WordCount {
    public static void main() throws IOException
    {
        FileInputStream in = null;
        int wordCount = 0;
        boolean whitespace = true;
        try {
            in = new FileInputStream("README.txt");
            wordCount = 0;
            int c;
            //Any non-whitespace followed by whitespace increases word count e.g. "lo a cv x "
            while ((c = in.read()) != -1) {
                if(!Character.isWhitespace((char) c)){ //if not whitespace
                    whitespace = false;
                    if(in.available() == 0) wordCount++;
                }
                else{ //if is whitespace
                    if(!whitespace){
                        wordCount++;
                        whitespace = true;
                    }
                }
            }
        }
        finally {
            if (in != null) {
                in.close();
                System.out.println(wordCount);
            }
        }
    }
}