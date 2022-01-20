
/**
 * Write a description of CountryExports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExports {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //Need to reset when using with another method
        //parser = fr.getCSVParser();
        String ans = countryInfo(parser,"Nauru");
        System.out.println(ans);
        
        //parser = fr.getCSVParser();
        //String ans2 = countryInfo(parser,"Germany");
        //System.out.println(ans2);
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"gold","diamonds");
        
        // Num countries export item
        parser = fr.getCSVParser();
        String testItem = "gold";
        int numCountries = numberOfExporters(parser,testItem);
        System.out.println(numCountries +" export " + testItem);
        
        //Big Exporters
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
        
    }
    
    public String countryInfo(CSVParser parser, String country){
        String countryName = "";
        String exports = "";
        String value = "";

        for (CSVRecord record: parser){
             countryName= record.get("Country");

             if (countryName.equals(country)){
                 exports = record.get("Exports");
                 value = record.get("Value (dollars)");
                 return (countryName +": " + exports + ": " + value);
                }
             
        }
        return "NOT FOUND";
    }
    
    
    public void listExportersTwoProducts(CSVParser parser, String exp1, String exp2){
        String exports;
        for(CSVRecord record : parser){
            exports = record.get("Exports");
            if (exports.contains(exp1) && exports.contains(exp2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        String exports = "";
        for(CSVRecord record: parser){
            exports = record.get("Exports");
            if (exports.contains(exportItem)){counter++;}
        }
        return counter;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int len = amount.length();
        String value = "";
        for (CSVRecord record : parser){
            value = record.get("Value (dollars)");
            if (len < value.length()){
                
                System.out.println(record.get("Country")+ value );
            }
        }
    }
}
