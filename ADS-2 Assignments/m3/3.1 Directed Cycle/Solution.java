import java.util.Scanner;
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
     * main method is used to hand the input.
     *
     * @param      args  The arguments
     * Time complexity for this method is O(N).
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertex = scan.nextInt();
        int edges = scan.nextInt();
        Digraph d = new Digraph(vertex);
        for (int i = 0; i < edges; i++) {
            d.addEdge(scan.nextInt(), scan.nextInt());
        }
        DirectedCycle dc = new DirectedCycle(d);
        if (!dc.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}
