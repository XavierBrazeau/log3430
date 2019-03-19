package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Stack;

class TestStack {
	
	Stack<Integer> stack;
	static int value;

	@BeforeEach
	void setUp() throws Exception {
		stack = new Stack<Integer>();
		value = 0;
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	private void emptyStack() {
		assertEquals(stack.size(), 0);
		assertTrue(stack.isEmpty());
	}
	
	private void pushWorkedAsIntended() {
		assertEquals(stack.size(), value);
		assertEquals(stack.peek().intValue(), value);		
	}
	
	@Test
	void testPopEmptyStack() {
		emptyStack();
		assertThrows(NoSuchElementException.class, () -> stack.pop());
		emptyStack();
	}
	
	@Test
	void testPushPop() {
		Integer returnedValue;
		emptyStack();
		stack.push(++value);
		pushWorkedAsIntended();
		returnedValue = stack.pop();
		emptyStack();
		assertEquals(returnedValue.intValue(), value);
	}
	
	@Test
	void testPushPush() {
		emptyStack();
		stack.push(++value);
		pushWorkedAsIntended();
		stack.push(++value);
		pushWorkedAsIntended();
	}
	
	@Test
	void testPushPushPop() {
		Integer returnedValue;
		emptyStack();
		stack.push(++value);
		pushWorkedAsIntended();
		stack.push(++value);
		pushWorkedAsIntended();
		returnedValue = stack.pop();
		assertEquals(stack.size(), 1);
		assertEquals(returnedValue.intValue(), value);
		assertEquals(stack.peek().intValue(), value-1);
	}
}
