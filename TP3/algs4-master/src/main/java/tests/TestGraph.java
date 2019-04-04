package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class TestGraph {
	
	static final String localUrl = TestDigraphMaDUM.class.getResource("goodGraph.txt").toString();
	static final String badUrl1 = TestDigraphMaDUM.class.getResource("badGraph.txt").toString();
	static final String badUrl2 = TestDigraphMaDUM.class.getResource("badGraph2.txt").toString();
	static final String badUrl3 = TestDigraphMaDUM.class.getResource("badGraph3.txt").toString();
	
	// Should refuse negative vertex
	@Test
	void constructorVInvalidParameter() {
		assertThrows(IllegalArgumentException.class, () -> new Graph(-1));
	}
	
	// Should refuse if v < 0
	@Test
	void constructorInInvalidVertex() {
		assertThrows(IllegalArgumentException.class, () -> new Graph(new In(badUrl1)));
	}
	
	// Should refuse if e < 0
	@Test
	void constructorInInvalidEdge() {
		assertThrows(IllegalArgumentException.class, () -> new Graph(new In(badUrl2)));
	}
	
	// Should refuse if empty file
	@Test
	void constructorInEmptyFile() {
		assertThrows(IllegalArgumentException.class, () -> new Graph(new In(badUrl3)));
	}
	
	// Should throw an error if looking for a vertex too big
	@Test
	void addEdgeWithNegativeV() {
		final int v = 5;
		String expectedString = "5 vertices, 0 edges " + System.getProperty("line.separator")
								+ "0: " + System.getProperty("line.separator")
								+ "1: " + System.getProperty("line.separator")
								+ "2: " + System.getProperty("line.separator")
								+ "3: " + System.getProperty("line.separator")
								+ "4: " + System.getProperty("line.separator");
		Graph graph = new Graph(v);
		assertEquals(v, graph.V());
		
		assertEquals(expectedString, graph.toString());
		assertEquals(0, graph.E());
		for (int i = 0; i < graph.V(); i++) {
			Iterator<Integer> it = graph.adj(i).iterator(); 
			assertFalse(it.hasNext());
			assertEquals(0, graph.degree(i));			
		}
		assertThrows(IllegalArgumentException.class, () -> graph.addEdge(10, 1));
		
	}
	
	// Add valid edge
	@Test
	void constructorVAddEdgeValid() {
		final int v = 5;
		Graph graph = new Graph(v);
		String expectedString = "5 vertices, 0 edges " + System.getProperty("line.separator")
								+ "0: " + System.getProperty("line.separator")
								+ "1: " + System.getProperty("line.separator")
								+ "2: " + System.getProperty("line.separator")
								+ "3: " + System.getProperty("line.separator")
								+ "4: " + System.getProperty("line.separator");
		assertEquals(v, graph.V());
		assertEquals(expectedString, graph.toString());
		assertEquals(0, graph.E());
		for (int i = 0; i < graph.V(); i++) {
			Iterator<Integer> it = graph.adj(i).iterator(); 
			assertFalse(it.hasNext());
			assertEquals(0, graph.degree(i));
		}
		
		graph.addEdge(0, 1);
		
		assertEquals(v, graph.V());
		assertEquals(1, graph.E());
		for (int i = 0; i < graph.V(); i++) {
			Iterator<Integer> it = graph.adj(i).iterator(); 
			assertFalse((i == 0 || i == 1) ? !it.hasNext(): it.hasNext());
			assertEquals((i == 0 || i == 1) ? 1 : 0, graph.degree(i));
		}
		expectedString = "5 vertices, 1 edges " + System.getProperty("line.separator")
						+ "0: 1 " + System.getProperty("line.separator")
						+ "1: 0 " + System.getProperty("line.separator")
						+ "2: " + System.getProperty("line.separator")
						+ "3: " + System.getProperty("line.separator")
						+ "4: " + System.getProperty("line.separator");
		assertEquals(expectedString, graph.toString());
	
	}
	
	// Valid constructor with In
	@Test
	void constructorIn_ValidParameter() {
		final int v = 5;
		final int e = 4;

		Graph graph = new Graph(new In(localUrl));
		assertEquals(v, graph.V());
		assertEquals(e, graph.E());
		for (int i = 0; i < graph.V(); i++) {
			Iterator<Integer> it = graph.adj(i).iterator();
			int j = 4;
			while(it.hasNext()) {
				Integer element = it.next();
				assertEquals(element.intValue(), (i == 0) ? j-- : 0);
			};
			assertEquals((i == 0 ? 4 : 1), graph.degree(i));
		}
	}
	
	// Valid constructor with Graph
	@Test
	void constructorGraphValid() {
		final int v = 5;
		Graph graph = new Graph(v);
		graph.addEdge(0, 1);
		Graph copy = new Graph(graph);
		
		assertEquals(v, graph.V());
		assertEquals(1, graph.E());
		for (int i = 0; i < graph.V(); i++) {
			Iterator<Integer> it = graph.adj(i).iterator(); 
			assertFalse((i == 0 || i == 1) ? !it.hasNext(): it.hasNext());
			assertEquals((i == 0 || i == 1) ? 1 : 0, graph.degree(i));
		}
		String expectedString = "5 vertices, 1 edges " + System.getProperty("line.separator")
						+ "0: 1 " + System.getProperty("line.separator")
						+ "1: 0 " + System.getProperty("line.separator")
						+ "2: " + System.getProperty("line.separator")
						+ "3: " + System.getProperty("line.separator")
						+ "4: " + System.getProperty("line.separator");
		assertEquals(expectedString, graph.toString());
	}
}
