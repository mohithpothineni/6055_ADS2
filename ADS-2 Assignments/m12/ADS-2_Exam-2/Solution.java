import java.util.Scanner;
import java.util.LinkedHashSet;





public class Solution {

	public static void main(String[] args) {
		// Parallel Edges are allowed...
		// Take the Graph input here...
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph wg = new EdgeWeightedGraph(vertices);
        for (int i = 0; i <edges;i++) {
            String[] line = sc.nextLine().split(" ");
            Edge e = new Edge(Integer.parseInt(line[0]),
                Integer.parseInt(line[1]), Double.parseDouble(line[2]));
            wg.addEdge(e);
        }

		String caseToGo = sc.nextLine();
		
        switch (caseToGo) {
		case "Graph":
			System.out.println(wg);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] tokens = sc.nextLine().split(" ");
            DijkstraSP dsp = new DijkstraSP(wg, Integer.parseInt(tokens[0]));
            Double distance =  dsp.distance(Integer.parseInt(tokens[1]));
            if (distance != null) {
                System.out.println(distance);    
            } else {
                System.out.println("No Path Found.");
            }
            break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			distance = 0.00;
            tokens = sc.nextLine().split(" ");
            LinkedHashSet<Integer> roadway = new LinkedHashSet<Integer>();
            Iterable<Edge> path1;
            Iterable<Edge> path2;
            
            try {
                dsp = new DijkstraSP(wg, Integer.parseInt(tokens[0]));
                distance += dsp.distance(Integer.parseInt(tokens[1]));
                path1 = dsp.pathTo(Integer.parseInt(tokens[1]));
                dsp = new DijkstraSP(wg, Integer.parseInt(tokens[1]));
                distance += dsp.distance(Integer.parseInt(tokens[2]));
                path2 = dsp.pathTo(Integer.parseInt(tokens[2]));    
            } catch(NullPointerException e) {
                System.out.println("No Path Found.");
                break;
            }

            System.out.println(distance);

            for (Edge e: path1) {
                roadway.add(e.other(e.either()));
                roadway.add(e.either());
            }
            
            for (Edge e: path2) {
                roadway.add(e.other(e.either()));
                roadway.add(e.either());
            }

            Object[] ways = roadway.toArray(); 

            for (int i = 0; i < ways.length; i++) {
                if (i != roadway.size() -1) {
                    System.out.print(ways[i] + " ");    
                } else {
                    System.out.println(ways[i]);
                }
                
            }
            break;
		default:
			break;
		}

	}
}