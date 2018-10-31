/**
 * Class for cc.
 */
public class CC {
    /**
     * marked[v] = has vertex v been marked?.
     */
    private boolean[] marked;
    /**
     * id[v] = id of connected component containing v.
     */
    private int[] id;
    /**
     * size[id] = number of vertices in given component.
     */
    private int[] size;
    /**
     * number of connected components.
     */
    private int count;

    /**
     * Computes the connected components of the undirected graph {@code G}.
     * @param g the undirected graph
     */
    public CC(final Graph g) {
        marked = new boolean[g.vertices()];
        id = new int[g.vertices()];
        size = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }
    /**
     * depth-first search for a Graph.
     * @param      g     G of type Graph.
     * @param      v     v of type int.
     * Time complexity for this method is O(N^2).
     */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }
    /**
     * Returns the component id of the connected
     * component containing vertex {@code v}.
     * @param  v the vertex
     * @return the component id of the connected
     * component containing vertex {@code v}
     * Time complexity for this method is O(1).
     */
    public int id(final int v) {
        return id[v];
    }
    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * Time complexity for this method is O(1).
     */
    public boolean connected(final int v, final int w) {
        return id(v) == id(w);
    }
}
