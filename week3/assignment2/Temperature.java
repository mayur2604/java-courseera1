package course1Week3Two;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class Temperature {
    CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestSoFar=null;
        for(CSVRecord record:parser)
        {
          coldestSoFar=coldestOfBoth(coldestSoFar,record);
        }
        return coldestSoFar;
    }
    void testColdestHourInFile()
    {
        FileResource fr = new FileResource("course1Week3Two/nc_weather/2012/weather-2012-01-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour=coldestHourInFile(parser);
        System.out.println("The coldest temperature is "+coldestHour.get("TemperatureF")+" at "+coldestHour.get("TimeEST"));
    }
    String fileWithColdestTemperature()
    {
      DirectoryResource dr=new DirectoryResource();
      String fileName="";
      CSVRecord coldestSoFar=null;
      for(File f:dr.selectedFiles())
      {
          FileResource fr=new FileResource(f);
          CSVRecord record=coldestHourInFile(fr.getCSVParser());
          coldestSoFar=coldestOfBoth(coldestSoFar,record);
          fileName=f.getAbsolutePath();
      }
      return fileName;
    }
    void testFileWithColdestTemperature()
    {
        String fileName=fileWithColdestTemperature();
        File f=new File(fileName);
        System.out.println("Coldest day was in file "+f.getName());
        FileResource fr=new FileResource(fileName);
        System.out.println("Coldest temperature on that day was "+coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord record:fr.getCSVParser())
        {
            System.out.println(record.get("DateUTC"));
        }
    }
    CSVRecord coldestOfBoth(CSVRecord coldestSoFar,CSVRecord record)
    {
            if(coldestSoFar==null)
                coldestSoFar=record;
            else
            {
                if(Double.parseDouble(coldestSoFar.get("TemperatureF"))>Double.parseDouble(record.get("TemperatureF")))
                {
                    coldestSoFar=record;
                }
            }
            return coldestSoFar;
    }
    CSVRecord lowestHumidityOfBoth(CSVRecord LowestSoFar,CSVRecord record)
    {
        if(LowestSoFar==null)
            LowestSoFar=record;
        else
        {
            if(Integer.parseInt(LowestSoFar.get("Humidity"))>Integer.parseInt(record.get("Humidity")))
            {
                LowestSoFar=record;
            }
        }
        return LowestSoFar;
    }
    CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord LowestHumidySoFar=null;
        for(CSVRecord record:parser)
        {
            if(!record.get("Humidity").equals("N/A"))
            LowestHumidySoFar=lowestHumidityOfBoth(LowestHumidySoFar,record);
        }
        return LowestHumidySoFar;
    }
    void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr=new DirectoryResource();
        String fileName="";
        CSVRecord lowestHumidySoFar=null;
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            CSVRecord record=lowestHumidityInFile(fr.getCSVParser());
            lowestHumidySoFar=lowestHumidityOfBoth(lowestHumidySoFar,record);
        }
        return lowestHumidySoFar;
    }
    void testLowestHumidityInManyFiles()
    {
        CSVRecord csv=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    double averageTemperatureInFile(CSVParser parser)
    {
        double average=0.0;
        int count=0;
        for(CSVRecord record:parser)
        {
            average+=Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        return average/count;
    }
    void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource("course1Week3Two/nc_weather/2014/weather-2014-01-20.csv");
        CSVParser parser = fr.getCSVParser();
        double averageTemp=averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+ averageTemp);
    }
    double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value)
    {

        double average=0.0;
        int count=0;
        for(CSVRecord record:parser)
        {
            if(!record.get("Humidity").equals("N/A") && Integer.parseInt(record.get("Humidity"))>=value) {
                average += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        if(count==0)
            return 0.0;
        return average/count;
    }
    void testAverageTemperatureWithHighHumidityInFile()
    {

        FileResource fr = new FileResource("course1Week3Two/nc_weather/2014/weather-2014-03-20.csv");
        CSVParser parser = fr.getCSVParser();
        double averageTemp=averageTemperatureWithHighHumidityInFile(parser,80);
        if(averageTemp!=0.0)
        System.out.println("Average temperature when high Humidity is "+ averageTemp);
        else
            System.out.println("No temperatures with that humidity");
    }
    public static void main(String args[])
    {
        Temperature t=new Temperature();
        t.testColdestHourInFile();
        t.testFileWithColdestTemperature();
        t.testLowestHumidityInFile();
        t.testLowestHumidityInManyFiles();
        t.testAverageTemperatureInFile();
        t.testAverageTemperatureWithHighHumidityInFile();
    }
}
