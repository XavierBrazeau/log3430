package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.BipartiteX;


import org.junit.jupiter.api.Test;

class testBipartiteX {
	
	@Test
	void testNonBipartite() {
		Graph graph = new Graph(3);
		
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(0, 1);
		
		BipartiteX bipartite = new BipartiteX(graph);
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
	
	@Test
	void testInvalidVertex() {
		Graph graph = new Graph(2);
		
		graph.addEdge(0, 1);
		
		
		BipartiteX bipartite = new BipartiteX(graph);
		
		try {
			bipartite.color(2);
		}catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		
		try {
			bipartite.color(-1);
		}catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		
		
	}
	

	@Test
	void testOddCycle() {
		Graph graph = new Graph(5);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 2);
		graph.addEdge(4, 1);
		
		
		BipartiteX bipartite = new BipartiteX(graph);
		
		
		
		
	}

}
