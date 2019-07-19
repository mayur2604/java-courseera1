package stringAssg3;

import edu.duke.StorageResource;
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


    static  StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int indexOfATG = dna.indexOf("ATG");
        if(indexOfATG == -1)
            return geneList;
        int startIndex = indexOfATG;
        while(startIndex <dna.length()) {

            int indexOfTAA = findStopCodon(dna,startIndex+3,"TAA");
            int indexOfTAG = findStopCodon(dna,startIndex+3,"TAG");
            int indexOfTGA = findStopCodon(dna,startIndex+3,"TGA");

            int minIndex = Math.min(indexOfTAA, indexOfTAG);
            minIndex = Math.min(minIndex, indexOfTGA);

            if(minIndex == dna.length())
                return geneList;

            geneList.add(dna.substring(indexOfATG,minIndex+3));
            startIndex = minIndex;
        }
        return geneList;

    }




    public static void main(String[] args){

        //                  012345678901234567
        StorageResource geneList = getAllGenes("abcATGdefTAAhijTAG");
        for(String gene : geneList.data())
            System.out.println(gene);
    }
}
