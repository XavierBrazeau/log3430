package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.BipartiteX;
import edu.princeton.cs.algs4.BipartiteXExtended;


import org.junit.jupiter.api.Test;

class testBipartiteXExtended {
	
	// Test a non bipartite graph
	@Test
	void testNonBipartite() {
		Graph graph = new Graph(4);
		
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(0, 1);
		
		BipartiteXExtended bipartite = new BipartiteXExtended(graph);
		List<Integer> test = bipartite.getVerticesWithAnEdgeToB(2);
		assertTrue(test.get(0).equals(0));
		assertTrue(test.get(1).equals(1));
		
		List<Integer> test2 = bipartite.getVerticesWithAnEdgeToB(4);
		assertTrue(test2.isEmpty());
		
		assertFalse(bipartite.isBipartite());
		assertNotNull(bipartite.oddCycle());
		assertThrows(UnsupportedOperationException.class, () -> bipartite.color(1));
		assertTrue(bipartite.getPartitionBlack().isEmpty());
	}
	
	// Test a false bipartite
	@Test
	void testFalseBipartite() {
		Graph graph = new Graph(4);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(1, 3);
		
		BipartiteXExtended bipartite = new BipartiteXExtended(graph);
		assertFalse(bipartite.isBipartite());
		assertTrue(bipartite.getPartitionBlack().isEmpty());
		assertNotNull(bipartite.oddCycle());
		
	}
	
	// Test a bipartite graph
	@Test
	void testBipartite() {
		Graph graph = new Graph(2);
		
		graph.addEdge(0, 1);
		
		
		BipartiteXExtended bipartite = new BipartiteXExtended(graph);
		
		assertTrue(bipartite.isBipartite());
		assertNull(bipartite.oddCycle());	
		assertNotEquals(bipartite.color(0), bipartite.color(1));
		assertEquals(bipartite.getPartitionBlack().size(), 1);
		assertTrue(bipartite.getPartitionBlack().get(0).equals(1));
		
	}
	
}
