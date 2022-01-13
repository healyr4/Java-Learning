

import edu.duke.*;
import java.io.File;
public class Part4 {
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
    
    public float cgRatio(String dna){
        float fraction = 0;
        int numGC = 0;
        int stringLength = dna.length();
        // Finding num Gs
        for(int i=0;i<stringLength;i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                numGC += 1;
            }
        }
        //System.out.println("RC is: " + numGC);
        fraction = (float)(numGC) /(float)(stringLength);
        // Finding num Cs
        return fraction;
        
    }
    
    public void testCG(){
        float ans1 = 0;
        ans1 = cgRatio("ATGCCATAG");
        System.out.println("Ratio is: " + ans1);

    }
        public void testCTG(){
        int counter = countCTG("CTCCTGCTGCTGCTTCTG");
        System.out.println("CTG occurs: " + counter);
    }
    
    public int countCTG(String dna){
        String codon = "CTG";
        int startIndex = 0;
        int currIndex = 0;
        int count = 0;
        while(true){
            currIndex = dna.indexOf(codon,startIndex);
            if (currIndex == -1){break;}
            count +=1; 
            startIndex = currIndex +3;
    }
        return count;
    }
    
    public void processGenes(StorageResource sr){
        int numLonger9 = 0;
       
        System.out.println("Strings of length greater than 9: ");
        for (String str: sr.data()){
            if (str.length() >60) {
                System.out.println(str);
                numLonger9+=1;
            }
        }
        System.out.println("------------------------");
        System.out.println("Num strings greater than 9: " + numLonger9);
        
        System.out.println("------------------------");
        System.out.println(" Strings whose C-G-ratio > 0.35: ");
        float cgRatio = 0;
        int numCG = 0;
        for (String str2: sr.data()){
            cgRatio = cgRatio(str2);
            if(cgRatio > 0.35){
                System.out.println(str2);
                numCG+=1;
            }
        }
        System.out.println("------------------------");
        System.out.println(" Num Strings whose C-G-ratio > 0.35: " + numCG);
        System.out.println("------------------------");
        
        String longestGene = "";
        for (String str3: sr.data()){
            if (str3.length() > longestGene.length()){
                longestGene = str3;
            }
            
        }
        System.out.println("Longest Gene Length: " + longestGene.length());
        
    }
    public void testProcessGenes(){
        StorageResource test1 = new StorageResource();
        test1.add("AGTACTTAA");
        test1.add("ACTAGTATGTAG");
        test1.add("AGCGCCATGGTA");
        processGenes(test1);
    }    
    public void testProcessGenes2(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        System.out.println("Testing dna file: " + dna);
        StorageResource str = getAllGenes(dna);
        processGenes(str);
        int numCTG = countCTG(dna);
        System.out.println("------------------------");        
        System.out.println("CTG occurs: " + numCTG);
    }    
}

