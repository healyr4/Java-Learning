
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public static String findSimpleGene(String dna){
        int start_index = dna.indexOf("ATG");
        System.out.println("Start index: " + start_index);
        if (start_index == -1){
            return "";
        }
        
        int end_index = dna.indexOf("TAA",start_index);
        if(end_index == -1){
            return "";
        }
        System.out.println("End index: " + end_index);
        
        int diff = start_index - end_index;
        if (diff%3==0){
            return dna.substring(start_index,end_index+3);
        }
        
        return "";
    }
       
    public static void testSimpleGene(){
        String string1 = "ATGAGAAAGTAA";
        System.out.println("String 1 is: " + string1);
        String ans1 = findSimpleGene(string1);
        System.out.println("String 1 is: " + string1 +" Answer is"+ans1);
        
        String string2 = "AAGAGAATGTAA";
        String ans2 = findSimpleGene(string2);
        System.out.println("String 2 is: " + string1 +" Answer is"+ans2);
        
        String string3 = "ATGAGAATGTGA";
        String ans3 = findSimpleGene(string3);
        System.out.println("String 3 is: " + string3 +" Answer is"+ans3);
        
        String string4 = "ATGAGATGTAA";
        String ans4 = findSimpleGene(string4);
        System.out.println("String 4 is: " + string4 +" Answer is"+ans4);
        
        String string5 = "";
        String ans5 = findSimpleGene(string5);
        System.out.println("String 5 is: " + string5 +" Answer is"+ans5);
   
    }

}
