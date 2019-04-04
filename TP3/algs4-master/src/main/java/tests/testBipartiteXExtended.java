package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.BipartiteX;
import edu.princeton.cs.algs4.BipartiteXExtended;


import org.junit.jupiter.api.Test;

class testBipartiteXExtended {
	@Test
	void testNonBipartite() {
		Graph graph = new Graph(3);
		
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(0, 1);
		
		BipartiteXExtended bipartite = new BipartiteXExtended(graph);
		assertNotNull(bipartite.getVerticesWithAnEdgeToB(2));
		assertFalse(bipartite.isBipartite());
		
		assertNotNull(bipartite.oddCycle());
		
		
		try{
			bipartite.color(1);
		}catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	void testFalseBipartite() {
		Graph graph = new Graph(4);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(1, 3);
		
		BipartiteX bipartite = new BipartiteX(graph);
		assertTrue(bipartite.isBipartite());
		
		
	}
	
	@Test
	void testBipartite() {
		Graph graph = new Graph(2);
		
		graph.addEdge(0, 1);
		
		
		BipartiteX bipartite = new BipartiteX(graph);
		assertTrue(bipartite.isBipartite());
		
		assertNull(bipartite.oddCycle());
		
		assertNotEquals(bipartite.color(0),bipartite.color(1));
	}
	
}
