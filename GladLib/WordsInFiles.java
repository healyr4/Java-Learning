
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// Determine which words occur in the greatest number of files,
// and for each word, which files they occur in. 
import edu.duke.*;
import java.util.*;
import java.io.File;
public class WordsInFiles {
    // Maps a word to an ArrayList of files;
    private HashMap<String,ArrayList<String>> wordMap;
    
    public WordsInFiles(){
        wordMap = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f){
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        for(String word: fr.words()){
            if(!wordMap.containsKey(word)){
                ArrayList<String> arrL = new ArrayList<String>();
                arrL.add(fileName);
                wordMap.put(word,arrL);
            }
            
            else{
                ArrayList<String> list = wordMap.get(word);
                if(list.indexOf(fileName)==-1){
                    list.add(fileName);
                    wordMap.put(word,list);
                }
                
            }
    }
    }
    
    private void buildWordFileMap(){
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f :dr.selectedFiles()){
            addWordsFromFile(f);
        }
        
    }
    
    
    public int maxNumber(){
        int maxNumWords = 0;
        int currNum;
        for(ArrayList<String> aL: wordMap.values()){
            currNum = aL.size();
            if (currNum > maxNumWords){
                maxNumWords = currNum;
            }
        }
        return maxNumWords;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> list = new ArrayList<String>();
        int currNum;

        ArrayList<String> tempAl;
        for(String word: wordMap.keySet()){
            tempAl = wordMap.get(word);
            currNum = tempAl.size();
            if (currNum == number){
                list.add(word);
            }
        }
        return list;
    }
    
    public void printFilesIn(String word){
        if (wordMap.containsKey(word)){
            ArrayList<String> arrList = wordMap.get(word);
            printFn(arrList);
        }
        else{
            System.out.println("Not present in files");
        }
    }
    
    private void printFn(ArrayList<String> aL){
        for(int i=0; i<aL.size();i++){
            System.out.println(aL.get(i));
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNumFiles = maxNumber();
        System.out.println("Max num files a word is in: " + maxNumFiles);
        int times = 4;
        ArrayList<String> occursNum = wordsInNumFiles(times);
        System.out.println("The following words appear " + times + " time(s)");
        //printFn(occursNum);
        System.out.println(occursNum.size()+" word appear " + times +" time(s)");
        
        String targetWord = "tree";
        System.out.println("The word-- "+ targetWord +" --appears in the following files:");
        printFilesIn(targetWord);

    }
    
    
    
    
    
}
