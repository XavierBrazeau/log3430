package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;


public class TestQueue {
	
	Queue<Integer> q;
	Integer i1 = new Integer(1);
	Integer i2 = new Integer(2);
	Integer i3 = new Integer(3);
	Integer[] data = { i1, i2, i3 };
	
	// Tests for attribute First
	
	void initializationWorked(Queue<Integer> q) {
		assertNotNull(q);
		assertTrue(q.isEmpty());
		assertEquals(0, q.size());
	}
	
	void notEmptyQueueState(int pos) {
		int initialPos = pos;
		Iterator<Integer> it = q.iterator();
		while(it.hasNext()) {
			assertEquals(it.next(), data[pos++]);
		}
		assertEquals(pos - initialPos, q.size());
	}
	
	@BeforeEach
	void init() {
		q = new Queue<Integer>();
		initializationWorked(q);
	}
	
	// Peeking in an empty queue should return an error
	@Test
	void peekAtNothing() {
		assertThrows(NoSuchElementException.class, () -> q.peek());
	}
	
	// Dequeue empty queue return an error
	@Test
	void dequeueEmptyQueue() {
		assertThrows(NoSuchElementException.class, () -> q.dequeue());
	}
	
	// Enqueue Enqueue Dequeue Enqueue
	@Test
	void useAllMethods() {
		String expectedResult = "2 3 ";
		
		q.enqueue(i1);
		assertEquals(i1, q.peek());
		assertEquals(1, q.size());
		
		q.enqueue(i2);
		notEmptyQueueState(0);
		
		q.dequeue();
		notEmptyQueueState(1);
		
		q.enqueue(i3);
		notEmptyQueueState(1);
		
		assertEquals(expectedResult, q.toString());

	}
	
	// Make sure last is null when dequeue last element
	@Test
	void dequeueOnlyElement() {
		q.enqueue(i1);
		assertEquals(i1, q.peek());
		assertEquals(1, q.size());
		
		q.dequeue();
		assertThrows(NoSuchElementException.class, () -> q.peek());
	}
}
