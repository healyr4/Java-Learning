
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public int howMany(String stringa, String stringb){
        int numOccurences = 0;
        int startIndex = 0;
        int lengthA= stringa.length();
        while(startIndex < stringb.length()){
            int currIndex = stringb.indexOf(stringa,startIndex);
            if (currIndex == -1){ break;}
            startIndex = currIndex + lengthA;
            numOccurences += 1;
        }
        
        return numOccurences;
    }

    public void testHowMany(){
        int test1 = howMany("a", "ananas");
        System.out.println("Occurences :" + test1);
        int test2 = howMany("bb", "bbbbb");
        System.out.println("Occurences :" + test2);
    }
}
