import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
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
     * main method to drive the program.
     * @param      args  The arguments
     * Time complexity for this method is O(V + E).
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        // Taking synsets file name as input.
        String synsets = sc.nextLine();
        File file = new File("Files\\" + synsets);
        HashMap<Integer, ArrayList<String>> synsetshm =
        new HashMap<Integer, ArrayList<String>>();
        HashMap<String, ArrayList<Integer>> revsynsetshm =
        new HashMap<String, ArrayList<Integer>>();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                ArrayList<String> al = new ArrayList<String>();
                String[] inp = scan.nextLine().split(",");
                String[] tokens = inp[1].split(" ");
                for (String s : tokens) {
                    al.add(s);
                }
                synsetshm.put(Integer.parseInt(inp[0]), al);
            }
            for (Integer i : synsetshm.keySet()) {
                ArrayList<String> value = synsetshm.get(i);
                for (String str : value) {
                    if (revsynsetshm.containsKey(str)) {
                        ArrayList<Integer> it = revsynsetshm.get(str);
                        it.add(i);
                        revsynsetshm.put(str, it);
                    } else {
                        ArrayList<Integer> t = new ArrayList<Integer>();
                        t.add(i);
                        revsynsetshm.put(str, t);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Taking hypernyms file name as input.
        String hypernyms = sc.nextLine();
        // Taking the type of testcase as input.
        String type = sc.nextLine();
        File file2 = new File("Files\\" + hypernyms);
        HashMap<Integer, Integer> hypernymshm =
        new HashMap<Integer, Integer>();
        int vertices = synsetshm.size();
        Digraph d = new Digraph(vertices);
        try {
            Scanner scan1 = new Scanner(file2);
            while (scan1.hasNextLine()) {
                String[] inp = scan1.nextLine().split(",");
                for (int i = 1; i < inp.length; i++) {
                    d.addEdge(Integer.parseInt(inp[0]),
                        Integer.parseInt(inp[i]));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        switch (type) {
        case "Graph":
            DirectedCycle dc = new DirectedCycle(d);
            int cnt = 0;
            for (int i = 0; i < d.vertices(); i++) {
                if (d.outdegree(i) == 0) {
                    cnt++;
                }
            }
            if (cnt > 1) {
                System.out.println("Multiple roots");
                break;
            }
            if (dc.hasCycle()) {
                System.out.println("Cycle detected");
            } else {
                System.out.println(d);
            }
            break;
        case "Queries":
            while (sc.hasNext()) {
                String[] inp = sc.nextLine().split(" ");
                if (inp[0].equals("null")) {
                    System.out.println("IllegalArgumentException");
                    return;
                }
                SAP s = new SAP(d);
                ArrayList<Integer> a1 = revsynsetshm.get(inp[0]);
                ArrayList<Integer> a2 = revsynsetshm.get(inp[1]);
                int[] arr = s.length(a1, a2);
                ArrayList<String> res = synsetshm.get(arr[1]);
                String temp = res.get(0);
                System.out.print("distance = " + arr[0] + ", ancestor = ");
                for (int k = 0; k < res.size(); k++) {
                    if (k != res.size() - 1) {
                        System.out.print(res.get(k) + " ");
                    } else {
                        System.out.print(res.get(k));
                    }
                }
                System.out.println();
            }
            break;
        default:
            break;
        }
    }
}
