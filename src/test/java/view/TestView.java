package view;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.swing.JOptionPane;

import org.junit.Test;

import controller.GameInterface;
import controller.MockController;
import core.Board;
import core.MockBoard;
import core.Square;

public class TestView {
	
	/**
	 * TDD. Caja negra. Utiliza un mock del controlador. Comprueba que al generar la vista
	 * con los valores de entrada (filas / columnas), estos se asocien de forma correcta y también
	 * que la generación dde los botones tengan los datos de inicialización correctos.
	 */
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
	
	/**
	 * TDD. Caja negra. Utiliza un mock del controlador. Comprueba que la generacion de un boton
	 * sea la correcta con los datos de entrada especificados y sus valores por defecto
	 */
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
	
	/**
	 * TDD. Caja negra. Utiliza un mock del controlador y de board. Utiliza un proxy de
	 * un método privado. Comprueba que al pasarle distintos Squares, los botones se printen
	 * con el valor adequado. Particiones equivalentes tenemos todos los tipos de Squares posibles
	 * Con valor, Sin Valor,  Sin Abrir, Con bandera y Bomba
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
		board[0][4].changeIsFlagged();
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
		view.proxyPrintaBoto(board[1][0], 1, 0);
		assertEquals(false, buttons[1][0].isEnabled());
		assertEquals("X", buttons[1][0].getText());
		view.proxyPrintaBoto(board[0][4], 0, 4);
		assertEquals(true, buttons[0][4].isEnabled());
		assertEquals("F", buttons[0][4].getText());
	}
	
	/**
	 * TDD. Caja negra. Utiliza un mock del controlador y de board. Se genera el mismmo
	 * test que el anterior pero utilizando el método que llamara el constructor donde
	 * se pasa el tablero entero y lo printa todo a la vez.
	 */
	@Test
	public void testPrintBoardTotal() {
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
		board[0][4].changeIsFlagged();
		view.printaTauler(board);
		//Necesitem separar aquesta funcio per controlar botons sols
		assertEquals(true, buttons[0][0].isEnabled());
		assertEquals("", buttons[0][0].getText());
		assertEquals(false, buttons[4][0].isEnabled());
		assertEquals("", buttons[4][0].getText());
		assertEquals(false, buttons[2][0].isEnabled());
		assertEquals("1", buttons[2][0].getText());
		assertEquals(false, buttons[1][0].isEnabled());
		assertEquals("X", buttons[1][0].getText());
		assertEquals(true, buttons[0][4].isEnabled());
		assertEquals("F", buttons[0][4].getText());
	}
	
	
	/**
	 * Caja negra. Utiliza un mock del controlador y uno de la propia vista. Se encarga que 
	 * los valores de la vista y la cantidad de botones sean los correctos dependiendo del 
	 * valor de dificultad pasado por el usuari. Este valor ha sido mockeado en la propia vista.
	 */
	@Test
	public void testTriaDificultatEnAcabar() {
		RView view = mock(RView.class);
		MockController mock = new MockController();
		doCallRealMethod().when(view).setController(any(MockController.class));
		view.setController(mock);
		doCallRealMethod().when(view).getRows();
		doCallRealMethod().when(view).getCols();
		when(view.showMessage(any(String.class),any(String.class),any(int.class))).thenReturn(0);
		doCallRealMethod().when(view).finish(any(boolean.class));
		view.finish(true);
		assertEquals(8, view.getRows());
		assertEquals(10, view.getCols());
		when(view.showMessage(any(String.class),any(String.class),any(int.class))).thenReturn(1);
		view.finish(false);
		assertEquals(14, view.getRows());
		assertEquals(18, view.getCols());
		when(view.showMessage(any(String.class),any(String.class),any(int.class))).thenReturn(2);
		view.finish(false);
		assertEquals(20, view.getRows());
		assertEquals(24, view.getCols());
	}

}
