
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public static String findSimpleGene(String dna, int start_index, int end_index){
        System.out.println("Start index: " + start_index);
        if (start_index == -1){
            return "";
        }
        
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
    
    public static int[] getIndices(String dna){
        String capDna = dna.toUpperCase();
        int start_index = capDna.indexOf("ATG");
        int end_index = capDna.indexOf("TAA",start_index);
        int[] indices = {start_index,end_index};
        return indices;
    }

    public static int getEndIndex(String dna){
        int start_index = dna.indexOf("ATG");
        return start_index;
    }
    
    public static void testSimpleGene(){
        String string1 = "atgaagtaa"; 
        int indicesAns1[] = getIndices(string1);
        String ans1 = findSimpleGene(string1,indicesAns1[0],indicesAns1[1]);
        System.out.println("String 1 is: " + string1 +" Answer is"+ans1);
        
        String string2 = "AAGAGAATGTAA";
        int indicesAns2[] = getIndices(string2);
        String ans2 = findSimpleGene(string2,indicesAns2[0],indicesAns2[1]);
        System.out.println("String 2 is: " + string1 +" Answer is"+ans2);
        
        String string3 = "ATGAGAATGTGA";
        int indicesAns3[] = getIndices(string3);
        String ans3 = findSimpleGene(string3,indicesAns3[0],indicesAns3[1]);
        System.out.println("String 3 is: " + string3 +" Answer is"+ans3);
        
        String string4 = "ATGAGATGTAA";
        int indicesAns4[] = getIndices(string4);
        String ans4 = findSimpleGene(string4,indicesAns4[0],indicesAns4[1]);
        System.out.println("String 4 is: " + string4 +" Answer is"+ans4);
        
        String string5 = "";
        int indicesAns5[] = getIndices(string5);
        String ans5 = findSimpleGene(string5,indicesAns5[0],indicesAns5[1]);
        System.out.println("String 5 is: " + string5 +" Answer is"+ans5);
   
    }

}
