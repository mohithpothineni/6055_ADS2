import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for page rank.
 */
class PageRank {

    /**
     * graph to be traversed.
     */
    private Digraph digraph;

    /**
     * array of rankings.
     */
    private double[] prs;

    /**
     * it stores incomings of a vertex.
     */
    private Iterable<Integer>[] it;

    /**
     * Constructs the object.
     *
     * @param      graph  The graph
     */
    PageRank(final Digraph graph) {
        this.digraph = graph;

        this.prs = new double[this.digraph.V()];
        for (int k = 0; k < this.prs.length; k++) {
            this.prs[k] = (double) 1 / this.prs.length;
        }
    }

    /**
     * helper2 segregates incomigs.
     */
    void helper2() {
        this.it = new Iterable[this.digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            //System.out.println(helper1(i));
            it[i] = helper1(i);
        }

    }

    /**
     * Gets the pr. of vertex.
     *
     * @param      v     vertex.
     *
     * @return     The pr.
     */
    double getPR(final int v) {
        helper2();
        final int repetetions = 1000;
        for (int i = 0; i < repetetions; i++) {
            double[] result = new double[digraph.V()];
            for (int j = 0; j < digraph.V(); j++) {
                double res = 0.00000;
                for (int inc : this.it[j]) {
                    res += (double) this.prs[inc] / this.digraph.outdegree(inc);
                    //System.out.println(result);
                }
                result[j] = (double) res;
            }
            for (int n = 0; n < prs.length; n++) {
                prs[n] = (double) result[n];
            }

            //System.out.println(Arrays.toString(prs));
        }
        return this.prs[v];
    }

    /**
     * helper1 gives incomings to vertex.
     *
     * @param      v     vertex
     *
     * @return     incomings
     */
    Iterable<Integer> helper1(final int v) {
        //find the incoming vertices values
        ArrayList<Integer> adjc = new ArrayList<Integer>();
        for (int m = 0; m < this.digraph.V(); m++) {
            Iterable<Integer> adj = null;
            if (m != v) {
                adj = this.digraph.adj(m);
                for (int inc : adj) {
                    if (inc == v) {
                        adjc.add(m);
                    }
                }


            }

        }

        return adjc;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {

        String result = "";
        for (int i = 0; i < digraph.V(); i++) {
            double tmp = (double) this.getPR(i);
            result += i + " - " + tmp + "\n";
        }
        return result;
    }
}

/**
 * Class for web search.
 */
class WebSearch {

}

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
     * main method.
     *
     * @param      args  The arguments
     * time complexity O(n*(E+V))
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        // read the first line of the input to get the number of vertices
        int noofvertices = Integer.parseInt(scan.nextLine());
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        Digraph digraph = new Digraph(noofvertices);

        for (int i = 0; i < noofvertices; i++) {
            String[] connections = scan.nextLine().split(" ");
            int vert = Integer.parseInt(connections[0]);
            for (int j = 1; j < connections.length; j++) {
                digraph.addEdge(vert, Integer.parseInt(connections[j]));
            }
        }

        System.out.println(digraph);

        // Create page rank object and pass the graph object to the constructor
        PageRank pagerank = new PageRank(digraph);
        // print the page rank object
        System.out.print(pagerank);

        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky

    }
}


