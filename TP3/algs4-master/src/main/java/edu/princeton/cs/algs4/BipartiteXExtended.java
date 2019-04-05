package edu.princeton.cs.algs4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rodrigo Morales
 * This class extends BipartiteX with new utlitary methods.
 * The objective is to avoid breaking the compatibility with
 * exisiting classes in algs4, and for further updates.
 *
 */
public class BipartiteXExtended extends BipartiteX {

	private Graph myGraph;
	public BipartiteXExtended(Graph G) {
		super(G);
		myGraph = G;
		
	}
	
		
	public List<Integer> getVerticesWithAnEdgeToB(int B) {
        int V=myGraph.V();
        List<Integer> Vertices = new ArrayList<>();
        for (int v = 0; v < V; v++) {
        	Iterator<Integer> iter = myGraph.adj(v).iterator();
        	while (iter.hasNext()){
        		int w = iter.next();
        		if (w==B)
        			Vertices.add(v);
        	}
        }
        return Vertices;
    }
	
	public List<Integer> getPartitionA() {
		List<Integer> partitionA = new ArrayList<>();
		for (int v = 0; v < myGraph.V(); v++) {
			if(color(v)) { partitionA.add(v); };
		}
		
		return partitionA;
	}

	
	   public static void main(String[] args) {
	        int V1 = Integer.parseInt(args[0]);
	        int V2 = Integer.parseInt(args[1]);
	        int E  = Integer.parseInt(args[2]);
	        int F  = Integer.parseInt(args[3]);

	        // create random bipartite graph with V1 vertices on left side,
	        // V2 vertices on right side, and E edges; then add F random edges
	        Graph G = GraphGenerator.bipartite(V1, V2, E);
	        for (int i = 0; i < F; i++) {
	            int v = StdRandom.uniform(V1 + V2);
	            int w = StdRandom.uniform(V1 + V2);
	            G.addEdge(v, w);
	        }

	        StdOut.println(G);


	        BipartiteXExtended b = new BipartiteXExtended(G);
	        if (b.isBipartite()) {
	            StdOut.println("Graph is bipartite");
	            for (int v = 0; v < G.V(); v++) {
	                StdOut.println(v + ": " + b.color(v));
	            }
	        }
	        else {
	            StdOut.print("Graph has an odd-length cycle: ");
	            for (int x : b.oddCycle()) {
	                StdOut.print(x + " ");
	            }
	            StdOut.println();
	        }
	        
	        List<Integer> VerticesWithAnEdgeToFirstVertex = b.getVerticesWithAnEdgeToB(0);
	        StdOut.println("VerticesWithAnEdgeToFirstVertex");
	        for (int v: VerticesWithAnEdgeToFirstVertex){
	        	StdOut.print(v+" ");
	        }
	        StdOut.println();
	    }
	
}