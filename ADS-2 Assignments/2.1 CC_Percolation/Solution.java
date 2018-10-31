import java.util.Scanner;

/**
 * Class for solution.
 */
final class Solution {

    /**
     * Constructs the object.
     */
    private Solution() {
    }

    /**
     * main method that drives the program.
     * @param      args  The arguments
     * Time complexity  O(N)
     * since
     * we are using while loop.
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        Percolation p = new Percolation(vertices);
        while (sc.hasNext()) {
            p.open(sc.nextInt() - 1, sc.nextInt() - 1);
        }
        System.out.println(p.percolates());
    }
}
