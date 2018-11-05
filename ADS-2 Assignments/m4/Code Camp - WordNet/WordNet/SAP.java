import java.util.ArrayList;
/**
 * Class for sap.
 */
public class SAP {
    /**
     * dg of the type Digraph.
     */
    private Digraph dg;
    /**
     * Constructs the object.
     * @param      g     Digraph.
     */
    public SAP(final Digraph g) {
        dg = g;
    }
    /**
     * This finds out the shortest ancestral path between two vertices.
     * @param      v     ArrayList.
     * @param      w     ArrayList.
     * @return     integer array.
     * Time complexity for this method is O(v*w*V) where v is length of
     * array list and w is length of array list and V is no of vertices.
     */
    public int[] length(final ArrayList<Integer> v,
        final ArrayList<Integer> w) {
        int min = dg.vertices();
        int temp = 0;
        for (int i = 0; i < v.size(); i++) {
            for (int j = 0; j < w.size(); j++) {
                BreadthFirstDirectedPaths b1 =
                new BreadthFirstDirectedPaths(dg, v.get(i));
                BreadthFirstDirectedPaths b2 =
                new BreadthFirstDirectedPaths(dg, w.get(j));
                for (int k = 0; k < dg.vertices(); k++) {
                    if (b1.hasPathTo(k) && b2.hasPathTo(k)) {
                        int sum = b1.distTo(k) + b2.distTo(k);
                        if (sum < min) {
                            min = sum;
                            temp = k;
                        }
                    }
                }
            }
        }
        int[] res = {min, temp};
        return res;
    }
}

