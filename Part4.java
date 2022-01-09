
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
public class Part4 {
    public static void getLink(String url){
        System.out.println(url);
        
        // Now read in from URL word for word
        URLResource ur = new URLResource(url);
        for (String word : ur.lines()){
            String lower_str = word.toLowerCase();
            int start_index = lower_str.indexOf("youtube.com");
            
            if (start_index != -1){
                int first_index = lower_str.lastIndexOf("\"",start_index);
                int last_index = lower_str.indexOf("\"",first_index +1);
                //System.out.println(first_index + " and "+ last_index);
                System.out.println(word.substring(first_index+1,last_index));
                
            }
            
        }
        
        
        
    }
    
    public static void tester(){
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        getLink(url);
    }
        

}
