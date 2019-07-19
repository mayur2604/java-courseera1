package stringAssg2;

public class Part1 {

    static int findStopCodon(String dna, int startIndex, String codon){
        int currIndex = dna.indexOf(codon,startIndex+3);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff%3 == 0)
                return currIndex;
            else {
                currIndex = dna.indexOf(codon, currIndex+1);
            }
        }
        return dna.length();
    }

    static String findGene(String dna){

        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
            return "";
        else{
            int indexOfTAA = findStopCodon(dna,startIndex,"TAA");
            int indexOfTAG = findStopCodon(dna,startIndex,"TAG");
            int indexOfTGA = findStopCodon(dna,startIndex,"TGA");

            int minIndex = Math.min(indexOfTAA,indexOfTAG);
            minIndex = Math.min(minIndex,indexOfTGA);

            if(minIndex == dna.length())
                return "";

                return dna.substring(startIndex,minIndex+3);
        }
    }

    static void testFindStopCodon(){
        System.out.println("abcATGdefTAA");
        System.out.println(findStopCodon("abcATGdefTAA",0, "TAA"));

        System.out.println("abcATGdefgTGA");
        System.out.println(findStopCodon("abcATGdefgTGA",0, "TGA"));

        System.out.println("abcATGdefgTAAhiTAG");
        System.out.println(findStopCodon("abcATGdefgTAAhiTAG",0, "TAG"));
    }

    static void testFindGene(){
        System.out.println(findGene("abcdef"));
        System.out.println(findGene("abATGcdef"));
        System.out.println(findGene("abcATGdefTAGghi"));
        System.out.println(findGene("abATGdefTAAghTAGijTGA"));

    }

    static  void printAllGenes(String dna){
        System.out.println("All the genes present in "+dna+" are");
        int indexOfATG = dna.indexOf("ATG");
        if(indexOfATG == -1)
            return ;
        int startIndex = indexOfATG;
        while(startIndex <dna.length()) {

            int indexOfTAA = findStopCodon(dna,startIndex+3,"TAA");
            int indexOfTAG = findStopCodon(dna,startIndex+3,"TAG");
            int indexOfTGA = findStopCodon(dna,startIndex+3,"TGA");

            int minIndex = Math.min(indexOfTAA, indexOfTAG);
            minIndex = Math.min(minIndex, indexOfTGA);

            if(minIndex == dna.length())
                return;

            System.out.println(dna.substring(indexOfATG,minIndex+3));
            startIndex = minIndex;
        }

    }


    public static void main(String[] args){
        System.out.println("Testing Find Stop Codon");
        testFindStopCodon();
        System.out.println("Testing Find Gene");
        testFindGene();
        //                  012345678901234567
        printAllGenes("abcATGdefTAAhijTAG");
    }
}
