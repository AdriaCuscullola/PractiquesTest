package controller;


import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.BoardR;
import core.Square;
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
}
