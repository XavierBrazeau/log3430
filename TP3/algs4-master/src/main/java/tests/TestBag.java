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
import edu.princeton.cs.algs4.Bag;


public class TestBag {
	
	Bag<Integer> b;
	Integer i1 = new Integer(1);
	Integer i2 = new Integer(2);
	Integer i3 = new Integer(3);
	Integer[] data = { i1, i2, i3 };
	
	// Tests for attribute First
	
	void initializationWorked(Bag<Integer> b) {
		assertNotNull(b);
		assertTrue(b.isEmpty());
		assertEquals(0, b.size());
		assertFalse(b.iterator().hasNext());
		assertThrows(NoSuchElementException.class, () -> b.iterator().next());
	}
	
	void verifyBag(int current, int expectedSize) {
		assertFalse(b.isEmpty());
		assertTrue(b.iterator().hasNext());
		assertTrue(current == b.iterator().next());
		assertEquals(expectedSize, b.size());
	}
	
	@BeforeEach
	void init() {
		b = new Bag<Integer>();
		initializationWorked(b);
	}
	
	// All methods
	@Test
	void allMethods() {
		int expectedSize = 0;
		
		b.add(i1);
		verifyBag(i1, ++expectedSize);
		
		b.add(i2);
		verifyBag(i2, ++expectedSize);
		
		b.add(i3);
		verifyBag(i3, ++expectedSize);
		
		assertThrows(UnsupportedOperationException.class, () -> b.iterator().remove());

	}
	
}
