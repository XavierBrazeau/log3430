package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;

class TestECGraphGenerator {

	int vertex1;
	int vertex2;
	int edges;
	int k;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	void DFSUtil(int v, boolean visited[], Graph graph) 
	{ 
	    // Mark the current node as visited and print it 
	    visited[v] = true; 
	  
	    // Recur for all the vertices adjacent to this vertex
	   Iterable<Integer> neigh = graph.adj(v);
	   Iterator<Integer> neighIt = neigh.iterator();
	while (neighIt.hasNext()) {
		  int currentNeigh = neighIt.next();
		   if (!visited[currentNeigh]) 
	            DFSUtil(currentNeigh, visited, graph); 
	    }
	} 
	
	boolean isConnected(Graph graph) {
		
		if(graph.E()==0){return true;}
		
		boolean[] visited = new boolean[graph.V()];
		int i;
		
		for (i = 0; i < graph.V(); i++) 
	        visited[i] = false;
		
		for (i = 0; i < graph.V(); i++) 
	        if (graph.degree(i) != 0) 
	            break;
		
		DFSUtil(i, visited, graph);
		
		for (i = 0; i < graph.V(); i++) 
		       if (visited[i] == false && graph.degree(i) > 0) 
		            return false;
		
		return true;
	}

	boolean isBipartite(Graph graph, int src) 
	    { 
	        // Create a color array to store  
	        // colors assigned to all veritces. 
	        // Vertex number is used as index  
	        // in this array. The value '-1' 
	        // of colorArr[i] is used to indicate  
	        // that no color is assigned 
	        // to vertex 'i'. The value 1 is  
	        // used to indicate first color 
	        // is assigned and value 0 indicates  
	        // second color is assigned. 
		 
		 int G[][] = new int[graph.V()][graph.V()];
		 for (int i = 0; i < graph.V(); i++)
		 {
			 Iterator<Integer> gIt = graph.adj(i).iterator();
			 while(gIt.hasNext())
			 {
				 int it = gIt.next();
				 G[i][it] = 1;
			 }
		 }
		 
	        int colorArr[] = new int[graph.V()]; 
	        for (int i=0; i<graph.V(); ++i) 
	            colorArr[i] = -1; 
	  
	        // Assign first color to source 
	        colorArr[src] = 1; 
	  
	        // Create a queue (FIFO) of vertex numbers  
	        // and enqueue source vertex for BFS traversal 
	        LinkedList<Integer>q = new LinkedList<Integer>(); 
	        q.add(src); 
	  
	        // Run while there are vertices in queue (Similar to BFS) 
	        while (q.size() != 0) 
	        { 
	            // Dequeue a vertex from queue 
	            int u = q.poll(); 
	  
	            // Return false if there is a self-loop  
	            if (G[u][u] == 1) 
	                return false;  
	  
	            // Find all non-colored adjacent vertices 
	            for (int v=0; v< graph.V(); ++v) 
	            { 
	                // An edge from u to v exists  
	                // and destination v is not colored 
	                if (G[u][v]==1 && colorArr[v]==-1) 
	                { 
	                    // Assign alternate color to this adjacent v of u 
	                    colorArr[v] = 1-colorArr[u]; 
	                    q.add(v); 
	                } 
	  
	                // An edge from u to v exists and destination 
	                //  v is colored with same color as u 
	                else if (G[u][v]==1 && colorArr[v]==colorArr[u]) 
	                    return false; 
	            } 
	        } 
	        // If we reach here, then all adjacent vertices can 
	        // be colored with alternate color 
	        return true; 
	} 
	
	boolean isRegular(Graph graph)
	 {
		 int nbNeigh = 0;
		 Iterator<Integer> it = graph.adj(0).iterator();
		 
		 while(it.hasNext())
		 {
			 nbNeigh++;
			 it.next();
		 }
		 
		 for(int i = 1; i < graph.V(); i++)
		 {
			 int currentNbNeigh = 0;
			 it = graph.adj(i).iterator();
			 
			 while(it.hasNext())
			 {
				 currentNbNeigh++;
				 it.next();
			 }
			 
			 if(currentNbNeigh != nbNeigh)
				 return false;
		 }
		 
		 return true;
	 }
	 
