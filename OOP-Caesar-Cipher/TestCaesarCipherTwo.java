
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File; 
import edu.duke.*;
public class TestCaesarCipherTwo {
    
    // return a new String that is every other character from message
    // starting with the start position
    public String halfOfString(String message, int start){
            String halfString ="";
            char currChar;
            for(int i = start; i<message.length(); i+=2){
                currChar = message.charAt(i);
                halfString+=currChar;
            }

        return halfString;
    }
    
    private int [] countLetters(String text){
        int [] freq = new int[26];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        text = text.toLowerCase();
        char currChar;
        int index = 0;
        for(int i=0; i<text.length();i++){
            currChar = text.charAt(i);
            index = alpha.indexOf(currChar);
            //System.out.println(index);
            if (index != -1){
                freq[index]+=1;
            }
        }
        return freq;     
    }

    public int indexOfMax( int[] values){
        int maxElement = 0;
        int maxIndex = 0;
        //maxElement = maxIndex(values);
        for(int i=0; i<values.length; i++){
            
            if (values[i] > maxElement){
                //System.out.println(values[i]);
                maxIndex = i;
                maxElement = values[i];
            }
        }
        return maxIndex;
    }
    public int getKey(String s){
        int key = 0;
        int[] freqs = countLetters(s);
        int maxIndex = indexOfMax(freqs);
        
        key = maxIndex - 4;
        System.out.println("Max index is: " + maxIndex);
        if(maxIndex <4){
             key = 26 -(4 - maxIndex);
         }

        return key;
    }    
    
    public String breakCaesarCipher(String encrypted){
        
        int k1,k2;
        String firstString = halfOfString(encrypted,0);
        String secondString = halfOfString(encrypted,1);
        
        k1 = getKey(firstString);
        k2 = getKey(secondString);
        // Keys are in opposite order
        System.out.println("Key1: " +k1 +" Key2 : " +k2);
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26-k1,26-k2);
        return cc2.encryptTwoKeys(encrypted);
    }    
    
    
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        //String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherTwo cc2 = new CaesarCipherTwo(4,17);
        
        //Encrypted String
        String encrypted = cc2.encryptTwoKeys(input);
        System.out.println("Encrypted is: " + encrypted);
        
        //Decrypted String
        String decrypted = cc2.decrypt(encrypted);
        System.out.println("Decrypted is: " + decrypted);
        
        //Broken CC
        String brokenCipher = breakCaesarCipher(input);
        System.out.println("Decrypted with breaker is: " + brokenCipher);
        
    }
    
}
