import java.io.*;
/**
 * <p>Reads in a .txt file and outputs another with the bytes shifted by <i>shift</i> amount</p>
 * <p>can be used to encrypt secret messages</p>
 * <p>for example: a file containing <i>Hello, World!</i> with a shift of 1 yields <i>Ifmmp-!Xpsme"</i></p>
 * @author Nolan Aubuchon
 * @version 2.0
 * @since May 15, 2016
 */
public class ByteShifter {
    /**
     * @param : a String <i>fileName</i> and an int <i>shift</i>
     * @return techincally nothing, but outputs a .txt file based on the input in the same directory
     */
    public static void main(String fileName, int shift) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(fileName);
            out = new FileOutputStream(fileName.substring(0,fileName.length()-4)+"_"+shift+fileName.substring(fileName.length()-4));
            int c;
            while ((c = in.read()) != -1) { //c == -1 indicates end of file
                out.write(c+shift);
            }
        }finally {
            if (in != null) {
                in.close();
                out.close();
            }
        }
    }
}