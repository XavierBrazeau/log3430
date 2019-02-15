package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import org.junit.jupiter.api.Test;

class TestDigraphMaDUM {
	
	static final int vertex = 13;
	static final int edges = 22;
/************************************************************************************
 * Attribute V
 ***********************************************************************************/
	@Test
	void testVConstrV() {
		// Test Constructor V & Getter V
		Digraph digraph = new Digraph(vertex);
		assertEquals(digraph.V(), vertex);
		
		//Test validateVertex
		assertThrows(IllegalArgumentException.class, () -> digraph.addEdge(vertex, vertex));
		assertThrows(IllegalArgumentException.class, () -> digraph.addEdge(-1, vertex));
		try {
			digraph.addEdge(vertex - 1, vertex - 1); //if the method fails the test also fails
		} catch (IllegalArgumentException e) {fail("Should not have thrown");}
		
		//Test toString
		String expectedPrefix = String.valueOf(vertex); 
		assertTrue(digraph.toString().startsWith(expectedPrefix));
	}
	
	@Test
	void testVConstrIn() {
		//Setup
		String url = "https://algs4.cs.princeton.edu/42digraph/tinyDG.txt";
		In in = new In(url);
		
		// Test Constructor In & Getter V
		Digraph digraph = new Digraph(in);
		assertEquals(digraph.V(), vertex);
		
		//Test toString
		String expectedPrefix = String.valueOf(vertex); 
		assertTrue(digraph.toString().startsWith(expectedPrefix));
		
		//Test validateVertex
		assertThrows(IllegalArgumentException.class, () -> digraph.addEdge(vertex, vertex));
		assertThrows(IllegalArgumentException.class, () -> digraph.addEdge(-1, vertex));
		try {
			digraph.addEdge(vertex - 1, vertex - 1); //if the method fails the test also fails
		} catch (IllegalArgumentException e) {fail("Should not have thrown");}
	}
	
	@Test
	void testVConstrCopy() {
		//Setup
		String url = "https://algs4.cs.princeton.edu/42digraph/tinyDG.txt";
		In in = new In(url);
		Digraph copy = new Digraph(in);
		
		// Test Constructor Copy & Getter V
		Digraph digraph = new Digraph(copy);
		assertEquals(digraph.V(), vertex);
		
		//Test validateVertex
		assertThrows(IllegalArgumentException.class, () -> digraph.addEdge(vertex, vertex));
		assertThrows(IllegalArgumentException.class, () -> digraph.addEdge(-1, vertex));
		try {
			digraph.addEdge(vertex - 1, vertex - 1); //if the method fails the test also fails
		} catch (IllegalArgumentException e) {fail("Should not have thrown");}
		
		//Test toString
		String expectedPrefix = String.valueOf(vertex); 
		assertTrue(digraph.toString().startsWith(expectedPrefix));
	}
	
	
/************************************************************************************
 * Attribute E
 ***********************************************************************************/
	
	@Test
	void testEConstrV() {		
		// Test Constructor V & Getter E
		Digraph digraph = new Digraph(vertex);
		assertEquals(digraph.E(), 0);
		
		//Test AddEdge
		digraph.addEdge(vertex - 1, vertex - 1); 
		assertEquals(digraph.E(), 1);
		
		//Test toString
		String expectedSub = String.valueOf(1) + " edges"; 
		assertTrue(digraph.toString().contains(expectedSub));
	}
	
	@Test
	void testEConstrIn() {		
		//Setup
		String url = "https://algs4.cs.princeton.edu/42digraph/tinyDG.txt";
		In in = new In(url);
		
		// Test Constructor In & Getter E
		Digraph digraph = new Digraph(in);
		assertEquals(digraph.E(), edges);
		
		//Test AddEdge
		digraph.addEdge(vertex - 1, vertex - 1); 
		assertEquals(digraph.E(), edges + 1);
		
		//Test toString
		String expectedSub = String.valueOf(edges + 1) + " edges"; 
		assertTrue(digraph.toString().contains(expectedSub));
	}
	
	@Test
	void testEConstrCopy() {		
		//Setup
		String url = "https://algs4.cs.princeton.edu/42digraph/tinyDG.txt";
		In in = new In(url);
		Digraph copy = new Digraph(in);
		
		// Test Constructor In & Getter E
		Digraph digraph = new Digraph(copy);
		assertEquals(digraph.E(), edges);
		
		//Test AddEdge
		digraph.addEdge(vertex - 1, vertex - 1); 
		assertEquals(digraph.E(), edges + 1);
		
		//Test toString
		String expectedSub = String.valueOf(edges + 1) + " edges"; 
		assertTrue(digraph.toString().contains(expectedSub));
	}
	
/************************************************************************************
 * Attribute Adj
 ***********************************************************************************/
	
	@Test
	void testAdjConstrVAddEdgeReverse() {		
		// Test Constructor V & Getter Adj
		Digraph digraph = new Digraph(vertex);
		for(int i = 0; i < vertex; i++)
			assertFalse(digraph.adj(i).iterator().hasNext());
		
		//Test AddEdge
		int vStart = 0;
		int vFinish = 1;
		digraph.addEdge(vStart, vFinish); 
		
		for(int i = 0; i < vertex; i++) {
			if(i == vStart) {
				Iterator<Integer> it = digraph.adj(i).iterator();
				assertTrue(it.hasNext());
				int neigh = it.next();
				assertEquals(neigh, vFinish);
				assertFalse(it.hasNext());
			} else {
				assertFalse(digraph.adj(i).iterator().hasNext());
			}
		}
		
		//Test Reverse
		Digraph reversed = digraph.reverse();
		for(int i = 0; i < vertex; i++) {
			if(i == vFinish) {
				Iterator<Integer> it = reversed.adj(i).iterator();
				assertTrue(it.hasNext());
				int neigh = it.next();
				assertEquals(neigh, vStart);
				assertFalse(it.hasNext());
			} else {
				assertFalse(reversed.adj(i).iterator().hasNext());
			}
		}
		
		//Test toString
		String expectedSub = String.valueOf(1) + " edges"; 
		assertTrue(digraph.toString().contains(expectedSub));
	}
	
}
