import java.awt.Color;
public class ColorTester{
    public static void main(){
        Color red = Color.RED;
        Color green = Color.GREEN;
        Color blue = Color.BLUE;
        System.out.println(red.getRGB());
        System.out.println(red.toString() + green.toString() + blue.toString());
        System.out.println(new Color(255).toString());
    }
}
