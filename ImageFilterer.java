import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.Color;
/**
 * <p>applies two different image filters to an image and saves them as seperate files</p>
 * <p>the first is suffixed "grey" and is in grayscale. the second is suffixed mixed, it returns only one of Red, Blue or Green Values, whichever is the largest</p>
 * <p><img src="C:\Users\Nolan\Desktop\Java Projects\JavaIO\exampleImage.png" alt="example image"/></p>
 * <p><img src="C:\Users\Nolan\Desktop\Java Projects\JavaIO\exampleImageGrey.png" alt="example image(grayscale)"/></p>
 * <p><img src="C:\Users\Nolan\Desktop\Java Projects\JavaIO\exampleImageMixed.png" alt="example image(mixed)"/></p>
 * @author Nolan Aubuchon
 * @version May 12, 2016
 */
public class ImageFilterer {
    /**
     * applies two different image filters to an image and saves them as seperate files
     * @param filename (with extention) of an image
     * @return void
     */
    public static void main(String str) throws IOException {
        ImageIO io = null;
        String pathname = str;
        str = str.substring(0,str.length()-4);
        BufferedImage buff = io.read(new File(pathname));
        System.out.println(Color.RED.getRGB());
        for(int i=0; i<buff.getWidth(); i++){
            for(int j=0; j<buff.getHeight(); j++){
                int red = new Color(buff.getRGB(i,j)).getRed();
                int blue = new Color(buff.getRGB(i,j)).getBlue();
                int green = new Color(buff.getRGB(i,j)).getGreen();
                int gray = (blue + red + green)/3;
                buff.setRGB(i,j,new Color(gray,gray,gray).getRGB());
            }
        }
        io.write(buff,"png",new File(str + "Grey.png"));
        buff = io.read(new File(pathname));
        for(int i=0; i<buff.getWidth(); i++){
            for(int j=0; j<buff.getHeight(); j++){
                int red = new Color(buff.getRGB(i,j)).getRed();
                int blue = new Color(buff.getRGB(i,j)).getBlue();
                int green = new Color(buff.getRGB(i,j)).getGreen();
                if(red > blue && red > green){
                    buff.setRGB(i,j,redOnly(buff.getRGB(i,j)));
                    System.out.println(Integer.toHexString(buff.getRGB(i,j)));
                }
                else if(green > red && green > blue){
                    buff.setRGB(i,j,greenOnly(buff.getRGB(i,j)));
                    System.out.println(Integer.toHexString(buff.getRGB(i,j)));
                }
                else{
                    buff.setRGB(i,j,blueOnly(buff.getRGB(i,j)));
                    System.out.println(Integer.toHexString(buff.getRGB(i,j)));
                }
            }
        }
        io.write(buff,"png",new File(str + "Mixed.png"));
    }
    /**
     * @param an integer color hex code
     * @return only RED integer value of a color hex code
     */
    private static int redOnly(int rgb){
         return new Color(
                         new Color(rgb).getRed(),
                         0,
                         0
                         ).getRGB();
    }
    /**
     * @param an integer color hex code
     * @return only GREEN integer value of a color hex code
     */
    private static int greenOnly(int rgb){
        return new Color(
                         0,
                         new Color(rgb).getGreen(),
                         0
                         ).getRGB();
    }
    /**
     * @param an integer color hex code
     * @return only Blue integer value of a color hex code
     */
    private static int blueOnly(int rgb){
        return new Color(
                         0,
                         0,
                         new Color(rgb).getBlue()
                         ).getRGB();
    }
}