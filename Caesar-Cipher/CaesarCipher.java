
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File; 
import edu.duke.*;
public class CaesarCipher {
    
    public String encrypt(String input, int k){
        
        StringBuilder encryptedString = new StringBuilder(input);
        int i;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(k) + alphabet.substring(0,k);
         
        for (i=0;i<encryptedString.length();i++){
            char currChar = encryptedString.charAt(i);
            int index = alphabet.indexOf(currChar);
            if (index != -1){
                char newChar = shiftedAlphabet.charAt(index);
                encryptedString.setCharAt(i,newChar);
            }
            
        }
        return encryptedString.toString();
    }
    
    public String encryptCaseSensitive(String input, int k){
        
        StringBuilder encryptedString = new StringBuilder(input);
        int i;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(k) + alphabet.substring(0,k);
        boolean flag ;
        for (i=0;i<encryptedString.length();i++){
            char currChar = encryptedString.charAt(i);
            if (Character.isUpperCase(currChar)){
                flag = false;
            }
            else{
                flag = true;
            }
            // Make it upper case to check against the alphabet
            currChar = Character.toUpperCase(currChar);
            //System.out.println(currChar);
            int index = alphabet.indexOf(currChar);
            if (index != -1){
                char newChar = shiftedAlphabet.charAt(index);
                if (flag) {
                    // Then we want it to be lower case
                   newChar =  Character.toLowerCase(newChar);
                }
                
                encryptedString.setCharAt(i,newChar);
            }
            
        }
        return encryptedString.toString();
    }
    
    public String encryptTwoKeys(String input, int k1, int k2){
        StringBuilder encryptedString = new StringBuilder(input);
        int i;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(k1) + alphabet.substring(0,k1);
        String shiftedAlphabet2 = alphabet.substring(k2) + alphabet.substring(0,k2);
        
        boolean flag;
        char newChar;
        for (i=0;i<encryptedString.length();i++){
            char currChar = encryptedString.charAt(i);
            if (Character.isUpperCase(currChar)){
                flag = false;
            }
            else{
                flag = true;
            }
            // Make it upper case to check against the alphabet
            currChar = Character.toUpperCase(currChar);
            int index = alphabet.indexOf(currChar);
            if (index != -1){
                if(i%2==0){
                    newChar = shiftedAlphabet1.charAt(index);
                }
                
                else{
                    newChar = shiftedAlphabet2.charAt(index);
                }
                if (flag) {
                    // Then we want it to be lower case
                   newChar =  Character.toLowerCase(newChar);
                }
                
                encryptedString.setCharAt(i,newChar);
            }
        }
        return encryptedString.toString();
    }
    
    public void testEncryptCaseSensitive(){
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        String ans = encryptCaseSensitive(input,key);
        System.out.println(ans);
        String ans2 = encryptCaseSensitive("First Legion", 23); 
        System.out.println(ans2);
    }
    
    public void testCaesar(){
        FileResource fr = new FileResource();
        String text = fr.asString();
        int key = 23;
        String ans = encrypt(text,key);
        System.out.println("key is " + key + "\n" + ans);
    }
    
    public void TestEncryptTwoKeys(){
        String encrypted = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(encrypted);
    }
}
