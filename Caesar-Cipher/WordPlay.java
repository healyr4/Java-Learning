
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        
        String vowels = "aeiouAEIOU";
        int index = vowels.indexOf(ch);
        if (index == -1){
            return false;
        }
        
        return true;
    }
    
    public void testerIsVowel(){
        char testChar = 'f';
        boolean vowel = isVowel(testChar);
        if(vowel){
            System.out.println(testChar + " is a vowel");
        }
        else{
            System.out.println(testChar + " is not vowel");
        }
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder changed = new StringBuilder(phrase);
        int i;
        boolean flag;
        char currCharacter;
        for(i=0; i<changed.length(); i++){
            currCharacter = changed.charAt(i);
            flag = isVowel(currCharacter);
            if(flag){
                changed.setCharAt(i,ch);
            }
        }
        
        return changed.toString();
    }
    
    public void testReplaceVowels(){
        String phrase = "Hello People";
        char ch = '*';
        String replacedString = replaceVowels(phrase,ch);
        System.out.println(replacedString);
    }
    
    public String emphasise(String phrase, char ch){
        
        StringBuilder changed = new StringBuilder(phrase);
        int i;
        char currChar;
        
        for (i=0; i<changed.length(); i++){
            currChar = changed.charAt(i);
            if (currChar == ch){
                if (i%2 == 0){
                    // Even indx but odd location in string
                    changed.setCharAt(i, '*');
                }
                else{
                    changed.setCharAt(i,'+');
                }
            }
        }
        return changed.toString();
    }
    
    public void testEmphasise(){
        String testString = "Mary Bella Abracadabra";
        char testChar = 'a';    
        String replacedString = emphasise(testString,testChar);
        System.out.println(replacedString);
    }
}
