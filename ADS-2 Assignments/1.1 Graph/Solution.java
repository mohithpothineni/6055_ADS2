import java.util.Scanner;
import java.util.HashMap;

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
     * main method is used for handling input.
     * displays output on console.
     *
     * @param      args  The command line arguments.
     * 
     * Time complexity is O(n*v*e).
     * depends on no of inputs.
     * no of vertices and edges
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        int vertex = Integer.parseInt(scan.nextLine());
        int edge = Integer.parseInt(scan.nextLine());

        Graph g = new Graph(vertex);
        String[] input = scan.nextLine().split(",");
        HashMap<Integer, String> map = new HashMap<Integer, String>();

        for (int i = 0; i < vertex; i++) {
            map.put(i, input[i]);
        }

        for (int j = 0; j < edge; j++) {
            String[] edgeinput = scan.nextLine().split(" ");
            g.addEdge(Integer.parseInt(
                edgeinput[0]), Integer.parseInt(edgeinput[1]));
        }

        System.out.println(g.vertexs() + " vertices, " + g.edges() + " edges");
        if (vertex <= 1) {
            System.out.println("No edges");
            return;
        }

        if (type.equals("List")) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < vertex; i++) {
                s.append(map.get(i) + ": ");
                for (int j : g.adj(i)) {
                s.append(map.get(j) + " ");
            }
            s.append("\n");
        }
        System.out.println(s.toString().trim());
        } else {
            for (int i = 0; i < vertex; i++) {
                for (int j = 0; j < vertex; j++) {
                    if (g.hasEdge(i, j)) {
                        System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
                }
            System.out.println();
            }
        }
    }
}


