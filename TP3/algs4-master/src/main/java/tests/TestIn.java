package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.In;


public class TestIn {
	
	In in;
	
	
	// Constructor - Standard
	@Test
	void constructorStandard() {
		in = new In();
	}
	
	// Constructor - Url
	@Test
	void constructorUrl() throws MalformedURLException {
		URL url = new URL("sd");
		in = new In(url);
	}
	
	// Constructor - File
	@Test
	void constructorFile() {
		File f = new File("sd");
		in = new In(f);
	}
	
	// Constructor - String
	@Test
	void constructorString() {
		String s = "dsd";
		in = new In(s);
	}
	
	// Constructor - Scanner
	@Test
	void constructorScanner() throws FileNotFoundException {
		File f = new File("sd");
		Scanner sc = new Scanner(f);
		in = new In(sc);
	}
	
}
