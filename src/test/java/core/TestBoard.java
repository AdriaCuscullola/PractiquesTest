package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBoard {
		
	@Test
	public void testInitialize() { //TDD. Caja Negra. Comprueba que los datos de entrada se incializen.
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
	public void testOpen() { //TDD. Caja Negra.Comprueba que se haya inizializado el talbero a partir de un booleano.
		Board b = new BoardR(5, 6, 10);
		
		b.openSquare(4, 3);
		boolean initialized = b.getInitialized();
		
		assertEquals(true, initialized);			
	}	
	
	@Test
	public void testInitalized() { //TDD. Caja Negra. Comprueba si se han generado las bombas solicitadas en el tablero (por lo tanto, se ha generado el tablero correctamente).
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
	public void testInitialized0Bombes() { //TDD. Caja Negra. Comprueba valor limite 0 bombas.
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
	public void testInitializedMaximBombes() { //TDD. Caja Negra. Comprueba valor limite del maximo de bombas.
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
	public void testInitializedLimitBombes() { //TDD. Caja Negra. Comprobación valor límite metiendo más bombas de las posibles.
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
	public void testPendingSquares() { //TDD. Caja Negra. Test que comprueba cuántas celdas vacías quedan por abrir.
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
	public void testOpenSquares() { //TDD. Caja Negra. Comprueba si se abre bien la casilla (devuelve 0), si ya se ha abierto (devuelve 1) o si hay una bandera (devuelve 2).
		Board b = new BoardR(5, 6, 10);
		boolean isOpen = b.getSquareIsOpen(4,3);
		assertEquals(false, isOpen);
		b.openSquare(4, 3);
		isOpen = b.getSquareIsOpen(4,3);
		assertEquals(true, isOpen);
	}
	
	@Test 
	public void testIsBomb() { //TDD. Caja Negra. Comprueba si se mete la bomba correctamente y si al abrir la casilla de la bomba devuelve bomba.
		Board c = new BoardR(1, 2, 1);
		Square[][] R = c.getBoard();
		boolean bomb = R[0][0].getIsBomb();
		assertEquals(false, bomb);	
		c.openSquare(0, 1);
		bomb = R[0][0].getIsBomb();
		assertEquals(true, bomb);	
	}
	
	@Test
	public void testSetFlag() { //TDD. Caja Negra. Comprueba si se va cambiando correctamente el estado de flag de la casilla.
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
	public void testGetStatus() { //TDD. Caja Negra. Comprueba si el getStatus devuelve el ENUM indicado.
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
	public void testLimitFrontera() { //TDD. Caja Negra. Comprueba los valores limites del tablero.
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
	public void loopTestingInitialize() { //TDD. Caja Blanca. Recorre todos los caminos de inizialicación del tablero con 0, 1, 15, max-1 i max bombas.
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
	public void conditionCoverageOpen() { //TDD. Caja Blanca. Recorre todos los caminos posibles de las condiciones del openSquare para poder hacer un condition Coverage.
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
