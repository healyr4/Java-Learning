
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File; 
import edu.duke.*;
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int k1,k2;

    public CaesarCipherTwo(int key1, int key2){
        k1 = key1;
        k2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(k1) + alphabet.substring(0,k1);
        shiftedAlphabet2 = alphabet.substring(k2) + alphabet.substring(0,k2);
    }
    
    
    public String encryptTwoKeys(String input){
        StringBuilder encryptedString = new StringBuilder(input);
        int i;
        boolean flag;
        char newChar;
        System.out.println("K1: " + k1 + " K2: " + k2);
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
    
    public String decrypt(String encrypted){
        String decrypted = "";
        int keyDecrypt1 = (26 - k1);
        int keyDecrypt2 = (26 - k2);
        System.out.println("Key 1: " + keyDecrypt1 + " Key 2: " + keyDecrypt2);
        System.out.println("K1: " + k1 + " K2: " + k2);
        CaesarCipherTwo objCc2 = new CaesarCipherTwo(keyDecrypt1,keyDecrypt2);
        
        decrypted = objCc2.encryptTwoKeys(encrypted);
        
        return decrypted;
    }
    /*
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String input = fr.asString();        
        CaesarCipherTwo ccTest= new CaesarCipherTwo(2,4);
                //Encrypted String
        String encrypted = ccTest.encryptTwoKeys(input);
        System.out.println("Encrypted is: " + encrypted);
        
        //Decrypted String
        String decrypted = ccTest.decrypt(encrypted);
        System.out.println("Decrypted is: " + decrypted);
    }
    */
}