	//Method tested: eulerianCycle
	//Case: <{V: v <= 0}, {error}>
	@Test
	void rejectNoVertexEulerian() {
		vertex1 = 0;
		edges = 1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.eulerianCycle(vertex1, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: eulerianCycle
	//Case: <{E: e = 0}, {error}>
	@Test
	void rejectNoEdgesEulerian() {
		vertex1 = 1;
		edges = 0;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.eulerianCycle(vertex1, edges), "L'erreur n'a pas été lancée.");
	}
	
	
	//Method tested: eulerianCycle
	//Case: <{V: v = 1. E: e = v = 1}, {Graph with eulerian cycle}>
	@Test
	void acceptSingleVertexSingleEdgeEulerian() {
		vertex1 = 1;
		edges = 1;
		Graph graph = GraphGenerator.eulerianCycle(vertex1, edges);
		assertAll("Graph with eulerian cycle",
				() -> assertNotNull(graph, "Le graphe n'a pas ete genere"),
				() -> {for (int i = 0; i < vertex1; i++) {
					assertEquals(0,graph.degree(i)%2, "Le graphe ne contient pas de cycle eulerien");
				}},
				() -> assertTrue(isConnected(graph))
		);
	}	
	
	//Method tested: eulerianCycle
	//Case: <{V > 1, E: > 1}, {Graph with eulerian cycle}>
	@Test
	void acceptPositiveVerticesPositiveEdgesEulerian() {
		vertex1 = 2;
		edges = 3;
		Graph graph = GraphGenerator.eulerianCycle(vertex1, edges);
		assertAll("Graph with eulerian cycle",
				() -> assertNotNull(graph, "Le graphe n'a pas ete genere"),
				() -> {for (int i = 0; i < vertex1; i++) {
					assertEquals(0,graph.degree(i)%2, "Le graphe ne contient pas de cycle eulerien");
				}},
				() -> assertTrue(isConnected(graph))
		);
	}
	
/******************************************************************************************************************************
 * Bipartite
 *****************************************************************************************************************************/
	//Method tested: bipartite
	//Case: <{V1: v < 0}, {error}>
	@Test
	void rejectNegativeVertex1Bipartite() {
		vertex1 = -1;
		vertex2 = 1;
		edges = 0;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: bipartite
	//Case: <{V2: v < 0}, {error}>
	@Test
	void rejectNegativeVertex2Bipartite() {
		vertex1 = 1;
		vertex2 = -1;
		edges = 0;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: bipartite
	//Case: <{E: e < 0}, {error}>
	@Test
	void rejectNegativeEdgeBipartite() {
		vertex1 = 0;
		vertex2 = 0;
		edges = -1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: bipartite
	//Case: <{V1: v = 1, V2: v = 1, E: e = 0}, {Bipartite graph}>
		@Test
	void rejectNoVerticesEdgesBipartite() {
		vertex1 = 0;
		vertex2 = 0;
		edges = 2;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
		
	}
	
	//Method tested: bipartite
	//Case: <{V1: v = 0, V2: v = 0, E: e = 0}, {Bipartite graph}>
		@Test
	void acceptNoVertexNoEdgesBipartite() {
			vertex1 = 0;
			vertex2 = 0;
			edges = 0;
			Graph graph = GraphGenerator.bipartite(vertex1, vertex2, edges);
			assertNotNull(graph, "Le graphe n'a pas ete genere");
		}
		
	
	//Method tested: bipartite
	//Case: <{V1: v > 0, V2: v > 0, E: e > 0}, {Bipartite graph}>
	@Test
	void acceptMultipleVertexMultipleEdgesBipartite() {
			vertex1 = 2;
			vertex2 = 2;
			edges = 2;
			Graph graph = GraphGenerator.bipartite(vertex1, vertex2, edges);
			assertAll("Graph bipartite",
					() -> assertNotNull(graph, "Le graphe n'a pas ete genere"),
					() -> assertTrue(isBipartite(graph,0))
			);
		}
		
/******************************************************************************************************************************
 * Regular
 *****************************************************************************************************************************/
	
	//Method tested: regular
	//Case: <{k: k < 0}, {error}>
	@Test
	void rejectNegativeDegreeRegular() {
		vertex1 = 2;
		k = -1;
		assertThrows(Exception.class, () -> GraphGenerator.regular(vertex1, k), "L'erreur n'a pas été lancée.");
	}
	
	
	//Method tested: regular
	//Case: <{V: V < 0}, {error}>
	@Test
	void rejectNegativeVertexRegular() {
		vertex1 = -2;
		k = 0;
		assertThrows(Exception.class, () -> GraphGenerator.regular(vertex1, k), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: regular
	//Case: <{V: v > 0, k:  V*k pair}, {regular graph}>
	@Test
	void acceptZeroDegreeVKPairHigherRegular() {
		vertex1 = 1;
		k = 0;
		Graph graph = GraphGenerator.regular(vertex1, k);
		assertAll("Graph regular",
				() -> assertNotNull(graph, "Le graphe n'a pas ete genere"),
				() -> assertTrue(isRegular(graph))
		);
	}
	
	//Method tested: regular
	//Case: <{V: v = 0, k:  k > 0}, {error}>
	@Test
	void acceptPositiveDegreeVKPAirEqualRegular() {
		vertex1 = 0;
		k = 2;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.regular(vertex1, k), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: regular
	//Case: <{V: v > 0, k: V*k pair}, {regular graph}>
	@Test
	void rejectPositiveDegreesWithTooSmallVertexVKPairRegular() {
		vertex1 = 2;
		k = 2;
		Graph graph = GraphGenerator.regular(vertex1, k);
		assertAll("Graph regular",
				() -> assertNotNull(graph, "Le graphe n'a pas ete genere"),
				() -> assertTrue(isRegular(graph))
		);
	}
	
	//Method tested: regular
	//Case: <{k: V*k impair}, {error}>
	@Test
	void acceptZeroDegreeVKImpairHigherRegular() {
		vertex1 = 3;
		k = 1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.regular(vertex1, k), "L'erreur n'a pas été lancée.");
	}

}