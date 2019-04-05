package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class TestIn {
	
	In in;
	final String FILE_NAME = TestIn.class.getResource("testIn").toString();
	final String FILE_NAME_ALL = TestIn.class.getResource("testInAll").toString();
	final String URL = "https://algs4.cs.princeton.edu/42digraph/tinyDG.txt";
	final double THRESHOLD = .0001;
	
	void verifyTestFile() {
		assertTrue(in.exists());
		assertFalse(in.isEmpty());
		assertTrue(in.hasNextLine());
		assertEquals("string", in.readLine());
		assertTrue(in.hasNextChar());
		assertEquals('a', in.readChar());
		assertEquals(1, in.readInt());
		assertTrue(Math.abs(1.1 -in.readFloat()) < THRESHOLD);
		assertTrue(Math.abs(1.2 -in.readFloat()) < THRESHOLD);
		assertEquals(10, in.readLong());
		assertEquals(5, in.readShort());
		assertEquals(2, in.readByte());
		assertEquals(true, in.readBoolean());
		assertEquals(false, in.readBoolean());
		assertEquals(true, in.readBoolean());
		assertEquals(false, in.readBoolean());
		assertEquals(10.1, in.readDouble());
	}
	
	@AfterEach
	void close() {
		in.close();
	}
	
	// Constructor - Standard
	@Test
	void constructorStandard() {
		in = new In();
	}
	
	// Constructor - Socket
	@Test
	void constructorSocket() throws IOException {
		Socket socket = mock(Socket.class);
		byte [] mockBytes = {10, 10, 10 ,10, 10};
        final InputStream inStream = new ByteArrayInputStream(mockBytes);
        when(socket.getInputStream()).thenReturn(inStream);
        
        in = new In(socket);
		int [] bytes = in.readAllInts();
		assertEquals(5, bytes.length);
		for (int i = 0; i < bytes.length; i++)
			assertEquals(10, bytes[i]);
	}
	
	// Constructor - Url
	@Test
	void constructorUrl() throws MalformedURLException {
		URL url = new URL(URL);
		in = new In(url);
		String out = in.readAll();
		assertTrue(out.startsWith("13"));
		assertEquals(138, out.length());
		assertEquals("", in.readAll());
	}
	
	// Constructor - File
	@Test
	void constructorFile() {
		File f = new File(FILE_NAME);
		in = new In(f);
		verifyTestFile();
	}
	
	// Constructor - String
	@Test
	void constructorString() {
		String s = FILE_NAME;
		in = new In(s);
		verifyTestFile();
	}
	
	// Constructor - Scanner
	@Test
	void constructorScanner() throws FileNotFoundException {
		File f = new File(FILE_NAME);
		Scanner sc = new Scanner(f);
		in = new In(sc);
		verifyTestFile();
	}
	
	// Read All
	@Test
	void readAll() throws FileNotFoundException {
		File f = new File(FILE_NAME_ALL);
		in = new In(f);
		String [] lines = in.readAllLines();
		
		assertEquals(5, lines.length);
		for (int i = 0; i < lines.length; i++)
			assertEquals("10", lines[i]);
		
		in = new In(f);
		String [] strings = in.readAllStrings();
		assertEquals(5, strings.length);
		for (int i = 0; i < strings.length; i++)
			assertEquals("10", strings[i]);
		
		in = new In(f);
		int [] ints = in.readAllInts();
		assertEquals(5, ints.length);
		for (int i = 0; i < ints.length; i++)
			assertEquals(10, ints[i]);
		
		in = new In(f);
		long [] longs = in.readAllLongs();
		assertEquals(5, longs.length);
		for (int i = 0; i < longs.length; i++)
			assertEquals(10, longs[i]);
		
		in = new In(f);
		double [] doubles = in.readAllDoubles();
		assertEquals(5, doubles.length);
		for (int i = 0; i < doubles.length; i++)
			assertEquals(10.0, doubles[i]);
	}
	
}
