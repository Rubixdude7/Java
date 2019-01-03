import java.io.File;
public class FileListFromFolder {
    public static void main(String str){
        File file = new File(str);
        String[] fileList = file.list();
        for(String name:fileList){
            System.out.println(name);
        }
    }
}