
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File; 
import edu.duke.*;
public class CaesarBreaker {
    // Count the frequency of letters in the file
    public static String original = "Heeeee heeee";
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
    
    
    public String decrypt(String encrypted){
     
         CaesarCipher cc = new CaesarCipher();
         int [] freqs = countLetters(encrypted);
         int maxIndex = indexOfMax(freqs);
         System.out.println("Max index is: " + maxIndex);
         int decryptKey = maxIndex - 4;
         System.out.println("Max index is: " + maxIndex);
         if(maxIndex <4){
             decryptKey = 26 -(4 - maxIndex);
         }
         
         System.out.println("Decrypt key is: " + decryptKey);
         
         return cc.encryptCaseSensitive(encrypted, 26 - decryptKey);
    }
    
    
    public void testDecrypt(){
        
	 CaesarCipher cc = new CaesarCipher();
	 String encrypted = cc.encryptCaseSensitive(original, 15);
	 System.out.println("Encrypted is: "+ encrypted);
	 String decrypted = decrypt(encrypted);
	 System.out.println("Decrypted is: " + decrypted);
        
    }
    
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
    
    public void testHalfOfString(){
        String halfString = halfOfString("Qbkm Zgis", 1);
        System.out.println("Half string is: " + halfString);
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
    
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int k1,k2;
        String firstString = halfOfString(encrypted,0);
        String secondString = halfOfString(encrypted,1);
        
        k1 = getKey(firstString);
        k2 = getKey(secondString);
        // Keys are in opposite order
        System.out.println("Key1: " +k2 +" Key1 : " +k2);
        
        return cc.encryptTwoKeys(encrypted, 26-k1,26-k2);
    }
    
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String testString ="";
        for (String s : fr.words()) {
            testString+=" "+ s;
        }
        String decrypted = decryptTwoKeys(testString);
        System.out.println("Decryption of string with 2 keys: " + "\r\n" + decrypted); 
    }
    
    public static void main(String args[]){
        CaesarBreaker obj = new CaesarBreaker();
        obj.testDecrypt();
        
        obj.testHalfOfString();
        obj.testDecryptTwoKeys();
       
    }
    
}

