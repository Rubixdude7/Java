import java.io.FileInputStream;
import java.io.IOException;
import java.lang.IllegalArgumentException;
/**
 * An extension of the FileInputStream class that allows a file to read forwards AND backwards
 *
 * @author Nolan Aubuchon
 * @version (a version number or a date)
 */
public class BivalvicInputStream extends FileInputStream
{
    private int position = -1;
    
    public BivalvicInputStream(String filename) throws IOException
    {
        super(filename);
    }
    public int read() throws IOException
    {
        position++;
        return super.read();
    }
    public int readBack() throws IOException
    {
        try
        {
            getChannel().position(getChannel().position() - 2);
            position -= 2;
            return read();
        }
        catch(IllegalArgumentException e) //if you start to read backwards from the first byte of a file
        {
            return -1;
        }
    }
    public int getPosition()
    {
        return position;
    }
}
