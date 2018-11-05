/**
 * Class for breadth first directed paths.
 */
public class BreadthFirstDirectedPaths {
    /**
     * infinity value of type int.
     */
    private static final int INFINITY = Integer.MAX_VALUE;
    /**
     * marked[v] = is there an s->v path?
     */
    private boolean[] marked;
    /**
     * edgeTo[v] = last edge on shortest s->v path.
     */
    private int[] edgeTo;
    /**
     * distTo[v] = length of shortest s->v path.
     */
    private int[] distTo;
    /**
     * Computes the shortest path from {@code s} and
     * every other vertex in graph {@code G}.
     * @param g the digraph
     * @param s the source vertex
     */
    public BreadthFirstDirectedPaths(final Digraph g, final int s) {
        marked = new boolean[g.vertices()];
        distTo = new int[g.vertices()];
        edgeTo = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = INFINITY;
        }
        bfs(g, s);
    }
    /**
     * Computes the shortest path from any one of the
     * source vertices in {@code sources}
     * to every other vertex in graph {@code G}.
     * @param g the digraph
     * @param sources the source vertices
     * @throws IllegalArgumentException unless each vertex {@code v} in
     *         {@code sources} satisfies {@code 0 <= v < V}
     */
    public BreadthFirstDirectedPaths(final Digraph g,
                                     final Iterable<Integer> sources) {
        marked = new boolean[g.vertices()];
        distTo = new int[g.vertices()];
        edgeTo = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertices(sources);
        bfs(g, sources);
    }
    /**
     * bfs from single source.
     * @param      g     Digraph g
     * @param      s     int.
     * Time complexity for this method is O(V + E)
     * where V is no of vertices and E is no of edges.
     */
    private void bfs(final Digraph g, final int s) {
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }
    /**
     * bfs from multiple roots.
     * @param      g        Digraph g.
     * @param      sources  The sources
     * Time complexity for this method is O(V + E)
     * where V is no of vertices and E is no of edges.
     */
    private void bfs(final Digraph g, final Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }
    /**
     * Is there a directed path from the source
     * {@code s} (or sources) to vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a directed path, {@code false} otherwise
     * Time complexity for this method is O(1).
     */
    public boolean hasPathTo(final int v) {
        return marked[v];
    }
    /**
     * Returns the number of edges in a shortest path from the source {@code s}
     * (or sources) to vertex {@code v}?
     * @param v the vertex
     * @return the number of edges in a shortest path
     * Time complexity for this method is O(1).
     */
    public int distTo(final int v) {
        return distTo[v];
    }
    /**
     * Returns a shortest path from {@code s} (or sources) to {@code v}, or
     * {@code null} if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     * Time complexity for this method is O(N).
     */
    public Iterable<Integer> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }
    /**
     * throw an IllegalArgumentException unless {@code 0 <= v < V}.
     * @param      v     int.
     * Time complexity for this method is O(1).
     */
    private void validateVertex(final int v) {
        int v1 = marked.length;
        if (v < 0 || v >= v1) {
            throw new IllegalArgumentException("vertex " + v
                + " is not between 0 and " + (v1 - 1));
        }
    }
    /**
     * throw an IllegalArgumentException unless {@code 0 <= v < V}.
     * @param      vertices  The vertices
     * Time complexity for this method is O(V) where V is no of
     * vertices.
     */
    private void validateVertices(final Iterable<Integer> vertices) {
        int v1 = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= v1) {
                throw new IllegalArgumentException("vertex " + v
                    + " is not between 0 and " + (v1 - 1));
            }
        }
    }
}
