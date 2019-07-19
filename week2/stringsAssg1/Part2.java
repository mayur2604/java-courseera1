package stringsAssg1;

public class Part2 {
    static String findSimpleGene(String dna){
        int positionOfATG = dna.indexOf("ATG");
        if(positionOfATG == -1)
            return "";
        else{
            int positionOfTAA = dna.indexOf("TAA",positionOfATG);
            if(positionOfTAA == -1)
                return "";
            else{
                int lenOfSubstrBetweenATgAngTAA = (positionOfTAA-1) - (positionOfATG+2);
                if(lenOfSubstrBetweenATgAngTAA%3 == 0)
                    return dna.substring(positionOfATG, positionOfTAA+3);
            }

        }
        return "";

    }

    static void testSimpleGene(){
        String[] dna = {"QWERTYTAA" , "ASDFGATG" , "ZXCVBNM" ,
                "QATGWERTAAB", "QATG1234TAA"
        };
        for(int i =0; i<dna.length;i++)
            System.out.println(dna[i] +" - " +findSimpleGene(dna[i]));
    }

    public static void main(String[] args){
        testSimpleGene();
    }
}
