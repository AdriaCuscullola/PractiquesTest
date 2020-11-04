package controller;


import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.BoardR;
import core.Square;

public class testGame {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitialize() {
		View v = new View(12, 10, this);	//el tauler per defecte serà 12 X 10.
		BoardR b = new BoardR(12, 10, 20);
		Square[][] R = b.getBoard();
		int bombs = 0;
		for(int i = 0; i < b.getRows(); i++) {
			for (int j = 0; j < b.getCols(); j++) {
				bombs += R[i][j].getIsBomb()? 1 : 0;
			}
		}
		assertEquals(12, b.getRows());
		assertEquals(10, b.getCols());
		assertEquals(20, bombs);
	}
}
