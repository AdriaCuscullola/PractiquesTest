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
	}
	
	@Test
	public void testGetSquarePosition() {
		Square square = new Square();
		square.addBoardPosition(1, 1);
		assertEquals(1, square.getXPosition());
		assertEquals(1, square.getYPosition());
	}
	
	@Test
	public void testValorCasellaNum() {
		MockBoard board = new MockBoard();
		Square testedSquare = new Square();
		testedSquare.addBoard(board);
		Square[][] squares;
		Square bomb = new Square();
		bomb.setBomb();
		Square normalSquare = new Square();
		squares = new Square[][] {{bomb, normalSquare, normalSquare}, {bomb, testedSquare, bomb}, {normalSquare, normalSquare, normalSquare}};
		board.addBoard(squares);
		testedSquare.open();
		assertEquals(3, testedSquare.getValue());
	}
	
}
