package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.BipartiteX;

import org.mockito.*;

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
		assertFalse(bipartite.isBipartite());
		
		
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
		
		assertThrows(IllegalArgumentException.class, () -> bipartite.color(2));
		assertThrows(IllegalArgumentException.class, () -> bipartite.color(-1));

	}
	

	@Test
	void testOddCycle() {
		Graph graph = new Graph(5);
		Integer[] expectedOddCycle = { new Integer(2), new Integer(1), new Integer(4), new Integer(2) };

		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 2);
		graph.addEdge(4, 1);
		
		
		BipartiteX bipartite = new BipartiteX(graph);
		
		assertNotNull(bipartite.oddCycle());
		Iterator<Integer> it = bipartite.oddCycle().iterator();
		int i = 0;;
		while (it.hasNext()) {
			assertEquals(it.next(), expectedOddCycle[i++]);
		}	
	}
	
	// Testing the check method when isBipartite is a flase true
	@Test
	void checkFalseBipartite() {
		Graph graph = new Graph(2);
		graph.addEdge(0, 1);
		BipartiteX bipartite = new BipartiteX(graph);
		
		BipartiteX spy = spy(bipartite);
	}
}

