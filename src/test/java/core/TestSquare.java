package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSquare {
	
	@Test
	public void testOpen() {
		Square s = new Square();
		s.open();
		boolean isOpen = s.getIsOpen();
		assertEquals(true, isOpen);
	}
	
	@Test
	public void testBomba() {
		Square s = new Square();
		s.setBomb();
		boolean isBomb = s.getIsBomb();
		assertEquals(true, isBomb);
		assertEquals(-1, s.getValue());
	}
	
	@Test
	public void testSetBomb() {
		Square s = new Square();
		boolean set = s.setBomb();
		assertEquals(true, set);
		set = s.setBomb();
		assertEquals(false,set);
	}	
	
	@Test
	public void testGetSquarePosition() {
		Square square = new Square();
		square.addBoardPosition(1, 1);
		assertEquals(1, square.getRow());
		assertEquals(1, square.getColumn());
	}
	/*
	 * Tests de Board de 1x1
	 */
	
	@Test
	public void testOpenSquareEnUnBoardDeUnaSquare() {
		/*
		 * Es genera un board 1x1 i es mira de obrir la casella que hi ha
		 */
		MockBoard board = new MockBoard();
		board.col = 1;
		board.rows = 1;
		Square[][] squares = createBoard(board, 1, 1);
		board.addBoard(squares);
		board.openSquare(0, 0);
		assertEquals(0, board.getSquare(0, 0).getValue());
		assertTrue(board.getSquare(0, 0).getIsOpen());
	}
	
	@Test
	public void testOpenBombEnUnBoardDeUnaSquare() {
		/*
		 * Es genera un board 1x1 i es mira de obrir la bomba que hi ha
		 */
		MockBoard board = new MockBoard();
		board.col = 1;
		board.rows = 1;
		Square[][] squares = createBoard(board, 1, 1);
		squares[0][0].setBomb();
		board.addBoard(squares);
		board.openSquare(0, 0);
		assertEquals(-1, board.getSquare(0, 0).getValue());
		assertTrue(board.getSquare(0, 0).getIsOpen());
	}
	
	/*
	 * Testos que comproven que el valor que te una casella (num de mines aprop) és el correcte
	 */

	@Test
	public void testValorCasellaNum() {
		/*
		 * Generem un Board 3x3 i posem la casella de test al mig, aquesta te un total de 3 mines al costat
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(1, 1);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{bomb, normalSquare, normalSquare}, {bomb, testedSquare, bomb}, {normalSquare, normalSquare, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(3, testedSquare.getValue());
	}
	
	
	@Test
	public void testValorCasellaNumSenseBombes() {
		/*
		 * Generem un Board 3x3 i posem la casella de test al mig, aquesta te un total de 0 mines al costat. Cas frontera
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(1, 1);
		Square[][] squares;
		Square normalSquare = new Square();
		squares = new Square[][] {{normalSquare, normalSquare, normalSquare}, {normalSquare, testedSquare, normalSquare}, {normalSquare, normalSquare, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(0, testedSquare.getValue());
	}
	
	@Test
	public void testValorCasellaNumNomesBombes() {
		/*
		 * Generem un Board 3x3 i posem la casella de test al mig, aquesta te un total de 8 mines al costat. Cas frontera
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(1, 1);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		squares = new Square[][] {{bomb, bomb, bomb}, {bomb, testedSquare, bomb}, {bomb, bomb, bomb}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(8, testedSquare.getValue());
	}
	
	@Test
	public void testValorCasellaNumEnUnCostat() {
		/*
		 * Generem un Board 3x3 i posem la casella de test al centre superior, aquesta te un total de 2 mines al costat.
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(0, 1);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{bomb, testedSquare, normalSquare}, {bomb, normalSquare, normalSquare}, {normalSquare, normalSquare, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(2, testedSquare.getValue());
	}
	
	@Test
	public void testValorCasellaNumEnUnaCantonada() {
		/*
		 * Generem un Board 3x3 i posem la casella de test a la cantonada superior esquerre, aquesta te un total de 1 mina al costat.
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square testedSquare = new Square();
		testedSquare.setBoard(board);
		testedSquare.addBoardPosition(0, 0);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{testedSquare, normalSquare, normalSquare}, {normalSquare, bomb, normalSquare}, {bomb, bomb, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(1, testedSquare.getValue());
	}
	
	/*
	 * Conjunt de testos que comproven que si obres una casella buida (valor = 0), s'obrin les del costat també.
	 */
	
	private Square[][] createBoard(Board board, int rows, int columns) {
		Square[][] squares = new Square[rows][columns];
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<columns; j++) {
				Square newSquare = new Square();
				newSquare.addBoardPosition(i, j);
				squares[i][j] = newSquare;
				newSquare.setBoard(board);
			}
		}
		return squares;
	}
	
	@Test
	public void testCasellesBuidesAdjacentsObertes() {
		/*
		 * Generem un board 3x3 i a la casella inferior dreta afegim una bomba. Obrim la de l'esquerra superior i s'haurien de obrir totes menys la bomba
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		board.addBoard(squares);
		board.openSquare(0, 0);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				if(i != 2 && j != 2) {
					assertTrue(square.getIsOpen());
				} else if (i == 2 && j == 2){
					assertFalse(square.getIsOpen());
				}
			}
		}
	}
	
	@Test
	public void testCasellesBuidesAdjacentsObertesClicantNum() {
		/*
		 * Generem un board 3x3 i a la casella inferior dreta afegim una bomba. Obrim la del centre i s'haurien de obrir nomes la del centre
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		board.addBoard(squares);
		board.openSquare(1, 1);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				if(i == 1 && j == 1) {
					assertTrue(square.getIsOpen());
				} else {
					assertFalse(square.getIsOpen());
				}
			}
		}
	}
	
	@Test
	public void testCasellesBuidesAdjacentsObertesClicantBomba() {
		/*
		 * Generem un board 3x3 i a la casella inferior dreta afegim una bomba. Obrim la Bomba i s'hauria de obrir nomes la Bomba
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		board.addBoard(squares);
		board.openSquare(2, 2);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				if(i == 2 && j == 2) {
					assertTrue(square.getIsOpen());
				} else {
					assertFalse(square.getIsOpen());
				}
			}
		}
	}
	
	/*
	 * Testos que comproven el mètode de afegir banderes
	 */
	
	@Test
	public void testSetBandera() {
		/*
		 * Test que comprova si al afegir una bandera o al treure-la canvia el seu estat
		 */
		Square square = new Square();
		assertFalse(square.isFlagged());
		square.changeIsFlagged();
		assertTrue(square.isFlagged());
		square.changeIsFlagged();
		assertFalse(square.isFlagged());
	}
	
	@Test
	public void testFlagAnOpenSquare() {
		/*
		 * Test que comprova que una cel·la no es pugui marcar amb una bandera si està obert
		 */
		Square square = new Square();
		assertFalse(square.isFlagged());
		square.open();
		square.changeIsFlagged();
		assertFalse(square.isFlagged());
	}
	
	@Test
	public void testOpenFlaggedSquare() {
		/*
		 * Test que comprova que una cel·la no s'obri al tenir una bandera
		 */
		Square square = new Square();
		square.changeIsFlagged();
		square.open();
		assertFalse(square.getIsOpen());
	}
	
	@Test
	public void testOpenFlaggedBomb() {
		/*
		 * Test que comprova que una cel·la bomba no s'obri al tenir una bandera
		 */
		Square square = new Square();
		square.setBomb();
		square.changeIsFlagged();
		square.open();
		assertFalse(square.getIsOpen());
	}
	
	@Test
	public void testOpenEmptySquareInBoardWithFlag() {
		/*
		 * Generem un board 3x3 i a la casella inferior dreta afegim una bomba. Afegim una bandera a la cel·la del centre i obrim la de l'esquerra superior.
		 * S'han de obrir totes menys la bomba i la de la bandera
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		squares[1][1].changeIsFlagged();
		board.addBoard(squares);
		board.openSquare(0, 0);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				if(i == 2 && j == 2) {
					assertFalse(square.getIsOpen());
				} else if (i == 1 && j == 1) {
					assertFalse(square.getIsOpen());
				} else {
					assertTrue(square.getIsOpen());
				}
			} 
		}
	}
	
	@Test
	public void testOpenEmptySquareFlaggedInBoard() {
		/*
		 * Generem un board 3x3 i a la casella inferior dreta afegim una bomba. Afegim una bandera a la cel·la de l'esquerra superior i la obrim.
		 * No s'ha de obrir cap.
		 */
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = createBoard(board, 3, 3);
		squares[2][2].setBomb();
		squares[0][0].changeIsFlagged();
		board.addBoard(squares);
		board.openSquare(0, 0);
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square square = board.getSquare(i, j);
				assertFalse(square.getIsOpen());
			} 
		}
	}
	
	@Test
	public void testGetStatusBomb() {
		Square s = new Square();
		s.setBomb();
		SquareStatus real = s.getStatus();
		SquareStatus aux = SquareStatus.NOT_OPEN;
		assertEquals(aux, real);
		s.open();
		real = S.getStatus();
		aux = SquareStatus.BOMB;
		assertEquals(aux,real);
	}
	
	@Test
	public void testGetStatusFlagged() {
		Square s = new Square();
		s.setBomb();
		SquareStatus real = s.getStatus();
		SquareStatus aux = SquareStatus.NOT_OPEN;
		assertEquals(aux, real);
		s.changeIsFlagged();
		real = s.getStatus();
		aux = SquareStatus.FLAGGED;
		assertEquals(aux,real);
	}
	
	@Test
	public void testGetStatusOpen() {
		Square s = new Square();
		s.open();
		SquareStatus real = s.getStatus();
		SquareStatus aux = SquareStatus.VALUE;
		assertEquals(aux, real);
	}
	
	
}
