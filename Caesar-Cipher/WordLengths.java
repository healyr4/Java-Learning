
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File; 
import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int [] counts){
        // read in the words from resource and count the number of words of each length
        //for all the words in resource, storing these counts in the array counts.
        int index = 0;
        for( String word: resource.words()){
            //word = word.toLowerCase();
            int arrLen = counts.length;
            int wordLen = word.length();
            int nonAlphabetic = 0;
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(wordLen-1);
            if( !Character.isLetter(firstChar)){
                wordLen--;
            }
            if( !Character.isLetter(lastChar)){
                wordLen--;
            }
            
            if (wordLen > arrLen-1){
                counts[arrLen-1]++;
               
            }
            
            else{
            counts[wordLen]++;
            }
        }
        
        
        
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        
        for (int k =0; k<counts.length; k++){
            int numWords = counts[k];
            if(numWords!=0){
                System.out.println(numWords +" word(s) of length " + k);
            }
        }
        int maxIndx = indexOfMax(counts);
        System.out.println("Most common word length is: " + maxIndx);
    }
    
    public int indexOfMax( int[] values){
        int maxElement = 0;
        int maxIndex = 0;
        //maxElement = maxIndex(values);
        for(int i=0; i<values.length; i++){
            
            if (values[i] > maxElement){
                System.out.println(values[i]);
                maxIndex = i;
                maxElement = values[i];
            }
        }
        return maxIndex;
    }
    
    public static void main(String args[]){
        WordLengths object = new WordLengths();
        object.testCountWordLengths();
    }
    
    
}
