package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBoard {
		
	@Test
	public void testInitialize() { //comprova que les dades d'entrada s'inicialitzin.
		Board b = new BoardR(5, 6, 10);
		int nbombs = b.getNBombs();
		int rows = b.getRows();
		int cols = b.getCols();
		
		assertEquals(10, nbombs);
		assertEquals(5, rows);
		assertEquals(6, cols);
	}
	
	@Test
	public void testOpen() { //comprova el boolea de l'initialized.
		Board b = new BoardR(5, 6, 10);
		
		b.openSquare(4, 3);
		boolean initialized = b.getInitialized();
		
		assertEquals(true, initialized);			
	}	
	
	@Test
	public void testInitalized() { //comprova si s'han generat les bombes demanades en el tauler (per tant, s'ha generat el tauler correctament).
		Board b = new BoardR(5, 6, 10);
		
		b.openSquare(4, 3);
		Square[][] R = b.getBoard();
		
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
			}
		}
		
		assertEquals(10, bombs);
	}
	
	@Test
	public void testInitialized0Bombes() { //comprovació valor limit 0 bombes.
		Board b = new BoardR(5, 6, 0);
		
		b.openSquare(4, 3);
		Square[][] R = b.getBoard();
		
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
			}
		}
		
		assertEquals(0, bombs);
	}
	
	@Test
	public void testInitializedMaximBombes() { //comprovació valor limit maxim bombes.
		Board b = new BoardR(5, 6, 30);
		
		b.openSquare(4, 3);
		Square[][] R = b.getBoard();
		
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
			}
		}
		
		assertEquals(29, bombs);
	}
	
	@Test
	public void testInitializedLimitUpperBombes() { //comprovació valor limit ficant més bombes de les possibles.
		Board b = new BoardR(5, 6, 30);
		
		b.openSquare(4, 3);
		Square[][] R = b.getBoard();
		
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
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
