package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.GraphGenerator;
import edu.princeton.cs.algs4.In;

import org.junit.jupiter.api.Test;

class TestDigraphMaDUM {
	
	static final int vertex = 13;
	static final int edges = 22;
	static final String localUrl = TestDigraphMaDUM.class.getResource("customGraph.txt").toString();
	static final String badUrl1 = TestDigraphMaDUM.class.getResource("badGraph.txt").toString();
	static final String badUrl2 = TestDigraphMaDUM.class.getResource("badGraph2.txt").toString();
	static final String badUrl3 = TestDigraphMaDUM.class.getResource("badGraph3.txt").toString();
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
		expectedSub = "0: 1";
		assertTrue(digraph.toString().contains(expectedSub));
		
	}
	
	@Test
	void testAdjConstrInAddEdgeReverse() {		
		//Setup
		In in = new In(localUrl);
		
		// Test Constructor In & Getter E
		Digraph digraph = new Digraph(in);
		for(int i = 0; i < vertex; i++)
			assertFalse(digraph.adj(i).iterator().hasNext());
		
		//Test AddEdge
		int vStart = 1;
		int vFinish = 0;
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
		expectedSub = "1: 0";
		assertTrue(digraph.toString().contains(expectedSub));
	}
	
	@Test
	void testAdjConstrCopyAddEdgeReverse() {		
		//Setup
		In in = new In(localUrl);
		Digraph copy = new Digraph(in);
		
		// Test Constructor In & Getter E
		Digraph digraph = new Digraph(copy);
		for(int i = 0; i < vertex; i++)
			assertFalse(digraph.adj(i).iterator().hasNext());
		
		//Test AddEdge
		int vStart = 1;
		int vFinish = 0;
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
		expectedSub = "1: 0";
		assertTrue(digraph.toString().contains(expectedSub));
	}
/************************************************************************************
 * Attribute indegree
 ***********************************************************************************/
	
	@Test
	void testIndegreeConstrVAddEdgeReverse() {		
		// Test Constructor V & Getter Adj
		Digraph digraph = new Digraph(vertex);
		for(int i = 0; i < vertex; i++)
			assertEquals(digraph.indegree(i), 0);
		
		//Test AddEdge
		int vStart = 0;
		int vFinish = 1;
		digraph.addEdge(vStart, vFinish); 
		
		for(int i = 0; i < vertex; i++) {
			if(i == vFinish) {
				assertEquals(digraph.indegree(i), 1);
			} else {
				assertEquals(digraph.indegree(i), 0);
			}
		}
		
		//Test Reverse
		Digraph reversed = digraph.reverse();
		for(int i = 0; i < vertex; i++) {
			if(i == vStart) {
				assertEquals(reversed.indegree(i), 1);
			} else {
				assertEquals(reversed.indegree(i), 0);
			}
		}
	}
	
	@Test
	void testIndegreeConstrInAddEdgeReverse() {		
		//Setup
		In in = new In(localUrl);
		
		// Test Constructor In & Getter E
		Digraph digraph = new Digraph(in);
		for(int i = 0; i < vertex; i++)
			assertEquals(digraph.indegree(i), 0);
		
		//Test AddEdge
		int vStart = 0;
		int vFinish = 1;
		digraph.addEdge(vStart, vFinish); 
		
		for(int i = 0; i < vertex; i++) {
			if(i == vFinish) {
				assertEquals(digraph.indegree(i), 1);
			} else {
				assertEquals(digraph.indegree(i), 0);
			}
		}
		
		//Test Reverse
		Digraph reversed = digraph.reverse();
		for(int i = 0; i < vertex; i++) {
			if(i == vStart) {
				assertEquals(reversed.indegree(i), 1);
			} else {
				assertEquals(reversed.indegree(i), 0);
			}
		}
	}
	
	@Test
	void testIndegreeConstrCopyAddEdgeReverse() {		
		//Setup
		In in = new In(localUrl);
		Digraph copy = new Digraph(in);
		
		// Test Constructor In & Getter E
		Digraph digraph = new Digraph(copy);
		for(int i = 0; i < vertex; i++)
			assertEquals(digraph.indegree(i), 0);
		
		//Test AddEdge
		int vStart = 0;
		int vFinish = 1;
		digraph.addEdge(vStart, vFinish); 
		
		for(int i = 0; i < vertex; i++) {
			if(i == vFinish) {
				assertEquals(digraph.indegree(i), 1);
			} else {
				assertEquals(digraph.indegree(i), 0);
			}
		}
		
		//Test Reverse
		Digraph reversed = digraph.reverse();
		for(int i = 0; i < vertex; i++) {
			if(i == vStart) {
				assertEquals(reversed.indegree(i), 1);
			} else {
				assertEquals(reversed.indegree(i), 0);
			}
		}
	}

/************************************************************************************
 * Tests for 100% coverage
 ***********************************************************************************/
	@Test
	void testInvalidConstrV() {		
		//Setup
		int invalidV = -1;
		assertThrows(IllegalArgumentException.class, () -> new Digraph(invalidV));		
	}
	
	@Test
	void testInvalidConstrIn() {		
		//Setup
		In in = new In(badUrl1);
		In in2 = new In(badUrl2);
		In in3 = new In(badUrl3);
		assertThrows(IllegalArgumentException.class, () -> new Digraph(in));
		assertThrows(IllegalArgumentException.class, () -> new Digraph(in2));
		assertThrows(IllegalArgumentException.class, () -> new Digraph(in3));
		
	}
	
	@Test
	void testOutDegree() {		
		//Setup
		In in = new In(localUrl);
		Digraph copy = new Digraph(in);
		
		// Test Constructor In & Getter E
		Digraph digraph = new Digraph(copy);
		for(int i = 0; i < vertex; i++)
			assertEquals(digraph.outdegree(i), 0);
		
		//Test AddEdge
		int vStart = 1;
		int vFinish = 0;
		digraph.addEdge(vStart, vFinish); 
		
		for(int i = 0; i < vertex; i++) {
			if(i == vStart) {
				assertEquals(digraph.outdegree(i), 1);
			} else {
				assertEquals(digraph.outdegree(i), 0);
			}
		}
	}
	
	@Test
	void testMain() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		String[] args = {localUrl};
		Digraph.main(args);
		
		System.out.flush();
		System.setOut(old);
		
		// Show what happened
		In in = new In(localUrl);
		Digraph d = new Digraph(in);
		assertEquals(d.toString().trim(), baos.toString().trim());
	}
	
}
