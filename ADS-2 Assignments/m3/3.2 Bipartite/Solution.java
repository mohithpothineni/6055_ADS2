import java.util.Scanner;
/**
 * Solution class.
 */
public final class Solution {
    /**
     * Constructs the object.
     * Time complexity for this method is O(N).
     */
    private Solution() {
    }
    /**
     * main method is used to hand the input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertex = scan.nextInt();
        int edges = scan.nextInt();
        Graph g = new Graph(vertex);
        for (int i = 0; i < edges; i++) {
            g.addEdge(scan.nextInt(), scan.nextInt());
        }
        Bipartite dc = new Bipartite(g);
        if (dc.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
