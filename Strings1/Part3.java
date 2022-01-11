
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public static boolean twoOccurences(String stringa, String stringb){
        int index1 = stringb.indexOf(stringa);
        if (index1 == -1){
            return false;
        }
        
        int index2 = stringb.indexOf(stringa,index1+1);
        if (index2 == -1){
            return false;
        }
        
        return true;
        
    } 
    
    public static void testing(){
        boolean ans1 = twoOccurences("a","a yorkie");
        System.out.println("Ans1 is:" + ans1);
        
        boolean ans2 = twoOccurences("an","I bought an ananas at the shop");
        System.out.println("Ans2 is:" + ans2);
        
        boolean ans3 = twoOccurences("cl","The controller wasw off");
        System.out.println("Ans3 is:" + ans3);
        
        
        
    }
}
