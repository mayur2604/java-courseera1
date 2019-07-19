package Baby_Names; /**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 *
 * @author Duke Software Team
 */

import edu.duke.*;
import org.apache.commons.csv.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num Born " + rec.get(2));
            }
        }
    }

    public static void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public static void testTotalBirths() {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }

    public static int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        int max = 0;
        List<Integer> l = new ArrayList<>();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            l.add(numBorn);
        }
        Collections.sort(l, Collections.reverseOrder());
        //for(int i=0;i<10;i++)
        //System.out.println(l.get(i));
        int births = 0, f = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) && rec.get(0).equals(name)) {
                births = Integer.parseInt(rec.get(2));
                f = 1;
            }
        }
        if (f == 0)
            return -1;
        else return l.indexOf(births) + 1;

    }

    public static String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        int max = 0;
        List<Integer> l = new ArrayList<>();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            l.add(numBorn);
        }
        Collections.sort(l, Collections.reverseOrder());
        //for(int i=0;i<10;i++)
        //System.out.println(l.get(i));
        int births = 0, f = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) && Integer.parseInt(rec.get(2)) == l.get(rank - 1))
                return rec.get(0);
        }
        return "NO NAME";

    }

    public static String whatNameInYear(String name, int year, int newYear, String gender) {
        //   FileResource fr1 = new FileResource("data/yob" + year + ".csv");
        // FileResource fr = new FileResource("data/yob" + newYear + ".csv");
        // List<Integer> l1 = new ArrayList<>();
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        return newName;

    }

    public static int yearOfHighestRank(String name, String gender) {

        int rank1 = getRank(2012, name, gender);
        int rank2 = getRank(2013, name, gender);
        int rank3 = getRank(2014, name, gender);
        if (rank1 < rank2 && rank1 < rank3)
            return 2012;
        else if (rank2 < rank3 && rank2 < rank1)
            return 2013;
        else return 2014;
    }

    public static double getAverageRank(String name, String gender) {

        int rank1 = getRank(2012, name, gender);
        int rank2 = getRank(2013, name, gender);
        int rank3 = getRank(2014, name, gender);
        if (rank1 == -1 || rank2 == -1 || rank3 == -1)
            return -1.0;
        return (rank1 + rank2 + rank3) / 3.0;
    }

    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        int max = 0;
        List<Integer> l = new ArrayList<>();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            l.add(numBorn);
        }
        Collections.sort(l, Collections.reverseOrder());
        int rank = getRank(year, name, gender);
        int total = 0;
        for (int i = 0; i < rank; i++)
            total += l.get(i);
        return total;
    }

    public static void main(String[] args) {
        testTotalBirths();
        System.out.println(getRank(2012, "Mason", "M"));
        System.out.println(getName(2014, 5, "M"));
        System.out.println("Mason born in " + 2012 + " would be " + whatNameInYear("Mason", 2012, 2014, "M") + " if she was born in " + 2014);
        System.out.println(yearOfHighestRank("Mason", "M"));
        System.out.println(getAverageRank("Mason", "M"));
        System.out.println(getTotalBirthsRankedHigher(2012, "Mason", "M"));
    }


}
