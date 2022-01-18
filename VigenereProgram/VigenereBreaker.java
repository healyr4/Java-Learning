import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedMessage = new StringBuilder();
        char myChar;
        for(int i=whichSlice;i<message.length();i+=totalSlices){
            myChar = message.charAt(i);
            slicedMessage.append(myChar);
        }
        return slicedMessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        String slice;
        for(int i = 0; i<klength;i++){
            slice = sliceString(encrypted,i,klength);
            int keyVar = cc.getKey(slice);
            key[i] = keyVar;
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> wordSet = new HashSet();
        for(String word: fr.words()){
            word = word.toLowerCase();
            wordSet.add(word);
        }
        return wordSet;
    }
    
    //public 
    public int countWords(String message, HashSet<String> wordSet){
        int numRealWords = 0;
        for(String word: message.split("\\W")){
            if(wordSet.contains(word.toLowerCase())){
                numRealWords++;
            }
        }
        return numRealWords;
    }
    
    public char mostCommonCharIn(HashSet<String> words){
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        char maxOccuring = 'e';
        int maxValue = 0;
        int currValue;
        
        for(String word: words){
            for (char ch: word.toCharArray()){
                if (!charMap.containsKey(ch)){
                    charMap.put(ch,1);
                }
                else{
                    charMap.put(ch, charMap.get(ch)+1);
                }
            }
            
            for( char currChar: charMap.keySet()){
                currValue = charMap.get(currChar);
                if(currValue > maxValue){
                    maxValue = currValue;
                    maxOccuring = currChar;
                }
            }
            
        }
        return maxOccuring;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxOccuring = 0;
        String actualLanguage;
        String decrypted;
        //FileResource fr = new FileResource("dictionaries/English.txt");
        for (String language: languages.keySet()){
            FileResource fr = new FileResource("dictionaries/" +language +".txt");
            decrypted = breakForLanguage(encrypted,readDictionary(fr));
            System.out.println(decrypted.substring(0,30));
        }
    }
    
    
    
    //"C:\Users\healy\OneDrive\JavaCourse1\VigenereProgram\dictionaries\English"
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        
        int maxWordCount = 0;
        //int keyLength = 0;
        int wordCount;
        int start = 38;
        int end = 39;
        String decrypted;
        int [] key;
        int [] correctKey = null;
        
        for(int i = start; i<end; i++){
            key = tryKeyLength(encrypted,i,'e');
            VigenereCipher vc = new VigenereCipher(key);
            decrypted = vc.decrypt(encrypted);
            wordCount = countWords(decrypted,dictionary);
            if(wordCount > maxWordCount){
                maxWordCount = wordCount;
                correctKey = key;
            }
            
        }
        VigenereCipher vc2 = new VigenereCipher(correctKey);
        System.out.println("Key is: " + Arrays.toString(correctKey));
        System.out.println("Key length is: " + correctKey.length);
        System.out.println("Num valid words: " + maxWordCount);
        
        decrypted = vc2.decrypt(encrypted);
        return decrypted;
    }
    
    public void breakVigenere1 () {
        FileResource fr = new FileResource();
        int [] key = tryKeyLength(fr.asString(),4,'e');
        System.out.println(java.util.Arrays.toString(key));
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(fr.asString());
        System.out.println(decrypted.substring(0,150));
        
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        FileResource fr2 = new FileResource();
        HashSet<String> dict = readDictionary(fr2);
        String decrypted = breakForLanguage(fr.asString(), dict);
        System.out.println(decrypted.substring(0,150));
    }    
    
    
    public void testSliceString(){
        String ans = sliceString("abcdefghijklm", 4, 5);
        System.out.println(ans);
    }
    
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        int [] key = tryKeyLength(fr.asString(),38,'e');
        System.out.println(java.util.Arrays.toString(key));
    }
}
