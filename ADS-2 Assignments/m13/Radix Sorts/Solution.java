import java.util.Scanner;
import java.util.Arrays;
/**
 * Solution class.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * The time complexity is O(W*NlogN).
     * main method is useful for handling input.
     *
     * @param args The arguments.
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String[] arrayofstrings = new String[num];
        for (int i = 0; i < num; i++) {
            arrayofstrings[i] = scan.nextLine();
        }
        Quick3string qs = new Quick3string();
        qs.sort(arrayofstrings);
        System.out.println(Arrays.toString(arrayofstrings));
    }
}
