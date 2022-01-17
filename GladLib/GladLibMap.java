
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private int numSubstitutions;
    private Random myRandom;
    
    private HashMap<String,ArrayList<String>> fileMap;
    private ArrayList<String> seenWords;
    private ArrayList<String> seenCategories;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] category = {"adjective", "noun", "color","country", 
                            "name", "animal", "timeframe", "verb", "fruit"};
        fileMap = new HashMap<String,ArrayList<String>>();
        for (String word : category){
            fileMap.put(word,
            readIt(source+"/"+word+".txt"));
        }
        seenWords = new ArrayList<String>();
        seenCategories = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (seenCategories.indexOf(label) == -1){
            seenCategories.add(label);
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if(fileMap.containsKey(label)){
            ArrayList<String> value = fileMap.get(label);
            return randomFrom(value);
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        while(seenWords.indexOf(sub)!= -1){
                sub = getSubstitute(w.substring(first+1,last));
        }
        seenWords.add(sub);
        numSubstitutions+=1;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int count = 0;
        int size;
        ArrayList<String> value;
        for (String word: fileMap.keySet()) {
            value = fileMap.get(word);
            size = value.size();
            count += size;
        }
        return count;
    }    
    
    public int totalWordsConsidered(){
        int total = 0;
        ArrayList<String> value;
        for(String categ : seenCategories){
            value = fileMap.get(categ);
            //System.out.println("CAtegory: " + categ);
            // Number will have a null value!!
            if(value!= null){
                //System.out.println("Size" + value.size());
                total += value.size();
        }
        }
        
        return total;
    }
    
    public void makeStory(){
        seenWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\r\n" + numSubstitutions +" Substitutions were made.");
    }
    
    public void tester(){
        seenWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\r\n" + numSubstitutions +" Substitutions were made.");
        
        int count = totalWordsInMap();
        System.out.println("\r\n" + count +" words in map");
        
        int totalConsidered = totalWordsConsidered();
        System.out.println("\r\n" + totalConsidered +" words considered");
    }

}
