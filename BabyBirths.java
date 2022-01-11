
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import File;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        
        for(CSVRecord record : parser){
            int numBorn = Integer.parseInt(record.get(2));
            if (numBorn <= 100){
		System.out.println("Name " + record.get(0) +
				   " Gender " + record.get(1) +
				   " Num Born " + record.get(2));                
            }
        }
    }
    
    public void totalBirths(FileResource fr){
        int numBirths = 0;
        int numBoys = 0;
        int numGirls = 0;
        
        for(CSVRecord record : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            numBirths += numBorn;
            if (record.get(1).equals("F")){
               numGirls += numBorn;
            }
            else{
                numBoys += numBorn;
            }
        }
        System.out.println("Num Total births: " + numBirths +
                            " Num girls names: " + numGirls +
                            " Num boys names: " + numBoys);
    }   
    
    public void testTotalBirths() {
        // "C:\\Users\healy\\OneDrive\\JavaCourse1\\Birth-Data\\us_babynames_test\\yob2014short.csv"
        FileResource fr = new FileResource("us_babynames_test/yob2014short.csv");
        totalBirths(fr);
    }
    
    public int getRank(String year, String name, String gender){
        int rank = 0;
        
        FileResource fr = new FileResource("us_babynames_by_year/yob"+ year + ".csv");
        for(CSVRecord record : fr.getCSVParser(false)){
            if (record.get(1).equals(gender)){
                rank += 1;
                if(record.get(0).equals(name)){
                    return rank;
                }
            }
        
        }
        return -1;
    
    }
    
    public String getName(String year, String name, String newYear, String gender){
        int rank = getRank(year,name,gender);
        int counter = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob"+ newYear + ".csv");
        
        for(CSVRecord record : fr.getCSVParser(false)){
            if (record.get(1).equals(gender)){
                counter += 1;
                if(counter == rank){
                    return record.get(0);
                }
            }
        
        }
        return "NO NAME";
    }
    
    public int yearOfHighestRank(String name, String gender){
        int hishestYear = 0;
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
            FileResource fr = FileResource(f);
            for(CSVRecord record : fr.getCSVParser(false)){
                
            
        }
        
    }
    
    public void tester(){
        //Test Rank
        int rank = getRank("1998","Thomas", "M");
        System.out.println("Rank is: " + rank);
        
        //Test what Ranking would be in another year
        String birthYear = "2012";
        String birthName = "Isabella";
        String newYear = "2014";
        String sex = "F";
        String altName = getName(birthYear, birthName, newYear, sex);
        System.out.println(birthName +" born in  " + birthYear
                            + " would be " + altName + " if born in "
                            + newYear);
    }
    
}