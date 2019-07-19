package stringAssg2;

public class Part2 {
    static void howMany(String stringA, String stringB){
        int lenOfStringA = stringA.length();
        int startIndex = 0;
        int numOfOccurences =0;
        while(true){
            int stringAIndex = stringB.indexOf(stringA,startIndex);
            if(stringAIndex == -1)
                break;
            else {
                numOfOccurences++;
                startIndex = stringAIndex+lenOfStringA;
            }
        }

        System.out.println("Number of times "+stringA+" occurs in "+stringB);
        System.out.println(numOfOccurences);
    }

    static void testHowMany(){
        howMany("GAA", "ATGAACGAATTGAATC");
        howMany("AA", "ATAAAA");
        howMany("AA","SHERLOCK HOLMES");
    }

    public static void main(String[] args){
        testHowMany();
    }
}
