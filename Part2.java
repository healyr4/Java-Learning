
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
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
}
