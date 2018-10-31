/**
 * Class for graph.
 */
public class Graph {
    /**
     * vertex of type int.
     */
    private final int vertexs;
    /**
     * edges of type int.
     */
    private int edges;
    /**
     * adj array of type Bag.
     */
    private Bag<Integer>[] adj;

    /**
     * Initializes an empty graph with {@code vertexs} vertices and 0 edges.
     * param vertexs the number of vertices
     *
     * @param  vertexs1 number of vertices
     * @throws IllegalArgumentException if {@code vertexs < 0}
     */
    public Graph(final int vertexs1) {
        if (vertexs1 < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.vertexs = vertexs1;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vertexs1];
        for (int v = 0; v < vertexs1; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * the time complexity is O(1).
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int vertexs() {
        return vertexs;
    }

    /**
     * the time complexity is O(1).
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int edges() {
        return edges;
    }

    /**
     * the time complexity is O(E).
     * Adds the undirected edge verte-w to this graph.
     *
     * @param  v one vertexs in the edge
     * @param  w the other vertexs in the edge
     * @throws IllegalArgumentException unless.
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
            edges++;
        }
        adj[v].add(w);
        adj[w].add(v);
    }


    /**
     * the time complexity is O(1).
     * Returns the vertices adjacent to vertexs {@code v}.
     *
     * @param  v the vertexs
     * @return the vertices adjacent to vertexs {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * the time complexity is O(E).
     * Determines if it has edge.
     *
     * @param      v     first vertexs.
     * @param      w     second vertexs.
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        boolean flag = false;
        for (int i = 0; i < vertexs; i++) {
            for (int k: adj[w]) {
                if (v == k) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}
