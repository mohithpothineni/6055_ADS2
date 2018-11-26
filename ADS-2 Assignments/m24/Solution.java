import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Class for solution.
 */
class Solution {
    //properies
    String txt = null;
    String pat = new String("It is a far, far better thing that I do, than I have ever done");
    static String[] algoNames = {"BoyerMoore", "Brute", "KMP", "RabinKarp"};

    /**
     * Constructs the object.
     */
    Solution() throws IOException {
        this.txt = new String(Files.readAllBytes(Paths.get("tale.txt")));
    }

    /**
     * main method.
     *
     * @param      args         The commandline arguments
     *
     * @throws     IOException  file not found error
     */
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            Solution solver = new Solution();
            System.out.println(algoNames[i]);
            SearchAlgo algo = null;
            long startTime = System.currentTimeMillis();
            if (i == 0) {
                algo = new BoyerMoore(solver.pat);
            } else if (i == 1) {
                algo = new Brute(solver.pat);;
            } else if (i == 2) {
                algo = new KMP(solver.pat);;
            } else {
                algo = new RabinKarp(solver.pat);
            }
            int offset = algo.search(solver.txt);
            if (offset != solver.txt.length()) {
                System.out.println("Pattern Found!!!");
                for (int j = offset; j < offset + solver.pat.length(); j++) {
                    System.out.print(solver.txt.charAt(j));
                }
                long endTime = System.currentTimeMillis();
                System.out.println("\nIt took " + (endTime - startTime) + " milliseconds");
                System.out.println();
            }

        }
    }

}
