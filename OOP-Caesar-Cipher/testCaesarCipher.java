
/**
 * Write a description of testCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File; 
import edu.duke.*;
public class testCaesarCipher {
    
    public int [] countLetters(String text){
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
    
    public String breakCaesarCipher(String encrypted){
         int [] freqs = countLetters(encrypted);
         int maxIndex = indexOfMax(freqs);
         //System.out.println("Max index is: " + maxIndex);
         int decryptKey = maxIndex - 4;
         //System.out.println("Max index is: " + maxIndex);
         if(maxIndex <4){
             decryptKey = 26 -(4 - maxIndex);
         }
         
         System.out.println("Decrypt key is: " + decryptKey);
         CaesarCipher ccObj = new CaesarCipher(decryptKey);
         return ccObj.decrypt(encrypted);
    }
    
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.asString();  
        CaesarCipher obj = new CaesarCipher(15);
        
        //Encrypted String
        String encrypted = obj.encrypt(input);
        System.out.println("Encrypted is: " + encrypted);
        
        //Decrypted String
        String decrypted = obj.decrypt(encrypted);
        System.out.println("Decrypted is: " + decrypted);
        
        //Break Caesar Cipher
        String broken = breakCaesarCipher(encrypted);
        System.out.println("Broken message: " + broken);
    }
    
}
