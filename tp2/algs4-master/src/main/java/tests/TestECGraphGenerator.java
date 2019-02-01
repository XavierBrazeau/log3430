package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;

class TestECGraphGenerator {

	int vertex1;
	int vertex2;
	int edges;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	//Method tested: eulerianCycle
	//Case: <{V: -1, E: e >= v }, {error}>
	@Test
	void rejectNegativeVertexLessThanEdges() {
		vertex1 = -1;
		edges = 0;
		assertThrows(Exception.class, () -> GraphGenerator.eulerianCycle(vertex1, edges));
	}
	
	//Method tested: eulerianCycle
	//Case: <{V: v > 0, E: e < V}, {Graph with eulerian cycle}>
	@Test
	void rejectLessEdgeThanPositiveVertex() {
		vertex1 = 5;
		edges = 4;
		Graph graph = GraphGenerator.eulerianCycle(vertex1, edges);
		assertAll("Graph with eulerian cycle",
				() -> assertNotNull(graph),
				() -> {for (int i = 0; i < vertex1; i++) {
					assertEquals(0,graph.degree(i)%2);
				}}
		);
	}
	
	//Method tested: bipartite
	//Case: <{V1: -1. V2: -1, E: 0}, {error}>
	void rejectNegativeVertex() {
		vertex1 = -1;
		vertex2 = -1;
		edges = 0;
	}
}
