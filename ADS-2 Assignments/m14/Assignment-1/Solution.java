import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * Time complexity O(N * W).
     * where N is noofwords and W is length is word.
     * Main method is used to handle the input.
     *
     * @param args  The command line arguments.
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        TST<Integer> ternary = new TST<Integer>();
        int value = 0;
        for (String s: words) {
            for (int i = 0; i < s.length(); i++) {
              ternary.put(s.substring(i), value);
              value++;
            }
        }
        Scanner scan = new Scanner(System.in);
        String prefix = scan.nextLine();
        for (String k : ternary.keysWithPrefix(prefix)) {
            System.out.println(k);
        }
    }
    /**
     * Loads words.
     *
     * @return the String array.
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
