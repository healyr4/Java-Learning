
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String,Integer> codonMap;
    
    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        String codon;
        Integer value;
        for (int i = start; i<dna.length() -2; i+=3){
            codon = dna.substring(i,i+3);
            if(!codonMap.containsKey(codon)){
                codonMap.put(codon,1);
            }
            
            else{
                value = codonMap.get(codon);
                codonMap.put(codon,value+1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        String mostCommon = "";
        int maxVal = 0;
        int value;
        for(String codon: codonMap.keySet()){
            value = codonMap.get(codon);
            if(value>maxVal){
                maxVal=value;
                mostCommon = codon;
            }
        }

        return mostCommon;
    }
    
    public void printCodonCounts(int start, int end){
        int num_unique = 0;
        int count;
        for(String codon: codonMap.keySet()){
            count = codonMap.get(codon);
            if(start<=count &&  count<=end){
                System.out.println("Codon: "  + codon + "\t"+ "count:" + count);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        for(String line: fr.lines()){
            line = line.trim();
            System.out.println(line);
        }
    }
    
    public void tester1(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        codonMap.clear();
        buildCodonMap(0,dna);
        String mostCommon = getMostCommonCodon();
        printCodonCounts(7,7);
        System.out.println("Most common codon: " + mostCommon +
                            " ,occurs " + codonMap.get(mostCommon) + " times");
        System.out.println("Num unique: " + codonMap.size());
        codonMap.clear();
        buildCodonMap(1,dna); 
        printCodonCounts(6,6);
        System.out.println("Num unique: " + codonMap.size());
        codonMap.clear();
        buildCodonMap(2,dna);
        printCodonCounts(4,4);
        System.out.println("Num unique: " + codonMap.size());
    }
    
}
