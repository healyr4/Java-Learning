
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File; 
import edu.duke.*;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt( String input){
        StringBuilder encryptedString = new StringBuilder(input);
        char currChar,newChar;
        int index;
        boolean flag;
        for(int i=0; i<input.length(); i++){
            currChar = input.charAt(i);
            if (Character.isUpperCase(currChar)){
                flag = false;
            }
            else{
                flag = true;
            }
            // Make it upper case to check against the alphabet
            currChar = Character.toUpperCase(currChar);

            index = alphabet.indexOf(currChar);
            
            if(index!=-1){
                newChar = shiftedAlphabet.charAt(index);
                if (flag) {
                    // Then we want it to be lower case
                   newChar =  Character.toLowerCase(newChar);
                }                
                encryptedString.setCharAt(i,newChar);
            }   
        }
        
        return encryptedString.toString();
    }
    
    public String decrypt(String input){
        String decrypted;
        
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        decrypted = cc.encrypt(input);
        return decrypted;
    }
    
}
