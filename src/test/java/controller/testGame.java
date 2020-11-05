package controller;


import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.BoardR;
import core.Square;
import core.SquareStatus;
import view.MockView;
import view.View;

public class testGame {


	@Test
	public void testInitializeBoard() { //
		Game g = new Game();
		/*View view = new MockView();
		g.setView(view);
		View v = g.getView();*/
		BoardR b = g.getBoard();
		Square[][] R = b.getBoard();
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(12, b.getRows());
		assertEquals(10, b.getCols());
		assertEquals(0, bombs);
	}
	
	@Test
	public void testOpenBoard() {
		Game g = new Game();
		Board b = g.getBoard();
		g.openBoard(3,3);
		Square[][] R = b.getBoard();
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(20, bombs);
	}
	
	@Test
	public void testFlagBoard() {
		Game g = new Game();
		Board b = g.getBoard();
		g.flagBoard(4,4);
		SquareStatus real = b.getStatus(4, 4);
		SquareStatus aux = SquareStatus.FLAGGED;
		assertEquals(aux, real);		
	}
	
	@Test
	public void testClickView() {
		MockView v = new MockView();
		
		
	}
}
