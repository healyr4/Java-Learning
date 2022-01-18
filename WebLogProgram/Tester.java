
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    private String filename = "weblog2_log";
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("short-test_log");
        obj.printAll();
    }
    
    public void testUniqueIp(){
        LogAnalyzer laObj = new LogAnalyzer();
        laObj.readFile("weblog2_log");
        
        int numUnique = laObj.countUniqueIPs();
        System.out.println("Num unique IPs: " + numUnique);
        //laObj.printAllHigherThanNum("Dec 05");
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        String day = "Sep 27";
        LogAnalyzer laObj = new LogAnalyzer();
        laObj.readFile("weblog2_log");
        //laObj.countUniqueIPs();
        ArrayList<String> uniqueIPs = laObj.uniqueIPVisitsOnDay(day);
        int size = uniqueIPs.size();
        if(size == 0){
            System.out.println("No unique ip visits on " + day);
        }
        else{
            System.out.println(size + " Unique Ips on " + day+":");
            for(String s: uniqueIPs){
                System.out.println(s);
            }
        }
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        int low = 400;
        int high = 499;
        la.readFile("weblog2_log");
        int numUnique = la.countUniqueIPsInRange(low,high);
        
        System.out.println("Number of unique IPs with status codes in range "+low +" and "
                            +high+ " is: " + numUnique);
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String,Integer> countVisits = new HashMap<String,Integer>();
        countVisits = la.countVisitsPerIP();
        int numUnique = countVisits.size();
        System.out.println(numUnique + " unique visitors");
        System.out.println(countVisits);
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String,Integer> countVisits = la.countVisitsPerIP();
        int maxNum = la.mostNumberVisitsByIP(countVisits);
        System.out.println("Max num visits per ip: " + maxNum);
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String,Integer> countVisits = la.countVisitsPerIP();
        
        int maxNum = la.mostNumberVisitsByIP(countVisits);
        ArrayList<String> list = la.iPsMostVisits(countVisits);
        System.out.println("Ips with max num of visits " + maxNum +" :");
        System.out.println(list);

    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        System.out.println("Hash Map IPs For Days:");
        System.out.println(ipDays);
        
    }
    public void TestDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        HashMap<String, ArrayList<String>> ipDays = la.iPsForDays();
        String maxDay = la.dayWithMostIPVisits(ipDays);
        System.out.println("Day with most IP visits: " + maxDay);
    }
}
