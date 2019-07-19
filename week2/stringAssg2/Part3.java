package stringAssg2;

public class Part3 {

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

    static void countGene(String dna) {
        String dnaStr = dna;
        int numOfGenes = 0;
        while(true){
            String gene = findGene(dna);
            if(gene.equals(""))
                break;
            else{
                int indexOfGene = dna.indexOf(gene);
                numOfGenes++;
                dna = dna.substring(indexOfGene + gene.length());
            }
        }
        System.out.println("Number of genes in "+ dnaStr +" = "+numOfGenes);
    }

    static  void printAllGenes(String dna){
        System.out.println("All the genes present in "+dna+" are");

        while(true){
            String gene = findGene(dna);
            if(gene.equals(""))
                break;
            else{
                System.out.println(gene);
                int indexOfGene = dna.indexOf(gene);
                dna = dna.substring(indexOfGene + gene.length());
            }
        }

    }


    static void testCountGenes(){
        //              012345678901234567
        countGene("abcATGdefgTAAhiTAG");
        printAllGenes("abcATGdefgTAAhiTAG");

        countGene("aATGTAAGATGCCCTAGT");
        printAllGenes("aATGTAAGATGCCCTAGT");
    }


    public static void main(String[] args){
        testCountGenes();

    }

}
