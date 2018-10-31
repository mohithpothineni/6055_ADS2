/**
 * Class for graph.
 */
class Graph {
    /**
     * integer variable vertices.
     */
    private int vertices;
    /**
     * integer variable edges.
     */
    private int edges;
    /**
     * array of bag type.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     */
    Graph() {

    }
    /**
     * Constructs the object.
     * @param      vertex     integer variable.
     */
    Graph(final int vertex) {
        vertices = vertex;
        edges = 0;
        adj = (Bag<Integer>[]) new Bag[vertex];
        for (int i = 0; i < vertex; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * returns vertices.
     * Time complexity is O(1).
     * @return  vertices.
     */
    public int vert() {
        return vertices;
    }
    /**
     * returns edges.
     * Time complexity is O(1).
     * @return edges.
     */
    public int edge() {
        return edges;
    }
    /**
     * Adds an edge.
     * Time complexity is O(1)
     * @param      v     integer variable.
     * @param      w     integer variable.
     */
    public void addEdge(final int v, final int w) {
        if (!hasEdge(v, w)) {
            edges++;
            adj[v].add(w);
            adj[w].add(v);
        }
        if (v == w) {
            return;
        }
    }
    /**
     * Determines if it has edge.
     * @param      v     integer variable.
     * @param      w     integer variable.
     * Time complexity is O(v)
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        if (adj[v] == null) {
            return true;
        }
        for (int i : adj[v]) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * iterable function.
     * @param      v integer variable.
     * Time complexity is O(v)
     * @return   array.
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * matrix method.
     * Time complexity is O(1)
     * @return   array.
     */
    public Bag[] matrix() {
        return adj;
    }
    /**
     * list method.
     * Time complexity is O(1)
     * @return  array.
     */
    public Bag[] list() {
        return adj;
    }
}

