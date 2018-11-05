/**
 * Class for kruskal mst.
 * Time complexity for this method is O(E log E).
 */
public class KruskalMST {
    /**
     * epsilon value.
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**
     * weight of MST.
     */
    private double weight;
    /**
     * edges in MST.
     */
    private Queue<Edge> mst = new Queue<Edge>();

    /**
     * Compute a minimum spanning tree (or forest)
     * of an edge-weighted graph.
     * @param g the edge-weighted graph
     */
    public KruskalMST(final EdgeWeightedGraph g) {
        // more efficient to build heap by passing array of edges
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : g.noedges()) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF uf = new UF(g.vertices());
        while (!pq.isEmpty() && mst.size() < g.vertices() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.weight();
            }
        }
        // check optimality conditions
        assert check(g);
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a
     * minimum spanning tree (or forest).
     * @return the sum of the edge weights in a
     * minimum spanning tree (or forest)
     * Time complexity for this method is O(1).
     */
    public double weight() {
        return weight;
    }
    /**
     * check optimality conditions (takes time proportional to E V lg* V).
     * @param      g     graph.
     * @return     true or false.
     */
    private boolean check(final EdgeWeightedGraph g) {

        // check total weight
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf(
                "Weight of edges does not equal weight(): %f vs. %f\n",
                              total, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(g.vertices());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : g.noedges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF(g.vertices());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : g.noedges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f
                         + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}
