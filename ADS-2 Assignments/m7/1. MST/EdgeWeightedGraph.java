/**
 * Class for edge weighted graph.
 */
public class EdgeWeightedGraph {
    /**
     * newline of type String.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * no of vertices.
     */
    private final int v;
    /**
     * no of edges.
     */
    private int e;
    /**
     * adj of type Bag array.
     */
    private Bag<Edge>[] adj;
    /**
     * Initializes an empty edge-weighted graph with
     * {@code V} vertices and 0 edges.
     *
     * @param  v1 the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedGraph(final int v1) {
        if (v1 < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.v = v1;
        this.e = 0;
        adj = (Bag<Edge>[]) new Bag[v1];
        for (int i = 0; i < v1; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**
     * Initializes a new edge-weighted graph that is a deep copy of {@code G}.
     *
     * @param  gp the edge-weighted graph to copy
     */
    public EdgeWeightedGraph(final EdgeWeightedGraph gp) {
        this(gp.vertices());
        this.e = gp.edges();
        for (int v1 = 0; v1 < gp.vertices(); v1++) {
            // reverse so that adjacency list is in same order as original
            Stack<Edge> reverse = new Stack<Edge>();
            for (Edge e1 : gp.adj[v1]) {
                reverse.push(e1);
            }
            for (Edge e2 : reverse) {
                adj[v1].add(e2);
            }
        }
    }


    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     * Time complexity for this method is O(1).
     */
    public int vertices() {
        return v;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     * Time complexity for this method is O(1).
     */
    public int edges() {
        return e;
    }
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    /**
    * validate vertex.
    * @param v1 the vertex.
    *
    **/
    private void validateVertex(final int v1) {
        if (v1 < 0 || v1 >= v) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (v - 1));
        }
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param  e1 the edge
     * @throws IllegalArgumentException unless both
     * endpoints are between {@code 0} and {@code V-1}
     * Time complexity for this method is O(1).
     */
    public void addEdge(final Edge e1) {
        int v1 = e1.either();
        int w = e1.other(v1);
        validateVertex(v1);
        validateVertex(w);
        adj[v1].add(e1);
        adj[w].add(e1);
        e++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v1 the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(final int v1) {
        validateVertex(v1);
        return adj[v1];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v1 the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * Time complexity for this method is O(1).
     */
    public int degree(final int v1) {
        validateVertex(v1);
        return adj[v1].size();
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph,
     * use foreach notation:
     * {@code for (Edge e : G.edges())}.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> noedges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int i = 0; i < vertices(); i++) {
            int selfLoops = 0;
            for (Edge ed : adj(i)) {
                if (ed.other(i) > i) {
                    list.add(ed);
                } else if (ed.other(i) == i) {  // add only one
                    // copy of each self loop
                    //(self loops will be consecutive)
                    if (selfLoops % 2 == 0) {
                        list.add(ed);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }
}
