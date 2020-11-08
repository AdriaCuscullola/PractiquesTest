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
	
	private Square[][] generateBoard(int rows, int columns) {
		Board b = new MockBoard();
		Square[][] board = new Square[rows][columns];
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<columns; j++) {
				Square newSquare = new Square();
				newSquare.addBoardPosition(i, j);
				board[i][j] = newSquare;
				newSquare.setBoard(b);
			}
		}
		return board;
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
		int row = 1;
		int col = 2;
		GameInterface mock = new MockController();
		View view = new RView(5, 5, mock);
		JButtonExtend[][] buttons = view.getButtons();
		Square[][] board = generateBoard(5, 5);
		board[1][0].setBomb();
		board[3][3].setBomb();
		board[0][4].setBomb();
		board[1][4].setBomb();
		board[4][0].open();
		board[0][2].open();
		board[1][0].open();
		//Necesitem separar aquesta funcio per controlar botons sols
		view.proxyPrintaBoto(board[0][0]);
		assertEquals(true, buttons[0][0].isEnabled());
		assertEquals("", buttons[0][0].getText());
	}

}
