package stringAssg3;

public class Part2 {
    static float cgRatio(String dna){
        int numOfCs = 0;
        int numOfGs = 0;
        for(int i=0 ;i<dna.length();i++){
            if(dna.charAt(i) == 'C')
                numOfCs++;
            if(dna.charAt(i) == 'G')
                numOfGs++;
        }

        return (float)numOfCs/numOfGs;

    }

    static int countCTG(String dna){
        int count = 0;
        int startIndex = 0;
        while (true){
            int CTGIndex = dna.indexOf("CTG",startIndex);
            if(CTGIndex == -1)
                break;
            else{
                count++;
                startIndex = CTGIndex+1;
            }
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(countCTG("CTGabcCTGdefCTG"));
        System.out.println(cgRatio("abCGabCCabG"));
    }

}
