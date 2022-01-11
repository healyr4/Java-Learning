
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
       int currIndex = 0;
       int diff = 0;
       currIndex = dna.indexOf(stopCodon,startIndex+3);
       while (currIndex != -1){
           diff = currIndex - startIndex;
           if (diff%3==0){
               return currIndex;
           }
           else {
               currIndex = dna.indexOf(stopCodon, currIndex+1);
           }
       }
        
       return dna.length(); 
    }
    
    public void testFindStopCodon(){
        int test1 = findStopCodon("AGTACTTAATAG",0, "TAG");
        System.out.println("Test index is: " + test1);
        int test2 = findStopCodon("ACTAGTACTTAATAG",0, "TGA");
        System.out.println("Test index is: " + test2);  
        int test3 = findStopCodon("AGTGATTAATAG",0, "TGA");
        System.out.println("Test index is: " + test3);   
        
        String test4 = findGene("ACTATGATTATATAA");
        System.out.println("Gene is: " + test4);  
    }
    
    public String findGene(String dna){
        int start_index = dna.indexOf("ATG");
        if(start_index == -1){
            return ""; 
        }
        
        int taaIndex = findStopCodon(dna, start_index, "TAA");
        int tagIndex = findStopCodon(dna, start_index, "TAG");
        int tgaIndex = findStopCodon(dna, start_index, "TGA");
        //System.out.println("TAA : " + taaIndex);
        //System.out.println("TAG : " + tagIndex);
        //System.out.println("TGA : " + tgaIndex);
        int minIndex = -1;
        if( taaIndex > -1 && taaIndex < tagIndex){
            minIndex = taaIndex;
        }
        else{
            minIndex = tagIndex;
        }
            
        if (tgaIndex > -1 && tgaIndex < minIndex){
            minIndex = tgaIndex;
        }
        
        if (minIndex == -1 || minIndex >= dna.length()-1){
            return "";
        }
        
        else{
            return dna.substring(start_index,minIndex +3);
        }
        
    
    }
    //Used method overloading 
    public String findGene(String dna, int startIndex){
        int start_index = dna.indexOf("ATG",startIndex);
        if(start_index == -1){
            return ""; 
        }
        
        int taaIndex = findStopCodon(dna, start_index, "TAA");
        int tagIndex = findStopCodon(dna, start_index, "TAG");
        int tgaIndex = findStopCodon(dna, start_index, "TGA");
        //System.out.println("TAA : " + taaIndex);
        //System.out.println("TAG : " + tagIndex);
        //System.out.println("TGA : " + tgaIndex);
        int minIndex = -1;
        if( taaIndex > -1 && taaIndex < tagIndex){
            minIndex = taaIndex;
        }
        else{
            minIndex = tagIndex;
        }
            
        if (tgaIndex > -1 && tgaIndex < minIndex){
            minIndex = tgaIndex;
        }
        
        if (minIndex == -1 || minIndex >= dna.length()-1){
            return "";
        }
        
        else{
            return dna.substring(start_index,minIndex +3);
        }
        
    
    }
    
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String strand = findGene(dna,startIndex);
            //System.out.println("Strand is: " + strand);
            if (strand.isEmpty()){
                System.out.println("Done");
                break;
            }
            System.out.println(strand);
            
            startIndex = dna.indexOf(strand,startIndex) + strand.length();
            //System.out.println("Start index: "+startIndex);
        }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true){
            String strand = findGene(dna,startIndex);
            //System.out.println("Strand is: " + strand);
            if (strand.isEmpty()){
                System.out.println("Done");
                break;
            }
            geneList.add(strand);
            
            startIndex = dna.indexOf(strand,startIndex) + strand.length();
            //System.out.println("Start index: "+startIndex);
        }
        return geneList;
    }
    
    public void testPrintAllGenes(){
        System.out.println("STARTING TESTING ");
        printAllGenes("CTATGCCTTAAATTATGGTTTAG");
    }
    
    public void getAllGenes(){
        System.out.println("STARTING TESTING ");
        StorageResource geneList = getAllGenes("CTATGCCTTAAATTATGGTTTAG");
        for (String gene: geneList.data()){
            System.out.println("Gene: " + gene);
        }
        
    }
    
}
