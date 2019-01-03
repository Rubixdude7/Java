
/**
 * <p>Tests Java enums. For example, I have used the word for "is" is Indo-European languages</p>
 * 
 * @author Nolan Aubuchon
 * @version 1.1
 */
public class EnumTester
{
    public static void main()
    {
        for(Is word : Is.values())
        {
            System.out.printf("the %8s word for \"is\" is:\t%4s\n",word,word.get());
        }
        System.out.printf("%s %s a terrible language",Is.ENGLISH,Is.ENGLISH.get());
    }
    
    private static enum Is
    {
        ENGLISH("is"),GOTHIC("ist"),LATIN("est"),GREEK("esti"),SANSKRIT("asti");
        private String word;
        Is(String word)
        {
            this.word = word;
        }
        private String get()
        {
            return word;
        }
    }
}
