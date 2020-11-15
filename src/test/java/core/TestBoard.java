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
		Board b = new BoardR(5, 6, 29);
		
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
	public void testInitializedLimitBombes() { //comprovació valor limit ficant més bombes de les possibles.
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
		
		Board c = new BoardR(5, 6, -1);
		c.openSquare(4, 3);
		Square[][] T = c.getBoard();
		bombs = 0;
		for(int i = 0; i < c.getRows(); i++) {
			for (int j = 0; j < c.getCols(); j++) {
				bombs += T[i][j].getIsBomb()? 1 : 0;
			}
		}		
		assertEquals(0, bombs);
	}	
	
	/*Tauler amb seed: = 55555
	oxoooo |0 
	oooooo |1
	oxoooo |2
	ooxooo |3
	ooooox |4
	------
	012345
	*/
	@Test
	public void testPendingSquares() { //test que comprova quantes cel·les buides queden per obrir.
		Board b = new BoardR(5, 6, 4);
		int pending = b.getPendingSquares();
		assertEquals(26, pending);
		b.initialize(4, 3, 55555);
		b.openSquare(4, 3);
		pending = b.getPendingSquares();
		assertEquals(25, pending);
		
		b.openSquare(0, 5);
		pending = b.getPendingSquares();
		assertEquals(10, pending);
		
		
		Board c = new BoardR(1, 2, 1);
		c.openSquare(0, 1);
		pending = c.getPendingSquares();
		assertEquals(0, pending);
	}
	
	@Test
	public void testOpenSquares() { //comprova si s'obre bé la casella (retorna 0), si ja s'ha obert(retorna 1) o si hi ha una bandera(retorna 2).
		Board b = new BoardR(5, 6, 10);
		boolean isOpen = b.getSquareIsOpen(4,3);
		assertEquals(false, isOpen);
		b.openSquare(4, 3);
		isOpen = b.getSquareIsOpen(4,3);
		assertEquals(true, isOpen);
	}
	
	@Test 
	public void testIsBomb() { //comprova si es fica la bomba correctament i si al obrir la casella de la bomba retorna bomba.
		Board c = new BoardR(1, 2, 1);
		Square[][] R = c.getBoard();
		boolean bomb = R[0][0].getIsBomb();
		assertEquals(false, bomb);	
		c.openSquare(0, 1);
		bomb = R[0][0].getIsBomb();
		assertEquals(true, bomb);	
	}
	
	@Test
	public void testSetFlag() { //comprova si es va canviant correctament l'estat de flag de la casella.
		Board b = new BoardR(5, 6, 10);
		b.initialize(4, 3, 55555);
		Square[][] R = b.getBoard();
		b.openSquare(4, 3);
		boolean isFlag = R[3][2].isFlagged();
		assertEquals(false, isFlag);
		b.changeFlag(3,2);
		isFlag = R[3][2].isFlagged();
		assertEquals(true, isFlag);
		b.changeFlag(3,2);
		isFlag = R[3][2].isFlagged();
		assertEquals(false, isFlag);
	}
	
	@Test
	public void testGetStatus() { //comprova si el getStatus retorna el ENUM indicat.
		Board b = new BoardR(5, 6, 10);
		b.initialize(4, 3, 55555);
		Square[][] R = b.getBoard();
		
		b.openSquare(4, 3);
		SquareStatus real = b.getStatus(4,3);
		SquareStatus aux = SquareStatus.VALUE;
		assertEquals(aux, real);
		
		SquareStatus real2 = b.getStatus(3,2);
		SquareStatus aux2 = SquareStatus.NOT_OPEN;
		assertEquals(aux2, real2);
		
		R[3][2].setBomb();
		R[3][2].open();
		SquareStatus real3 = b.getStatus(3,2);
		SquareStatus aux3 = SquareStatus.BOMB;
		assertEquals(aux3, real3);
		
		R[3][1].changeIsFlagged();
		SquareStatus real4 = b.getStatus(3,1);
		SquareStatus aux4 = SquareStatus.FLAGGED;
		assertEquals(aux4, real4);
	}
	
	@Test
	public void testLimitFrontera() {
		Board b = new BoardR(5, 6, 10);
		b.openSquare(-1, -1);
		int pending = b.getPendingSquares();
		assertEquals(20, pending);
		
		b.openSquare(5, 6);
		pending = b.getPendingSquares();
		assertEquals(20, pending);
		
		b.openSquare(5, -1);
		pending = b.getPendingSquares();
		assertEquals(20, pending);
		
		b.openSquare(-1,6);
		pending = b.getPendingSquares();
		assertEquals(20, pending);
	}
	
	@Test
	public void loopTestingInitialize() {
		Board b = new BoardR(5, 6, 0);
		b.initialize(0, 0, 0);
		Square[][] T = b.getBoard();
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += T[i][j].getIsBomb()? 1 : 0;
			}
		}	
		assertEquals(0, bombs);
		
		b = new BoardR(5, 6, 1);
		b.initialize(0, 0, 0);
		T = b.getBoard();
		bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += T[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(1, bombs);
		
		b = new BoardR(5, 6, 15);
		b.initialize(0, 0, 0);
		T = b.getBoard();
		bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += T[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(15, bombs);
		
		b = new BoardR(5, 6, 15);
		b.initialize(0, 0, 55555);
		T = b.getBoard();
		bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += T[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(15, bombs);
		
		b = new BoardR(5, 6, 28);
		b.initialize(0, 0, 0);
		T = b.getBoard();
		bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += T[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(28, bombs);		
	
		b = new BoardR(5, 6, 29);
		b.initialize(0, 0, 0);
		T = b.getBoard();
		bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += T[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(29, bombs);		
	}
	
	@Test
	public void conditionCoverageOpen() {
		Board b = new BoardR(5, 6, 29);
		b.openSquare(-1, -1);
		b.openSquare(-1, 7);
		b.openSquare(7, -1);
		b.openSquare(-1, 2);
		b.openSquare(7, 2);
		b.openSquare(2, 7);
		b.openSquare(2, -1);
		b.openSquare(2, 2);
		b.openSquare(2, 1);
		int pendings = b.getPendingSquares();
		assertEquals(0, pendings);
	}
}
