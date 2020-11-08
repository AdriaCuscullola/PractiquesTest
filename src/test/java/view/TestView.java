package view;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.GameInterface;
import controller.MockController;
import core.Board;
import core.MockBoard;
import core.Square;

public class TestView {

	@Test
	public void testConstructor() {
		GameInterface mock = new MockController();
		View view = new RView(12, 10, mock);
		assertEquals(12, view.getRows());
		assertEquals(10, view.getCols());
		
		JButtonExtend[][] buttons = view.getButtons();
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 10; j++) {
				assertEquals(true, buttons[i][j].isEnabled());
				assertEquals("", buttons[i][j].getText());
				assertEquals(i, buttons[i][j].getRow());
				assertEquals(j, buttons[i][j].getCol());
			}
		}
		assertEquals(12, buttons.length);
		assertEquals(10, buttons[0].length);
	}
	
	
	@Test
	public void testButtonConstructor() {
		int row = 1;
		int col = 2;
		GameInterface mock = new MockController();
		JButtonExtend button = new JButtonExtend(row, col, mock);
		assertEquals(1, button.getRow());
		assertEquals(2, button.getCol());
		assertEquals(mock, button.getController());
		
		assertEquals(mock, button.getMouseListeners()[1]);
	}
	
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
	
	
	/*
	 *   1 0 2  |
	 * x 1 0 2  |
	 * 1 1 1 2  |
	 * 0 0 1    |
	 * 0 0 1    |
	 * S'ha de mirar: Si esta enabled i el text.
	 */
	@Test
	public void testPrintBoard() {
		MockBoard boardMock = new MockBoard();
		boardMock.col = 5;
		boardMock.rows = 5;
		GameInterface mock = new MockController();
		RView view = new RView(5, 5, mock);
		JButtonExtend[][] buttons = view.getButtons();
		Square[][] board = createBoard(boardMock, 5, 5);
		boardMock.addBoard(board);
		board[1][0].setBomb();
		board[3][3].setBomb();
		board[0][4].setBomb();
		board[1][4].setBomb();
		board[4][0].open();
		board[0][2].open();
		board[1][0].open();
		//Necesitem separar aquesta funcio per controlar botons sols
		view.proxyPrintaBoto(board[0][0], 0, 0);
		assertEquals(true, buttons[0][0].isEnabled());
		assertEquals("", buttons[0][0].getText());
		view.proxyPrintaBoto(board[4][0], 4, 0);
		assertEquals(false, buttons[4][0].isEnabled());
		assertEquals("", buttons[4][0].getText());
		view.proxyPrintaBoto(board[2][0], 2, 0);
		assertEquals(false, buttons[2][0].isEnabled());
		assertEquals("1", buttons[2][0].getText());
	}

}
