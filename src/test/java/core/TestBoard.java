package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBoard {
		
	@Test
	public void testInitialize() {
		Board b = new BoardR(5, 6, 10);
		int nbombs = b.getNBombs();
		int rows = b.getRows();
		int cols = b.getCols();
		
		assertEquals(10, nbombs);
		assertEquals(5, rows);
		assertEquals(6, cols);
	}
	
	public void testOpen() {
		Board b = new BoardR(5, 6, 10);
		
		b.openSquare(5, 3);
		boolean initialized = b.getInitialized();
		
		assertEquals(true, initialized);	
		
	}	
}
