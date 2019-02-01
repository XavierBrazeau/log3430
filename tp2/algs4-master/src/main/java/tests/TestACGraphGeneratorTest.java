package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.GraphGenerator;

class TestACGraphGeneratorTest {

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

	//Method tested: eulerianCycle
	//Case: <{V: v < 0, E: e >= v }, {error}>
	@Test
	void rejectNegativeVertexLessThanEdges() {
		vertex1 = -1;
		edges = 0;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.eulerianCycle(vertex1, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: eulerianCycle
	//Case: <{V: v < 0, E: e < v }, {error}>
	@Test
	void rejectNegativeVertexLessEdges() {
		vertex1 = -1;
		edges = -2;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.eulerianCycle(vertex1, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: eulerianCycle
	//Case: <{V: v > 0, E: e >= V}, {Graph with eulerian cycle}>
	@Test
	void acceptPositiveVertexEqualEdeges() {
		vertex1 = 5;
		edges = 5;
		Graph graph = GraphGenerator.eulerianCycle(vertex1, edges);
		assertAll("Graph with eulerian cycle",
				() -> assertNotNull(graph, "Le graphe n'a pas ete genere"),
				() -> {for (int i = 0; i < vertex1; i++) {
					assertEquals(0,graph.degree(i)%2, "Le graphe ne contient pas de cycle eulerien");
				}}
		);
	}
	
	//Method tested: eulerianCycle
	//Case: <{V: v > 0, E: e < V}, {Graph with eulerian cycle}>
	@Test
	void acceptPositiveVertexLessEdges() {
		vertex1 = 5;
		edges = 4;
		Graph graph = GraphGenerator.eulerianCycle(vertex1, edges);
		assertAll("Graph with eulerian cycle",
				() -> assertNotNull(graph, "Le graphe n'a pas ete genere"),
				() -> {for (int i = 0; i < vertex1; i++) {
					assertEquals(0,graph.degree(i)%2, "Le graphe ne contient pas de cycle eulerien");
				}}
		);
	}
	
	//Method tested: bipartite
	//Case: <{V1: v <= 0. V2: v <= 0, E: e >= 0}, {error}>
	@Test
	void rejectNegativeVertex() {
		vertex1 = -1;
		vertex2 = -1;
		edges = 0;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: bipartite
	//Case: <{V1: v <= 0. V2: v <= 0, E: e < 0}, {error}>
	@Test
	void rejectNegativeVertexNegativeEdges() {
		vertex1 = -1;
		vertex2 = -1;
		edges = -1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: bipartite
	//Case: <{V1: v <= 0. V2: v > 0, E: e >= 0}, {error}>
	@Test
	void rejectNegativeVertex1() {
		vertex1 = -1;
		vertex2 = 1;
		edges = 0;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: bipartite
	//Case: <{V1: v <= 0. V2: v > 0, E: e < 0}, {error}>
	@Test
	void rejectNegativeVertex1NegativeEdges() {
		vertex1 = -1;
		vertex2 = 1;
		edges = -1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}	
	
	//Method tested: bipartite
	//Case: <{V1: v > 0. V2: v <= 0, E: e >= 0}, {error}>
	@Test
	void rejectNegativeVertex2() {
		vertex1 = 1;
		vertex2 = -1;
		edges = 0;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: bipartite
	//Case: <{V1: v > 0. V2: v > 0, E: e >= 0}, {error}>
	@Test
	void rejectPositiveVertexPositiveEdges() {
		vertex1 = 1;
		vertex2 = 1;
		edges = 0;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: bipartite
	//Case: <{V1: v > 0. V2: v > 0, E: e < 0}, {error}>
	@Test
	void rejectPositiveVertexNegativeEdges() {
		vertex1 = 1;
		vertex2 = 1;
		edges = -1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.bipartite(vertex1, vertex2, edges), "L'erreur n'a pas été lancée.");
	}	
	
	//Method tested: bipartite
	//Case: <{V1: v > 0. V2: v <= 0, E: e < 0}, {error}>
	@Test
	void rejectNegativeVertex2NegativeEdges() {
		vertex1 = 1;
		vertex2 = -1;
		edges = -1;
		Graph graph = GraphGenerator.bipartite(vertex1, vertex2, edges);
		boolean isBipartite = true;
		Set<Integer> spaceA, spaceB;

		//TODO
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Method tested: regular
	//Case: <{V: V*k pair && V >= k + 1, k: k < 0}, {error}>
	@Test
	void rejectNegativeDegreesWithValidVertex() {
		vertex1 = 2;
		k = -1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.regular(vertex1, k), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: regular
	//Case: <{V: V*k pair && V >= k + 1, k: k < 0}, {error}>
	@Test
	void rejectNegativeDegreesWithImpairVertex() {
		vertex1 = 1;
		k = -1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.regular(vertex1, k), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: regular
	//Case: <{V: V*k pair && V >= k + 1, k: k < 0}, {error}>
	@Test
	void rejectNegativeDegreesWithTooSmallVertex() {
		vertex1 = -2;
		k = -1;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.regular(vertex1, k), "L'erreur n'a pas été lancée.");
	}
	
	//Method tested: regular
	//Case: <{V: V*k pair && V >= k + 1, k: k < 0}, {error}>
	@Test
	void rejectPositiveDegreesWithTooSmallImpairVertex() {
		vertex1 = 1;
		k = 3;
		assertThrows(IllegalArgumentException.class, () -> GraphGenerator.regular(vertex1, k), "L'erreur n'a pas été lancée.");
	}
}
