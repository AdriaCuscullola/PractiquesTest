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
	public void testGetSquarePosition() {
		Square square = new Square();
		square.addBoardPosition(1, 1);
		assertEquals(1, square.getRow());
		assertEquals(1, square.getColumn());
	}
	
	@Test
	public void testValorCasellaNum() {
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
	public void testValorCasellaNumNomesBomes() {
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
	
	@Test
	public void testCasellesBuidesAdjacentsObertes() {
		MockBoard board = new MockBoard();
		board.col = 3;
		board.rows = 3;
		Square[][] squares = new Square[3][3];
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				Square newSquare = new Square();
				newSquare.addBoardPosition(i, j);
				squares[i][j] = newSquare;
				newSquare.setBoard(board);
			}
		}
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
	
}
