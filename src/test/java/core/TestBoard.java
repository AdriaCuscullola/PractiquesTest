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
	
	public void testInitalized() { //comprova si s'han generat les bombes demanades en el tauler (per tant, s'ha generat el tauler correctament).
		Board b = new BoardR(5, 6, 10);
		
		b.openSquare(5, 3);
		Square[][] R = b.getBoard();
		
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); i++) {
				bombs += R[i][j].getIsBomb()? 0 : 1;
			}
		}
		
		assertEquals(10, bombs);
	}
	
	public void testInitialized0Bombes() { //comprovació valor limit 0 bombes.
		Board b = new BoardR(5, 6, 0);
		
		b.openSquare(5, 3);
		Square[][] R = b.getBoard();
		
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); i++) {
				bombs += R[i][j].getIsBomb()? 0 : 1;
			}
		}
		
		assertEquals(0, bombs);
	}
	
	public void testInitializedMaximBombes() { //comprovació valor limit maxim bombes.
		Board b = new BoardR(5, 6, 29);
		
		b.openSquare(5, 3);
		Square[][] R = b.getBoard();
		
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); i++) {
				bombs += R[i][j].getIsBomb()? 0 : 1;
			}
		}
		
		assertEquals(29, bombs);
	}
	
	
	/*
	public void testOpenSquare() {
		Board b = new BoardR(5, 6, 10);
		b.openSquare(5, 3);
		b.getInitialized();
		}*/
	
}
