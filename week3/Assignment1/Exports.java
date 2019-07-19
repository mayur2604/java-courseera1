package course1Week3One;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Exports {
    void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser,"Germany"));
        parser = fr.getCSVParser();
        listExportersTwoProduct(parser,"gold","diamonds");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"gold"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999");
    }
    String countryInfo(CSVParser parser,String country)
    {
        for(CSVRecord record:parser)
        {
            if(record.get("Country").equals(country))
            {
                return record.get("Country")+": "+record.get("Exports")+": "+record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    void listExportersTwoProduct(CSVParser parser,String exportItem1,String exportItem2)
    {
        for(CSVRecord record:parser)
        {
            String exports=record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }
    int numberOfExporters(CSVParser parser,String exportItem)
    {
        int count=0;
        for(CSVRecord record:parser)
        {
            String exports=record.get("Exports");
            if(exports.contains(exportItem))
            {
                count++;
            }
        }
        return count;
    }
    void bigExporters(CSVParser parser,String amount)
    {
        for(CSVRecord record:parser)
        {
            String value=record.get("Value (dollars)");
            if(value.length()>amount.length())
            {
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }
    public static void main(String args[])
    {
        Exports e=new Exports();
        e.tester();
    }
}
