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
		Square[][] R = b.getBoard();
		boolean bomb = R[2][3].getIsBomb();
		
		assertEquals(false, bomb);
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
	
	@Test
	public void testPendingSquares() { //test que comprova quantes cel·les buides queden per obrir.
		Board b = new BoardR(5, 6, 10);
		b.openSquare(4, 3);
		int pending = b.getPendingSquares();
		assertEquals(19, pending);
		
		Board c = new BoardR(1, 2, 1);
		c.openSquare(0, 1);
		pending = c.getPendingSquares();
		assertEquals(0, pending);
	}
	
	@Test
	public void testOpenSquares() { //comprova si s'obre bé la casella (retorna 0), si ja s'ha obert(retorna 1) o si hi ha una bandera(retorna 2).
		Board b = new BoardR(5, 6, 10);
		int n = b.openSquare(4, 3);
		assertEquals(0, n);
		n = b.openSquare(4, 3);
		assertEquals(1, n);
		
		b.changeFlag(3,2);
		n = b.openSquare(3, 2);
		assertEquals(2, n);
	}
	
	@Test 
	public void testIsBomb() { //comprova si es fica la bomba correctament i si al obrir la casella de la bomba retorna -1.
		Board c = new BoardR(1, 2, 1);
		Square[][] R = c.getBoard();
		c.openSquare(0, 1);
		boolean bomb = R[0][0].getIsBomb();
		assertEquals(true, bomb);	
		int n = c.openSquare(0,0);
		assertEquals(-1, n);
	}
	
	@Test
	public void testSetFlag() { //comprova si es va canviant correctament l'estat de flag de la casella.
		Board b = new BoardR(5, 6, 10);
		b.openSquare(4, 3);
		b.changeFlag(3,2);
		boolean flag = b.getIsFlagged(3,2);
		assertEquals(true, flag);
		b.changeFlag(3,2);
		flag = b.getIsFlagged(3,2);
		assertEquals(false, flag);
		
	}
}
