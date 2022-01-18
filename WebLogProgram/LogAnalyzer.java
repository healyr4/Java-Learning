
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private ArrayList<String> seenIps;
     private ArrayList<String> uniqueIPVisitsSomeDay ;
     private ArrayList<String> uniqueIPsInRange;
     private HashMap<String,Integer> counts;
     private ArrayList<String> mostVisitsIP;
     private HashMap<String,ArrayList<String>> mapDaysArrayIPs;
     private HashMap<String, Integer> mapIPMost;
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         seenIps = new ArrayList<String>();
         uniqueIPVisitsSomeDay = new ArrayList<String>();
         uniqueIPsInRange = new ArrayList<String>();
         counts = new HashMap<String,Integer>();
         mostVisitsIP = new ArrayList<String>();
         mapDaysArrayIPs = new HashMap<String,ArrayList<String>>();
         mapIPMost = new HashMap<String,Integer>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line: fr.lines()){
             LogEntry log;
             log = WebLogParser.parseEntry(line);
             records.add(log);
         }
         
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         int numUnique = 0;
         String ip;
         for(LogEntry le: records){
             ip = le.getIpAddress();
             if(!seenIps.contains(ip)){
                 seenIps.add(ip);
                 numUnique++;
             }
         }
         return numUnique;
     }
     
     public void  printAllHigherThanNum(int num){
         System.out.println("Started");
         for (LogEntry le: records){
             int statusCode = le.getStatusCode();
             if(statusCode > num){
                 System.out.println(le);
             }   
         }
     }
     
     
     // returns an ArrayList of Strings of unique IP addresses that had access
     // on the given day "someday"
     public ArrayList<String> uniqueIPVisitsOnDay (String someday){
         uniqueIPVisitsSomeDay.clear();
         for(LogEntry le: records){
             Date date = le.getAccessTime();
             String str = date.toString();
             String ip =le.getIpAddress();
             if(str.contains(someday) && !(uniqueIPVisitsSomeDay.contains(ip))){
                 uniqueIPVisitsSomeDay.add(ip);
             }
         }
         return uniqueIPVisitsSomeDay;
     }
     
     public int countUniqueIPsInRange(int low, int high){
        //int numIPs = 0;
        
        for(LogEntry le: records){
            int code = le.getStatusCode();
            String ip = le.getIpAddress();
            if(code >= low && code <= high && !(uniqueIPsInRange.contains(ip))){
                uniqueIPsInRange.add(ip);
            }
        }

        return uniqueIPsInRange.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         counts = new HashMap<String,Integer>();
         for( LogEntry le: records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
             }
             else{
                 Integer val = counts.get(ip);
                 counts.put(ip,val+1);
             }      
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> map){
         int maxNumVisits = 0;
         String mostCommonIP;
         int numVisits;
         for(String ip: counts.keySet()){
             numVisits = counts.get(ip);
             if(numVisits > maxNumVisits){
                 maxNumVisits = numVisits;
                 mostCommonIP = ip;
             }
         }
         return maxNumVisits;
     }
        
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
         int maxNumVisits = mostNumberVisitsByIP(counts);
         int val;
         for(String ip: counts.keySet()){
             //System.out.println(ip);
             val = counts.get(ip);
             if(val == maxNumVisits){
                 mostVisitsIP.add(ip);
             }
         }
         return mostVisitsIP;
     }
     
     // maps days from web logs to an ArrayList of IP addresses
     // that occurred on that day 
     public HashMap<String, ArrayList<String>> iPsForDays(){
         String ip;
        for (LogEntry le: records){
             Date d = le.getAccessTime();
             String date = d.toString().substring(4,10);
             ip = le.getIpAddress();
             if(!mapDaysArrayIPs.containsKey(date)){
                 ArrayList<String> newIPArrayL= new ArrayList<String>();
                 newIPArrayL.add(ip);
                 mapDaysArrayIPs.put(date,newIPArrayL);
             }
             
             else{
                 ArrayList<String> tempAl =  mapDaysArrayIPs.get(date); 
                 tempAl.add(ip);
                 mapDaysArrayIPs.put(date,tempAl);
             }
        }
        return mapDaysArrayIPs;
     }
        
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> mapDaysArrayIPs){
         String dayMax = "";
         int maxIPs = 0;
         int numIPs = 0;
         for(String day: mapDaysArrayIPs.keySet()){
             numIPs = (mapDaysArrayIPs.get(day)).size();
             if(numIPs > maxIPs){
                 maxIPs = numIPs;
                 dayMax = day;
             } 
         }
         return dayMax;
     }
         
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> mapDaysArrayIPs, String day){
         for(String day1: mapDaysArrayIPs.keySet()){
             if(day1.equals(day)){
                 for(String ip: mapDaysArrayIPs.get(day1)){
                     if(!mapIPMost.containsKey(ip)){
                         mapIPMost.put(ip,1);
                     }
                     else{
                         Integer val = mapIPMost.get(ip);
                         mapIPMost.put(ip,val+1);
                     }
                        
                 }
                 return iPsMostVisits(mapIPMost);
             }
         }
         return null;   
     }   
}
